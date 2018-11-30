package com.kiss.message.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Client {

    private Integer id;

    private String uniqid;

    private String name;

    private String alias;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
