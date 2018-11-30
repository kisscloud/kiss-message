package com.kiss.message.dao.Impl;

import com.kiss.message.dao.TemplateDao;
import com.kiss.message.entity.Template;
import com.kiss.message.mapper.TemplateMapper;
import com.kiss.message.output.TemplateOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TemplateDaoImpl implements TemplateDao {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public Integer createTemplate(Template template) {

        return templateMapper.createTemplate(template);
    }

    @Override
    public Integer updateTemplate(Template template) {

        return templateMapper.updateTemplate(template);
    }

    @Override
    public Template getTemplateByUniqid(String uniqid) {

        return templateMapper.getTemplateByUniqid(uniqid);
    }

    @Override
    public List<TemplateOutput> getTemplateOutputs(Integer start, Integer size,Integer type) {

        return templateMapper.getTemplateOutputs(start,size,type);
    }

    @Override
    public Template getTemplateByUniqidAndClientId(Integer clientId, String uniqid) {

        Map<String,Object> params = new HashMap<>();
        params.put("uniqid",uniqid);
        params.put("clientId",clientId);

        return templateMapper.getTemplateByUniqidAndClientId(params);
    }

    @Override
    public Integer addTemplateCount(Integer id, Integer type) {

        return templateMapper.addTemplateCount(id,type);
    }
}
