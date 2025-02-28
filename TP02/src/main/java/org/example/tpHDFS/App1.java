package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class App1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://namenode:8020");
//        pour ettablir la connection avec le namenode
        FileSystem fs = FileSystem.get(conf);
//        read file from hdfs
        Path path = new Path("/file.txt");
//        lire le fichier octet par octet
        FSDataInputStream fsdis = fs.open(path);
//        lire line par line (BufferedReader)
//        lire char par char (InputStreamReader)
        BufferedReader br = new BufferedReader(new InputStreamReader(fsdis));
        String line = null;
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        fs.close();
    }
}
