package com.kiss.message.mapper;

import com.kiss.message.entity.Template;
import com.kiss.message.output.TemplateOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TemplateMapper {

    Integer createTemplate(Template template);

    Integer updateTemplate(Template template);

    Template getTemplateByUniqid(String uniqid);

    List<TemplateOutput> getTemplateOutputs(@Param("start") Integer start, @Param("size") Integer size,@Param("type") Integer type);

    Template getTemplateByUniqidAndClientId(Map params);

    Integer addTemplateCount(@Param("id") Integer id,@Param("type") Integer type);
}
