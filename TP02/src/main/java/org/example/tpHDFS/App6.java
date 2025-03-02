package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class App6 {
    public static void CreateCSV(String hdfsFilePath , FileSystem fs){
        try {
            // Chemin du fichier CSV
            Path path = new Path(hdfsFilePath);

            // Vérifier si le fichier existe déjà
            if (fs.exists(path)) {
                System.out.println("Le fichier " + hdfsFilePath + " existe déjà.");
                return;
            }

            // Ouvrir un flux de sortie pour écrire dans le fichier CSV
            FSDataOutputStream outputStream = fs.create(path);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            // Données des produits (ID, Nom, Prix)
            String[] products = {
                    "1,Hoodie,200",
                    "2,T-shirt ,100",
                    "3,Phone Case ,70",
                    "4,Stickers ,49.99"
            };

            // Écrire l'en-tête du fichier CSV
            writer.write("ID,Nom,Prix");
            writer.newLine();

            // Écrire les données des produits dans le fichier CSV
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }

            // Fermer les ressources
            writer.close();
            outputStream.close();

            System.out.println("Fichier CSV créé avec succès dans HDFS : " + hdfsFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void ReadCSV(String hdfsFilePath,FileSystem fs){
        try {
            // Chemin du fichier CSV
            Path path = new Path(hdfsFilePath);

            // Vérifier si le fichier existe
            if (!fs.exists(path)) {
                System.out.println("Le fichier " + hdfsFilePath + " n'existe pas.");
                return;
            }

            // Ouvrir un flux d'entrée pour lire le fichier CSV
            FSDataInputStream inputStream = fs.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Lire et afficher le contenu du fichier ligne par ligne
            System.out.println("Contenu du fichier CSV " + hdfsFilePath + " :");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Fermer les ressources
            reader.close();
            inputStream.close();

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

        // Écrire un fichier CSV contenant une liste de produits (ID, Nom, Prix) dans HDFS sous /user/hadoop/appData/products.csv.
        CreateCSV("/user/hadoop/appData/products.csv",fs);

        // Lire le fichier CSV /user/hadoop/appData/products.csv depuis HDFS et afficher son contenu ligne par ligne.
        ReadCSV("/user/hadoop/appData/products.csv",fs);


        fs.close();
    }
}
