package com.kiss.message.controller;

import com.kiss.message.client.MessageClient;
import com.kiss.message.enums.Consumer;
import com.kiss.message.input.SendMailInput;
import com.kiss.message.input.SendSMSInput;
import com.kiss.message.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = "message", description = "发送短信邮件相关接口")
@Slf4j
public class MessageController implements MessageClient {


    @Autowired
    private ConsumerService consumerService;

    @Override
    @ApiOperation(value = "发送短信")
    public ResultOutput sendSMS(SendSMSInput sendSMSInput) {

        return consumerService.send(Consumer.SMS.type,Consumer.SINGLE.type,sendSMSInput.getClientId(),sendSMSInput.getMobile(),sendSMSInput.getContent(),sendSMSInput.getData(),sendSMSInput.getLang(),sendSMSInput.getLock());
    }

    @Override
    public ResultOutput sendMail(SendMailInput sendMailInput) {

        return consumerService.send(Consumer.SMS.type,Consumer.SINGLE.type,sendMailInput.getClientId(),sendMailInput.getMail(),sendMailInput.getContent(),sendMailInput.getData(),sendMailInput.getLang(),sendMailInput.getLock());
    }

    public static void main(String[] args) {
        String content = "aaa{{ $code    }}dddd";
        String regex = String.format("\\{\\{\\s*\\$%s\\s*\\}\\}", "code");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll(String.valueOf(1234));

        System.out.println(content);
    }
}
