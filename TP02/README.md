
```markdown
# TP02 Manipulation de HDFS Avec une Application JAVA

This repository demonstrates how to interact with Hadoop Distributed File System (HDFS) using Java applications. The project includes:

- **App1**: Reads a file from HDFS
- **AppWriter**: Writes data to a file in HDFS
- **Docker-Compose Setup**: Deploys an HDFS cluster with a NameNode and multiple DataNodes

## Prerequisites

Ensure you have the following installed:

- Docker & Docker-Compose
- Java Development Kit (JDK) 8+
- Apache Hadoop JARs (included in `./jars` directory)
- Git (optional, for cloning this repository)

## 1. Setting up the HDFS Cluster

### Step 1: Clone the Repository

```bash
git clone https://github.com/ayman-gassi/hadoop-labs/.git
cd <repository_name>
```

### Step 2: Start the HDFS Cluster (cd HDFSCLuster)

```bash
docker-compose up -d
```

This command launches:

- A NameNode (managing file system metadata)
- Five DataNodes (storing file blocks)
- A ResourceManager (for YARN jobs)
- A NodeManager (for managing containerized applications)

### Step 3: Verify the Cluster Status

Check if all services are running:

```bash
docker ps
```

Access the HDFS web UI: [http://localhost:9870](http://localhost:9870)

## 2. Interacting with HDFS

### Creating Directories and Files

```bash
# Create a new directory in HDFS
hdfs dfs -mkdir /user/hadoop

# Create an empty file
hdfs dfs -touchz /file.txt
```

### Uploading a File

```bash
hdfs dfs -put localfile.txt /file.txt
```

### Listing Files

```bash
hdfs dfs -ls /
```

### Reading a File

```bash
hdfs dfs -cat /file.txt
```

## 3. Running the Java Applications

### Step 1: Compile the Java Code

Ensure your Hadoop JARs are available in the classpath.

```bash
javac -cp "./jars/*" -d . src/org/example/tpHDFS/App1.java
javac -cp "./jars/*" -d . src/org/example/tpHDFS/AppWriter.java
```

### Step 2: Run the Writer Application

This will create a new file (`file2.txt`) in HDFS and write data into it.

```bash
java -cp "./jars/*:." org.example.tpHDFS.AppWriter
```

Verify the file content:

```bash
hdfs dfs -cat /file2.txt
```

### Step 3: Run the Reader Application

This will read and print the content of `/file.txt`.

```bash
java -cp "./jars/*:." org.example.tpHDFS.App1
```

## 4. Stopping the Cluster

```bash
docker-compose down
```

## 5. Troubleshooting

### Check Hadoop Logs

```bash
docker logs namenode
```

### Check Running Containers

```bash
docker ps
```

### Restart a Container

```bash
docker restart namenode
```

## Resources

- [Hadoop Official Documentation](https://hadoop.apache.org/docs/current/)
- [HDFS Commands](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-hdfs/HDFSCommands.html)

Enjoy experimenting with HDFS! 🚀
```

N'oubliez pas de remplacer `<repository_url>` et `<repository_name>` par les valeurs appropriées pour votre projet. Ce fichier `README.md` fournit une documentation claire et complète pour les utilisateurs de votre repository.