package com.kiss.message.validator;

import com.kiss.message.dao.TemplateDao;
import com.kiss.message.entity.Template;
import com.kiss.message.input.CreateTemplateInput;
import com.kiss.message.status.MessageStatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TemplateValidator implements Validator {

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private TemplateDao templateDao;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateTemplateInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateTemplateInput.class.isInstance(target)) {
            CreateTemplateInput createTemplateInput = (CreateTemplateInput) target;
            boolean clientIdVal = clientValidator.validateId(createTemplateInput.getCliendId(),"clientId",errors);

            if (clientIdVal) {
                validateUniqid(createTemplateInput.getCliendId(),createTemplateInput.getUniqid(),errors);
            }
        }
    }

    public void validateUniqid(Integer clientId,String uniqid, Errors errors) {

        if (StringUtils.isEmpty(uniqid)) {
            errors.rejectValue("uniqid",String.valueOf(MessageStatusCode.TEMPLATE_UNIQID_IS_EMPTY),"模板的标识为空");
            return;
        }

        Template template = templateDao.getTemplateByUniqidAndClientId(clientId,uniqid);

        if (template != null) {
            errors.rejectValue("uniqid",String.valueOf(MessageStatusCode.TEMPLATE_UNIQID_IS_EXIST),"模板标识已存在");
        }
    }
}
