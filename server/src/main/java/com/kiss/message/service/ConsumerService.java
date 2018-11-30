package com.kiss.message.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiss.message.dao.*;
import com.kiss.message.entity.*;
import com.kiss.message.enums.Consumer;
import com.kiss.message.status.MessageStatusCode;
import com.kiss.message.util.HttpUtil;
import com.kiss.message.util.RedisUtils;
import com.kiss.message.util.ResultOutputUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import output.ResultOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ConsumerService extends BaseService {

    @Autowired
    private SMSLogDao smsLogDao;

    @Autowired
    private MailLogDao mailLogDao;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private LocaleDao localeDao;


    public ResultOutput send(Integer contentType, Integer messageType, String clientId, String receiver, String content, String data, String lang, Integer lock) {

        String cacheKey = cachePrefix + receiver;

        if (lock != null && lock != 0) {
            String lockBool = redisUtils.get(cacheKey);

            if (lockBool != null) {
                log.info("Lock {} {} seconds", receiver, lock);
                return ResultOutputUtil.error(MessageStatusCode.LOCKING);
            }
        }

        Client client = clientDao.getClientByUniqid(clientId);

        if (client == null) {
            log.info("App is not exist");
            return ResultOutputUtil.error(MessageStatusCode.APP_NOT_EXIST);
        }

        String templateId = parseTemplateId(content);

        if (templateId == null) {
            log.info("Content is illegal");
            return ResultOutputUtil.error(MessageStatusCode.CONTENT_IS_ILLEGAL);
        }

        Template template = templateDao.getTemplateByUniqid(templateId);

        if (template == null) {
            log.info("Template {} is not exist", templateId);
            return ResultOutputUtil.error(MessageStatusCode.TEMPLATE_NOT_EXIST);
        }

        Locale locale = localeDao.getLocalByTemplateId(template.getId(), lang);

        if (locale == null) {
            log.info("Template locale content is not exist");
            return ResultOutputUtil.error(MessageStatusCode.TEMPLATE_LOCALE_NOT_EXIST);
        }

        Map<String, Object> dataMap;

        try {
            dataMap = JSONObject.parseObject(data);
        } catch (Exception e) {
            log.info("Parse data error:{}", e.getMessage());
            e.printStackTrace();
            return ResultOutputUtil.error(MessageStatusCode.DATA_PARSE_TO_JSON_ERROR);
        }

        content = bindTemplateData(locale.getContent(), dataMap);
        String from = bindTemplateData("{{ $from }}", dataMap);
        String subject = bindTemplateData("{{ $subject }}", dataMap);

        if (messageType == Consumer.SINGLE.type) {

            if (contentType == Consumer.MAIL.type) {
                sendSMS(client.getId(), locale.getTemplateId(), Consumer.SINGLE.type, receiver, from, subject, content);
            } else if (contentType == Consumer.SMS.type) {
                sendMail(client.getId(), locale.getTemplateId(), Consumer.SINGLE.type, receiver, from, subject, content);
            }
        }

        return ResultOutputUtil.success();
    }

    public void sendSMS(Integer clientId, Integer templateId, Integer messageType, String receiver, String from, String subject, String content) {
        log.info("Wait ti send sms");

        String[] res = parseSMSReveive("");
        Map<String, Object> params = new HashMap<>();
        params.put("account", mobileUser);
        params.put("password", mobilePassword);
        params.put("mobile", res[0] + res[1]);
        params.put("msg", "");
        params.put("report", "false");

        SMSLog smsLog = new SMSLog();
        smsLog.setType(1);
        smsLog.setClientId(clientId);
        smsLog.setTemplateId(templateId);
        smsLog.setReceiver(receiver);
        smsLog.setContent(content);
        smsLog.setChannel("master");

        if (!env.equals("staging") && !env.equals("production")) {
            return;
        }

        String bytesData = null;

        try {
            bytesData = JSON.toJSONString(params);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ToJsonString sms params via master error");
        }

        String result = HttpUtil.doPost(mobileHost, bytesData);

        if (result == null) {
            log.info("send sms host via master error");
            smsLog.setStatus(2);
        }

        JSONObject resJson = JSONObject.parseObject(result);

        if (!"0".equals(resJson.getString("code"))) {
            log.info("Send sms host via master error");
            smsLog.setStatus(2);
        } else {
            smsLog.setStatus(1);
        }

        smsLog.setResponse(result);

        Integer count = smsLogDao.createSMSLog(smsLog);

        if (count == 0) {
            log.info("创建smslog失败:{}",smsLog);
        }

        templateDao.addTemplateCount(templateId,2);
    }

    public void sendMail(Integer clientId, Integer templateId, Integer messageType, String receiver, String from, String subject, String content) {

        log.info("Wait to send mail");
        MailLog mailLog = new MailLog();
        mailLog.setType(1);
        mailLog.setClientId(clientId);
        mailLog.setTemplateId(templateId);
        mailLog.setReceiver(receiver);
        mailLog.setFrom(from);
        mailLog.setSubject(subject);
        mailLog.setContent(content);
        mailLog.setChannel("master");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("apiUser", mailUser));
        params.add(new BasicNameValuePair("apiKey", mailPassword));
        params.add(new BasicNameValuePair("from", mainFrom));
        params.add(new BasicNameValuePair("to", receiver));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", content));
        params.add(new BasicNameValuePair("fromName", from));

        if (!env.equals("staging") && !env.equals("production")) {
            return;
        }

        String result = HttpUtil.formDataPost(mailHost, params);

        if (result == null) {
            log.info("Send mail via master error");
        }

        JSONObject res = JSONObject.parseObject(result);

        if (res.getInteger("statusCode") == 200) {
            log.info("Send main via master success");
        } else {
            log.info("Send main via master error");
            mailLog.setResponse(result);
        }

        Integer count = mailLogDao.createMailLog(mailLog);

        if (count == 0) {
            log.info("Write mail log error {}", mailLog.toString());
        }

        templateDao.addTemplateCount(templateId,1);
    }

    public String[] parseSMSReveive(String receiver) {

        if (receiver.contains("-")) {
            String[] res = receiver.split("-");
            res[0] = res[0].replaceFirst("\\+", "");
            return res;
        }

        String[] res = new String[]{"", receiver};

        return res;
    }

    public String parseTemplateId(String content) {

        String prefix = "template::";
        if (content.startsWith(prefix)) {
            return content.substring(prefix.length());
        }

        return null;
    }

    public String bindTemplateData(String content, Map<String, Object> dataMap) {

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            String regex = String.format("\\{\\{\\s*\\$%s\\s*\\}\\}", entry.getKey());
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            content = matcher.replaceAll(String.valueOf(entry.getValue()));
        }

        return content;
    }
}
