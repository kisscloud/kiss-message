package com.kiss.message.dao;

import com.kiss.message.entity.Template;
import com.kiss.message.output.TemplateOutput;

import java.util.List;

public interface TemplateDao {

    Integer createTemplate(Template template);

    Integer updateTemplate(Template template);

    Template getTemplateByUniqid(String uniqid);

    List<TemplateOutput> getTemplateOutputs(Integer start, Integer size, Integer type);

    Template getTemplateByUniqidAndClientId(Integer clientId, String uniqid);

    Integer addTemplateCount(Integer id, Integer type);
}
