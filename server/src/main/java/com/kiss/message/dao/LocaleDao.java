package com.kiss.message.dao;

import com.kiss.message.entity.Locale;

public interface LocaleDao {

    Integer createLocale(Locale locale);

    Integer updateLocale(Locale locale);

    Locale getLocalByTemplateId(Integer templateId,String lang);
}
