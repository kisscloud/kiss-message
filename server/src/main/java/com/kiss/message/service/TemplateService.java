package com.kiss.message.service;

import com.kiss.message.dao.TemplateDao;
import com.kiss.message.entity.Template;
import com.kiss.message.input.CreateTemplateInput;
import com.kiss.message.output.TemplateOutput;
import com.kiss.message.status.MessageStatusCode;
import com.kiss.message.util.ResultOutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import output.ResultOutput;
import utils.BeanCopyUtil;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateDao templateDao;

    public ResultOutput createTemplate(CreateTemplateInput createTemplateInput) {

        Template template = BeanCopyUtil.copy(createTemplateInput,Template.class);
        Integer count = templateDao.createTemplate(template);

        if (count == 0) {
            return ResultOutputUtil.error(MessageStatusCode.CREATE_TEMPLATE_FAILED);
        }

        TemplateOutput templateOutput = BeanCopyUtil.copy(template,TemplateOutput.class,BeanCopyUtil.defaultFieldNames);

        return ResultOutputUtil.success(templateOutput);
    }

    public ResultOutput getTemplates(Integer page,Integer size,Integer type) {

        Integer start = (page - 1) * size;
        List<TemplateOutput> templateOutputs = templateDao.getTemplateOutputs(start,size,type);

        return ResultOutputUtil.success(templateOutputs);
    }
}
