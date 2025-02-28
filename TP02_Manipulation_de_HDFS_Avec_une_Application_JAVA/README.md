
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
git clone https://github.com/ayman-gassi/hadoop-labs/TP02.git
cd hadoop-labs/TP02.git
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



### Step 2: Run the Writer Application

Generate the Jar file and put it into the clusterFolder in /jars then run thos commands : 

```bash
java -cp "./jars/*:." org.example.tpHDFS.AppWriter
```
