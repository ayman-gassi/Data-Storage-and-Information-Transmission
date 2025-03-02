package org.example.tpHDFS;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;



public class App2 {
    public static void CreateDirectory(String hdfsDirPath , FileSystem fs){
        try {
            // Créer un objet Path pour le répertoire
            Path dirPath = new Path(hdfsDirPath);

            // Vérifier si le répertoire existe déjà
            if (!fs.exists(dirPath)) {
                // Créer le répertoire s'il n'existe pas
                fs.mkdirs(dirPath);
                System.out.println("Répertoire cree avec succes : " + hdfsDirPath);
            } else {
                System.out.println("Le répertoire existe déjà : " + hdfsDirPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void CreateFile(String filePath , FileSystem fs){
        try {
            // Créer un nouveau fichier sur HDFS
            Path path = new Path(filePath);
            if (fs.exists(path)) {
                System.out.println("Le fichier existe déjà.");
                return;
            }

            FSDataOutputStream outputStream = fs.create(path);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            // Écrire le texte dans le fichier
            writer.write("Its Java & HDFS");
            writer.newLine();

            // Fermer les ressources
            writer.close();
            outputStream.close();
            System.out.println("Fichier cree avec succes sur HDFS.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void ShowContext(String directoryPath ,FileSystem fs ){
        try {
            // Chemin du répertoire à lister
            Path path = new Path(directoryPath);

            // Vérifier si le répertoire existe
            if (!fs.exists(path)) {
                System.out.println("Le repertoire " + directoryPath + " n'existe pas.");
                return;
            }

            // Lister les fichiers et répertoires
            RemoteIterator<LocatedFileStatus> fileStatusIterator = fs.listFiles(path, true);

            System.out.println("Contenu du répertoire " + directoryPath + " :");
            while (fileStatusIterator.hasNext()) {
                LocatedFileStatus fileStatus = fileStatusIterator.next();
                System.out.println((fileStatus.isDirectory() ? "D" : "F") + " - " + fileStatus.getPath());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void ShowFileContent(String filePath ,FileSystem fs ){
        try {
            // Chemin du fichier à lire
            Path path = new Path(filePath);

            // Vérifier si le fichier existe
            if (!fs.exists(path)) {
                System.out.println("Le fichier " + filePath + " n'existe pas.");
                return;
            }

            // Ouvrir un flux de lecture pour le fichier
            FSDataInputStream inputStream = fs.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Lire et afficher le contenu du fichier ligne par ligne
            System.out.println("Contenu du fichier " + filePath + " :");
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

        // Créer un répertoire /user/hadoop/appData dans HDFS s’il n’existe pas.
        String hdfsDirPath = "/user/hadoop/appData";
        CreateDirectory(hdfsDirPath,fs);

        // Créer un fichier data.txt dans /user/hadoop/appData et y écrire le texte "Bienvenue sur HDFS avec Java.".
        String hdfsFilePath = "/user/hadoop/appData/data.txt";
        CreateFile(hdfsFilePath,fs);

        // Lister tous les fichiers et répertoires dans /user/hadoop/appData
        ShowContext(hdfsDirPath,fs);

        //  Lire et afficher le contenu du fichier /user/hadoop/appData/data.txt.
        ShowFileContent(hdfsFilePath,fs);

        fs.close();

    }
}
