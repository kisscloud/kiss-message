package com.kiss.message.input;

import lombok.Data;

@Data
public class SendSMSInput {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * template uniqid
     */
    private String content;

    /**
     * 模板中使用到的数据
     */
    private String data;

    /**
     * 语言
     */
    private String lang;

    /**
     * 是否需要锁定
     */
    private Integer lock;
}
