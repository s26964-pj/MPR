package com.example.mprkol;

public class Client {
    private String clientID;
    private String name;
    private int balance;

    public Client(String clientID, String name, int balance){
        this.clientID = clientID;
        this.name = name;
        this.balance = balance;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID='" + clientID + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
