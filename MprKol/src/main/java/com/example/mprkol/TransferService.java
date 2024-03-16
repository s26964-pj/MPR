package com.example.mprkol;

import org.springframework.stereotype.Component;

@Component
public class TransferService {
    private final ClientStorage clientStorage;
    public TransferService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Transfer sendMoney(String clientID, int amount){
        Transfer transfer = new Transfer();
        Client client = clientStorage.findClientByID(clientID);

        if(client == null){
            System.out.println("Client does not exist");
            return null;
        }

        int newBalance = client.getBalance() - amount;

        if(newBalance < 0 ){
            System.out.println("Your balance is to low!");
            transfer.setNewBalance(client.getBalance());
            transfer.setTransferType(TransferType.DECLINED);
        }
        else{
            transfer.setNewBalance(newBalance);
            transfer.setTransferType(TransferType.ACCEPTED);
            client.setBalance(newBalance);
        }

        return transfer;
    }

    public Transfer addMoney(String clientID, int amount){
        int newBalance;
        Transfer transfer = new Transfer();
        Client client = clientStorage.findClientByID(clientID);
        newBalance = client.getBalance() + amount;

        if(amount < 0){
            System.out.println("Negative value!");
            transfer.setNewBalance(client.getBalance());
            transfer.setTransferType(TransferType.DECLINED);
        }
        else{
            transfer.setNewBalance(newBalance);
            transfer.setTransferType(TransferType.ACCEPTED);
            client.setBalance(newBalance);
        }

        return transfer;
    }
}
