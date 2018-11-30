package com.kiss.message.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MailLog {

    private Integer id;

    private Integer clientId;

    private Integer templateId;

    private String receiver;

    private String from;

    private String subject;

    private String content;

    private String channel;

    private String response;

    private Integer type;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
