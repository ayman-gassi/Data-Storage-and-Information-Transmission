package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class App4 {
    public static void DeleteFile(String filePath, FileSystem fs){
        try {
            // Chemin du fichier à supprimer
            Path path = new Path(filePath);

            // Vérifier si le fichier existe
            if (!fs.exists(path)) {
                System.out.println("Le fichier " + filePath + " n'existe pas.");
                return;
            }

            // Supprimer le fichier
            boolean isDeleted = fs.delete(path, false); // false signifie ne pas supprimer les répertoires récursivement
            if (isDeleted) {
                System.out.println("Fichier " + filePath + " supprimé avec succès.");
            } else {
                System.out.println("Échec de la suppression du fichier " + filePath + ".");
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


        // Supprimer le fichier /user/hadoop/appData/data_v1.txt dans HDFS.
        String fileName = "/user/hadoop/appData/data_v1.txt";
        DeleteFile(fileName,fs);


        fs.close();
    }
}
