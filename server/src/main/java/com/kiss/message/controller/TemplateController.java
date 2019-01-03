package com.kiss.message.controller;

import com.kiss.message.client.TemplateClient;
import com.kiss.message.input.CreateTemplateInput;
import com.kiss.message.service.TemplateService;
import com.kiss.message.validator.TemplateValidator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

@RestController
@Api(tags = "Template", description = "模板配置相关接口")
public class TemplateController implements TemplateClient {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateValidator templateValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.setValidator(templateValidator);
    }

    @Override
    public ResultOutput createTemplate(@RequestBody CreateTemplateInput createTemplateInput) {

        return templateService.createTemplate(createTemplateInput);
    }

    @Override
    public ResultOutput getTemplates(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("type") Integer type) {

        return templateService.getTemplates(page, size, type);
    }
}
