package com.kiss.message.dao.Impl;

import com.kiss.message.dao.SMSLogDao;
import com.kiss.message.entity.SMSLog;
import com.kiss.message.mapper.SMSLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SMSLogDaoImpl implements SMSLogDao {

    @Autowired
    private SMSLogMapper smsLogMapper;

    @Override
    public List<SMSLog> getSMSLogs(Map params) {

        return smsLogMapper.getSMSLogs(params);
    }

    @Override
    public Integer createSMSLog(SMSLog smsLog) {

        return smsLogMapper.createSMSLog(smsLog);
    }
}
