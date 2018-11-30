package com.kiss.message.output;

import lombok.Data;

@Data
public class TemplateOutput {

    private Integer id;

    private String uniqid;

    private Integer clientId;

    private String clientName;

    private Integer count;

    private Long createdAt;

    private Long updatedAt;
}
