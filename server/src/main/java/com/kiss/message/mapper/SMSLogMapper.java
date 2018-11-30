package com.kiss.message.mapper;

import com.kiss.message.entity.SMSLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SMSLogMapper {

    List<SMSLog> getSMSLogs(Map params);

    Integer createSMSLog(SMSLog smsLog);
}
