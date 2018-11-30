package com.kiss.message.mapper;

import com.kiss.message.entity.MailLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MailLogMapper {

    List<MailLog> getMailLogs(Map params);

    Integer createMailLog(MailLog mailLog);
}
