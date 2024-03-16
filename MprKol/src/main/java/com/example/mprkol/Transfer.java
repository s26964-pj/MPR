package com.example.mprkol;

public class Transfer {
    private int newBalance;
    private TransferType transferType;

    public Transfer(int newBalance, TransferType transferType){
        this.newBalance = newBalance;
        this.transferType = transferType;
    }

    public Transfer(){

    }
    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "Balance=" + newBalance +
                ", transferType=" + transferType +
                '}';
    }
}
