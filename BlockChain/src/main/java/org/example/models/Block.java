package org.example.models;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {
    private long index;
    private ArrayList<Transaction> transactions;
    private Timestamp timestamp;
    private String currentHash;
    private String priviousHash;

    public Block(long index, ArrayList<Transaction> transactions,String priviousHash) {
        this.index = index;
        this.transactions = transactions;
        this.timestamp = new Timestamp(new Date().getTime());
        this.currentHash = calculateHash();
        this.priviousHash = priviousHash;
    }
    private  String calculateHash(){
        StringBuilder data = new StringBuilder();
        transactions.forEach(e -> data.append(e.toString()));
        String result = data.toString();
        return DigestUtils.sha256Hex(index+result+timestamp.toString()+priviousHash);
    }
    public String getCurrentHash(){
        return currentHash;
    }
    public String getPriviousHash(){
        return priviousHash;
    }

    public void setPriviousHash(String priviousHash) {
        this.priviousHash = priviousHash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", data='" + transactions + '\'' +
                ", timestamp=" + timestamp +
                ", currentHash='" + currentHash + '\'' +
                ", priviousHash='" + priviousHash + '\'' +
                '}';
    }
    public static boolean verifychain(List<Block> blockchain){
        for(int i=1 ; i< blockchain.size() ; i++){
            if(blockchain.get(i).getPriviousHash().equals(blockchain.get(i-1).getCurrentHash())){
                return  true;
            }
        }
        return false;
    }
}
