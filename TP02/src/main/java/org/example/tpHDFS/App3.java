package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class App3 {
    public static void CopyFromLocal(String localFilePath , String hdfsFilePath ,FileSystem fs){
        try {

            // Chemins source (local) et destination (HDFS)
            Path localPath = new Path(localFilePath);
            Path hdfsPath = new Path(hdfsFilePath);

            // Vérifier si le fichier local existe
            if (!fs.exists(localPath)) {
                System.out.println("Le fichier local " + localFilePath + " n'existe pas.");
                return;
            }

            // Copier le fichier local vers HDFS
            fs.copyFromLocalFile(localPath, hdfsPath);
            System.out.println("Fichier copie de " + localFilePath + " vers " + hdfsFilePath + " sur HDFS.");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void RenameFile(String sourcePath , String destinationPath ,FileSystem fs){
        try {

            // Chemins source et destination
            Path srcPath = new Path(sourcePath);
            Path destPath = new Path(destinationPath);

            // Vérifier si le fichier source existe
            if (!fs.exists(srcPath)) {
                System.out.println("Le fichier source " + sourcePath + " n'existe pas.");
                return;
            }

            // Vérifier si le fichier de destination existe déjà
            if (fs.exists(destPath)) {
                System.out.println("Le fichier de destination " + destinationPath + " existe déjà.");
                return;
            }

            // Renommer le fichier
            boolean isRenamed = fs.rename(srcPath, destPath);
            if (isRenamed) {
                System.out.println("Fichier renommé de " + sourcePath + " vers " + destinationPath + ".");
            } else {
                System.out.println("Échec du renommage du fichier.");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        // Configuration Hadoop
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://namenode:8020");

        // Obtenir une instance de FileSystem
        FileSystem fs = FileSystem.get(conf);

        // Copier un fichier local test.txt dans HDFS sous /user/hadoop/appData/test.txt.
        String localFilePath = "TP02/test.txt";
        String hdfsFilePath = "/user/hadoop/appData/test.txt";
        CopyFromLocal(localFilePath,hdfsFilePath,fs);

        // Renommer le fichier /user/hadoop/appData/data.txt en /user/hadoop/appData/data_v1.txt.
        String newName = "/user/hadoop/appData/data_v1.txt";
        RenameFile(hdfsFilePath,newName,fs);



        fs.close();
    }
}
