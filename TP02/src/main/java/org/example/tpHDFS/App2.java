package org.example.tpHDFS;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

//  Créer un répertoire /user/hadoop/appData dans HDFS s’il n’existe pas.
public class App2 {
    public static void main(String[] args) {
        String hdfsDirPath = "/user/hadoop/appData";

        try {
            // Configuration Hadoop
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS","hdfs://namenode:8020");

            // Obtenir une instance de FileSystem
            FileSystem fs = FileSystem.get(conf);

            // Créer un objet Path pour le répertoire
            Path dirPath = new Path(hdfsDirPath);

            // Vérifier si le répertoire existe déjà
            if (!fs.exists(dirPath)) {
                // Créer le répertoire s'il n'existe pas
                fs.mkdirs(dirPath);
                System.out.println("Répertoire créé avec succès : " + hdfsDirPath);
            } else {
                System.out.println("Le répertoire existe déjà : " + hdfsDirPath);
            }

            // Fermer le FileSystem
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
