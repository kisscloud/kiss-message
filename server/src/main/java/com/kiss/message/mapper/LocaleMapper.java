package com.kiss.message.mapper;

import com.kiss.message.entity.Locale;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LocaleMapper {

    Integer createLocale(Locale locale);

    Integer updateLocale(Locale locale);

    Locale getLocalByTemplateId(Map params);
}
