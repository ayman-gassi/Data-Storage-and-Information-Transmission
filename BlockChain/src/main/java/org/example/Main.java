package org.example;

import org.example.models.Block;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Block> chain = new ArrayList<>();
        chain.add(new Block(0,"block1","0"));
        chain.add(new Block(1,"block2",chain.get(0).getCurrentHash()));
        chain.add(new Block(2,"block3",chain.get(1).getCurrentHash()));
        chain.add(new Block(3,"block4",chain.get(2).getCurrentHash()));
        chain.add(new Block(4,"block5",chain.get(3).getCurrentHash()));
        chain.forEach(System.out::println);
        System.out.println("Verification of the chain");
        System.out.println(Block.verifychain(chain) ? "Blockchain Valide" : "Blockchain not valid ");
        System.out.println("Verification 2 of the chain");
        chain.get(3).setPriviousHash("Hacking");
        System.out.println(Block.verifychain(chain) ? "Blockchain Valide" : "Blockchain not valid ");

    }
}