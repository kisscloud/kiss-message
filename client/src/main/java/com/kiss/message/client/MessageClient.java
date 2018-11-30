package com.kiss.message.client;

import com.kiss.message.input.SendMailInput;
import com.kiss.message.input.SendSMSInput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface MessageClient {

    @PostMapping("/send/sms")
    ResultOutput sendSMS(@Validated @RequestBody SendSMSInput sendSMSInput);

    @PostMapping("/send/mail")
    ResultOutput sendMail(@Validated @RequestBody SendMailInput sendMailInput);
}
