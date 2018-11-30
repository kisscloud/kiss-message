package com.kiss.message.dao.Impl;

import com.kiss.message.dao.LocaleDao;
import com.kiss.message.entity.Locale;
import com.kiss.message.mapper.LocaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LocaleDaoImpl implements LocaleDao {

    @Autowired
    private LocaleMapper localeMapper;

    @Override
    public Integer createLocale(Locale locale) {

        return localeMapper.createLocale(locale);
    }

    @Override
    public Integer updateLocale(Locale locale) {

        return localeMapper.updateLocale(locale);
    }

    @Override
    public Locale getLocalByTemplateId(Integer templateId, String lang) {

        Map<String, Object> params = new HashMap<>();
        params.put("templateId", templateId);
        params.put("lang", lang);
        return localeMapper.getLocalByTemplateId(params);
    }
}
