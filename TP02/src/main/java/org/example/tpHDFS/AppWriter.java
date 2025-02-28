package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class AppWriter {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://namenode:8020");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/file2.txt");
        FSDataOutputStream fsdos = fs.create(path);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fsdos));
        bw.write("hello mm \n");

        bw.close();
        fs.close();
    }
}
