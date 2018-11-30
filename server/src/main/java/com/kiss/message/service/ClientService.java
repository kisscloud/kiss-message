package com.kiss.message.service;

import com.kiss.message.dao.ClientDao;
import com.kiss.message.entity.Client;
import com.kiss.message.input.CreateClientInput;
import com.kiss.message.output.ClientOutput;
import com.kiss.message.status.MessageStatusCode;
import com.kiss.message.util.ResultOutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import output.ResultOutput;
import utils.BeanCopyUtil;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    public ResultOutput createClient(CreateClientInput createClientInput) {

        Client client = BeanCopyUtil.copy(createClientInput,Client.class);
        String uniqid = UUID.randomUUID().toString().replaceAll("-","");
        client.setUniqid(uniqid);
        Integer count = clientDao.createClient(client);

        if (count == 0) {
            return ResultOutputUtil.error(MessageStatusCode.CREATE_CLIENT_FAILED);
        }

        ClientOutput clientOutput = BeanCopyUtil.copy(client,ClientOutput.class,BeanCopyUtil.defaultFieldNames);

        return ResultOutputUtil.success(clientOutput);
    }

    public ResultOutput getClients(Integer page,Integer size){

        Integer start = (page - 1) * size;
        List<Client> clients = clientDao.getClients(start,size);
        List<ClientOutput> clientOutputs = BeanCopyUtil.copyList(clients,ClientOutput.class,BeanCopyUtil.defaultFieldNames);

        return ResultOutputUtil.success(clientOutputs);
    }
}
