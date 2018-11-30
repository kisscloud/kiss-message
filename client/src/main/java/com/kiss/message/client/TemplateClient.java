package com.kiss.message.client;

import com.kiss.message.input.CreateTemplateInput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface TemplateClient {

    @PostMapping("/template")
    ResultOutput createTemplate(CreateTemplateInput createTemplateInput);

    @GetMapping("/templates")
    ResultOutput getTemplates(Integer page,Integer size,Integer type);
}
