package com.kiss.message.input;

import lombok.Data;

@Data
public class CreateTemplateInput {

    private String uniqid;

    private Integer cliendId;

    private Integer type;
}
