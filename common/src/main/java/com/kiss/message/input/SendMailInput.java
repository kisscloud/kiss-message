package com.kiss.message.input;

import lombok.Data;

@Data
public class SendMailInput {

    private String clientId;

    private String mail;

    private String content;

    private String data;

    private String lang;

    private Integer lock;
}
