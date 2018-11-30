package com.kiss.message.dao;

import com.kiss.message.entity.MailLog;

import java.util.List;
import java.util.Map;

public interface MailLogDao {

    List<MailLog> getMailLogs(Map params);

    Integer createMailLog(MailLog mailLog);
}
