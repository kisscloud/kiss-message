package com.kiss.message.dao;

import com.kiss.message.entity.SMSLog;

import java.util.List;
import java.util.Map;

public interface SMSLogDao {

    List<SMSLog> getSMSLogs(Map params);

    Integer createSMSLog(SMSLog smsLog);
}
