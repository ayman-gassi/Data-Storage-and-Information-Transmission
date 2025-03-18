package org.example.models;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Block {
    private long index;
    private String data ;
    private Timestamp timestamp;
    private String currentHash;
    private String priviousHash;

    public Block(long index, String data,String priviousHash) {
        this.index = index;
        this.data = data;
        this.timestamp = new Timestamp(new Date().getTime());
        this.currentHash = calculateHash();
        this.priviousHash = priviousHash;
    }
    private  String calculateHash(){
        return DigestUtils.sha256Hex(index+data+timestamp.toString()+priviousHash);
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
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                ", currentHash='" + currentHash + '\'' +
                ", priviousHash='" + priviousHash + '\'' +
                '}';
    }
    public static boolean verifychain(List<Block> blockchain){
        for(int i=0 ; i< blockchain.size() ; i++){
            if(!blockchain.get(i).getPriviousHash().equals(blockchain.get(i).getCurrentHash())){
                return  false;
            }
        }
        return true;
    }
}
