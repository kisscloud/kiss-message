package com.kiss.message.dao.Impl;

import com.kiss.message.dao.MailLogDao;
import com.kiss.message.entity.MailLog;
import com.kiss.message.mapper.MailLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MailLogDaoImpl implements MailLogDao {

    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public List<MailLog> getMailLogs(Map params) {

        return mailLogMapper.getMailLogs(params);
    }

    @Override
    public Integer createMailLog(MailLog mailLog) {

        return mailLogMapper.createMailLog(mailLog);
    }
}
