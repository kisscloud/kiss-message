package com.kiss.message.mapper;

import com.kiss.message.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientMapper {

    Integer createClient(Client client);

    Integer updateClient(Client client);

    Client getClientByUniqid(String uniqid);

    Client getClientByName(String name);

    List<Client> getClients(@Param("page") Integer page,@Param("size") Integer size);

    Client getClientById(Integer id);
}
