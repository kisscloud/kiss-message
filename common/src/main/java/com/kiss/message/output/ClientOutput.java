package com.kiss.message.output;

import lombok.Data;

@Data
public class ClientOutput {

    private Integer id;

    private String uniqid;

    private String name;

    private Integer status;

    private Long createdAt;

    private Long updatedAt;
}
