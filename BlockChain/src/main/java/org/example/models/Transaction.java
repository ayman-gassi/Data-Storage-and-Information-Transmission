package org.example.models;

public class Transaction {
    private String sender;
    private String receiver;
    private double value;

    public Transaction(String sender, String receiver, double value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", value=" + value +
                '}';
    }
}
