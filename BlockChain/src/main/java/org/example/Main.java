package org.example;

import org.example.models.Block;
import org.example.models.Transaction;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Block> chain = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Ayman","Othmane",2000));
        chain.add(new Block(0,transactions,"0"));
        transactions.add(new Transaction("Ayman","Ahmed",5000));
        chain.add(new Block(1,transactions,chain.get(0).getCurrentHash()));
        transactions.add(new Transaction("Ayman","Sami",10));
        chain.add(new Block(2,transactions,chain.get(1).getCurrentHash()));
        transactions.add(new Transaction("Ayman","Oussama",10000000));
        chain.add(new Block(3,transactions,chain.get(2).getCurrentHash()));
        chain.forEach(System.out::println);
        System.out.println("Verification of the chain");
        System.out.println(Block.verifychain(chain) ? "Blockchain Valide" : "Blockchain not valid ");
    //        System.out.println("Verification 2 of the chain");
    //      chain.get(3).setPriviousHash("Hacking");
    //    System.out.println(Block.verifychain(chain) ? "Blockchain Valide" : "Blockchain not valid ");

    }
}