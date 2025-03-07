package org.example.tpHDFS;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.*;

public class App5 {
    public static void GetFileMetadata(String filePath, FileSystem fs) {
        try {
            // Chemin du fichier
            Path path = new Path(filePath);

            // Vérifier si le fichier existe
            if (!fs.exists(path)) {
                System.out.println("Le fichier " + filePath + " n'existe pas.");
                return;
            }

            // Récupérer les métadonnées du fichier
            FileStatus fileStatus = fs.getFileStatus(path);

            // Afficher les métadonnées
            System.out.println("Métadonnées du fichier " + filePath + " :");
            System.out.println("Chemin : " + fileStatus.getPath());
            System.out.println("Taille : " + fileStatus.getLen() + " octets");
            System.out.println("Propriétaire : " + fileStatus.getOwner());
            System.out.println("Groupe : " + fileStatus.getGroup());
            System.out.println("Permissions : " + fileStatus.getPermission());
            System.out.println("Date de modification : " + fileStatus.getModificationTime());
            System.out.println("Est un répertoire : " + fileStatus.isDirectory());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HDFSSpaceCheck(FileSystem fs) {
        try {
            // Récupérer les informations sur l'espace HDFS
            ContentSummary contentSummary = fs.getContentSummary(new Path("/"));

            // Afficher les informations sur l'espace
            System.out.println("Espace total dans HDFS : " + contentSummary.getSpaceConsumed() + " octets");
            System.out.println("Espace utilisé dans HDFS : " + contentSummary.getSpaceQuota() + " octets");
            System.out.println("Espace libre dans HDFS : "
                    + (contentSummary.getSpaceQuota() - contentSummary.getSpaceConsumed()) + " octets");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MoveFile(String sourcePath, String destinationPath, FileSystem fs) {
        try {
            // Chemins source et destination
            Path srcPath = new Path(sourcePath);
            Path destPath = new Path(destinationPath);

            // Vérifier si le fichier source existe
            if (!fs.exists(srcPath)) {
                System.out.println("Le fichier source " + sourcePath + " n'existe pas.");
                return;
            }

            // Vérifier si le répertoire de destination existe, sinon le créer
            Path destDir = new Path("/user/hadoop/archive");
            if (!fs.exists(destDir)) {
                fs.mkdirs(destDir); // Créer le répertoire de destination
                System.out.println("Répertoire de destination créé : " + destDir);
            }

            // Vérifier si le fichier de destination existe déjà
            if (fs.exists(destPath)) {
                System.out.println("Le fichier de destination " + destinationPath + " existe déjà.");
                return;
            }

            // Déplacer le fichier
            boolean isMoved = fs.rename(srcPath, destPath);
            if (isMoved) {
                System.out.println("Fichier déplacé de " + sourcePath + " vers " + destinationPath + ".");
            } else {
                System.out.println("Échec du déplacement du fichier.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Configuration Hadoop
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://namenode:8020");

        // Obtenir une instance de FileSystem
        FileSystem fs = FileSystem.get(conf);

        // -Récupérer et afficher les métadonnées du fichier
        // /user/hadoop/appData/test.txt.
        GetFileMetadata("/user/hadoop/appData/test.txt", fs);

        // -Vérifier l’espace disponible dans HDFS.
        HDFSSpaceCheck(fs);

        // - Déplacer le fichier /user/hadoop/appData/test.txt dans un sous-répertoire
        // /user/hadoop/archive/.
        String sourcePath = "/user/hadoop/appData/test.txt";
        String destinationPath = "/user/hadoop/archive/test.txt";
        MoveFile(sourcePath, destinationPath, fs);

        fs.close();
    }
}
