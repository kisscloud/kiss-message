package com.kiss.message.dao.Impl;

import com.kiss.message.dao.ClientDao;
import com.kiss.message.entity.Client;
import com.kiss.message.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Integer createClient(Client client) {

        return clientMapper.createClient(client);
    }

    @Override
    public Integer updateClient(Client client) {

        return clientMapper.updateClient(client);
    }

    @Override
    public Client getClientByUniqid(String uniqid) {

        return clientMapper.getClientByUniqid(uniqid);
    }

    @Override
    public Client getClientByName(String name) {

        return clientMapper.getClientByName(name);
    }

    @Override
    public List<Client> getClients(Integer start,Integer size) {

        return clientMapper.getClients(start,size);
    }

    @Override
    public Client getClientById(Integer id) {

        return clientMapper.getClientById(id);
    }
}
