package com.kiss.message.validator;

import com.kiss.message.dao.ClientDao;
import com.kiss.message.entity.Client;
import com.kiss.message.input.CreateClientInput;
import com.kiss.message.status.MessageStatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private ClientDao clientDao;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateClientInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateClientInput.class.isInstance(target)) {
            CreateClientInput createClientInput = (CreateClientInput) target;
            validateName(createClientInput.getName(),errors);
            validateStatus(createClientInput.getStatus(),errors);
        }
    }

    public void validateName(String name,Errors errors) {

        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name",String.valueOf(MessageStatusCode.CLIENT_NAME_IS_EMPTY),"客户端名称为空");
            return;
        }

        Client client = clientDao.getClientByName(name);

        if (client != null) {
            errors.rejectValue("name",String.valueOf(MessageStatusCode.CLIENT_NAME_IS_EXIST),"客户端名称已存在");
        }
    }

    public void validateStatus(Integer status,Errors errors) {

        if (status == null) {
            errors.rejectValue("status",String.valueOf(MessageStatusCode.CLIENT_STATUS_IS_EMPTY),"客户端状态为空");
        }
    }

    public boolean validateId(Integer id,String idName,Errors errors) {

        if (id ==  null) {
            errors.rejectValue(idName,String.valueOf(MessageStatusCode.CLIENT_ID_IS_EMPTY),"客户端id为空");
            return false;
        }

        Client client = clientDao.getClientById(id);

        if (client == null) {
            errors.rejectValue(idName,String.valueOf(MessageStatusCode.CLIENT_NOT_EXIST),"客户端不存在");
            return false;
        }

        return true;
    }
}
