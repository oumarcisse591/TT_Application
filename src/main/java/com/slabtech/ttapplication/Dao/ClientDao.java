package com.slabtech.ttapplication.Dao;

import com.slabtech.ttapplication.Entity.Client;

import java.util.List;

public interface ClientDao {

    List<Client> findAllClient();

    Client findFirstByOrderByUdDesc();
    Client findByClientName(String userName);

    Long getClientCount();

    Client findClientById(int theId);

    void deleteClientById(int theId);

    Client saveClient(Client theClient);
}
