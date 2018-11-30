package com.kiss.message.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class BaseService {

    @Value("${sms_service_master.user}")
    public String mobileUser;

    @Value("${sms_service_master.password}")
    public String mobilePassword;

    @Value("${sms_service_master.host}")
    public String mobileHost;

    @Value("${redis.cachePrefix}")
    public String cachePrefix;

    @Value("${env}")
    public String env;

    @Value("${mail_service_master.host}")
    public String mailUser;

    @Value("${mail_service_master.password}")
    public String mailPassword;

    @Value("${mail_service_master.host}")
    public String mailHost;

    @Value("${mail_service_master.from}")
    public String mainFrom;
}
