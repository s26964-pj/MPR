package com.example.mprkol;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {
    private List<Client> clientList = new ArrayList<>();

    public ClientStorage(){
        clientList.add(new Client("1","Marek",20000));
        clientList.add(new Client("2","Tomek",1000));
        clientList.add(new Client("3","Jan",500000));
    }

    public void registerClient(Client client){
        clientList.add(client);
    }

    public Client findClientByID(String clientID){
        for(Client client : clientList){
            if(client.getClientID().equals(clientID)){
                return client;
            }
        }
        return null;
    }

    public List<Client> getClientList() {
        return clientList;
    }
}

