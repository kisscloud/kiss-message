package com.kiss.message.dao;

import com.kiss.message.entity.Client;

import java.util.List;

public interface ClientDao {

    Integer createClient(Client client);

    Integer updateClient(Client client);

    Client getClientByUniqid(String uniqid);

    Client getClientByName(String name);

    List<Client> getClients(Integer start,Integer size);

    Client getClientById(Integer id);
}
