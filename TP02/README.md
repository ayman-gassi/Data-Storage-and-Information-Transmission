# TP02 Manipulation de HDFS Avec une Application JAVA

This repository demonstrates how to interact with Hadoop Distributed File System (HDFS) using Java applications. The project includes:

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
git clone https://github.com/ayman-gassi/hadoop-labs/TP02.git
cd hadoop-labs/TP02
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

## 3. Running the Java Applications

This will Run Every App java file.

```bash
cd jars
java -jar App0.jar
java -jar App1.jar
java -jar App2.jar
java -jar App3.jar
java -jar App4.jar
java -jar App5.jar
java -jar App6.jar
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

