package com.kiss.message.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Locale {

    private Integer id;

    private Integer templateId;

    private String lang;

    private String content;

    private String from;

    private String subject;

    private Date createdAt;

    private Date updatedAt;
}
