package com.kiss.message.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Template {

    private Integer id;

    private String uniqid;

    private Integer clientId;

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
