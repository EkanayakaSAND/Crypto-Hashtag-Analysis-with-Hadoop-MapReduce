# 🚀 Crypto Hashtag Analysis with Hadoop MapReduce

Analyze trending hashtags from crypto-related tweets using Hadoop MapReduce.

---

## 📘 Assignment 1 – Cloud Computing (EC7205)

**Faculty of Engineering, University of Ruhuna**  
**Semester:** 7 (May 2025)  
**Deadline:** 🗓 07th June 2025  
**Team Size:** 👥 3 Members  

---

## 📊 Overview

This project uses Hadoop MapReduce to process and analyze a large-scale dataset of tweets related to cryptocurrencies.

- 📂 **Dataset**: [Crypto Tweets: 80K in ENG Aug 2022](https://www.kaggle.com/datasets/tleonel/crypto-tweets-80k-in-eng-aug-2022)
- 🎯 **Goal**: Identify popular hashtags from the crypto community during August 2022.
- 🛠️ **Tools**: Java, Hadoop, Maven, HDFS

---

## 🧾 Dataset Details

| Feature    | Description |
|------------|-------------|
| **Source** | Kaggle |
| **Name**   | Crypto Tweets: 80K in ENG Aug 2022 |
| **Type**   | CSV |
| **Fields** | tweet_id, timestamp, text, user_id, username, hashtags, etc. |
| **Size**   | 80,000 Tweets |

---

## ✅ Task Description

### 📌 Hashtag Popularity Analysis

Extract hashtags from tweets and calculate their frequency to identify trending topics and coins.

---

## 🖥️ Environment Setup

### ✅ Prerequisites

- Java (JDK 8+)
- Apache Hadoop (2.x or 3.x)
- Python (optional for preprocessing)
- Git

---

## ⚙️ Hadoop Setup (Standalone Mode)

1. 📥 Download Hadoop
2. 🛠 Configure:
    - `core-site.xml`
    - `hdfs-site.xml`
    - `mapred-site.xml`

3. 🧹 Format HDFS:

    ```bash
    hdfs namenode -format
    ```
4. ▶ Start Hadoop services:
    ```
    start-dfs.sh
    start-yarn.sh
    ```
5. ✅  Verify with:
    ```
    jps
    ```

---

## 🚀 How to Run

### 1. Extract Hashtags from CSV

```bash
javac ExtractHashtagsFromCSV.java
java ExtractHashtagsFromCSV
```
This will create a file: hashtags.txt

### 2. Prepare HDFS Input

```bash
hadoop fs -mkdir -p /crypto/input
hadoop fs -put ./input/extracted_hashtags.txt /crypto/input/
```

### 3. Build the JAR File

```bash
mvn clean package
```

### 4. Run the MapReduce Job

```bash
hadoop jar target/analysis-1.0-SNAPSHOT-jar-with-dependencies.jar \
  com.cryptotweets.HashtagAnalysis /crypto/input /crypto/output
```

### 5. View Output

```bash
hadoop fs -cat /crypto/output/part-r-00000
```
### 🧠 Output Sample

```
#bitcoin	112
#nft	    89
#ethereum	75
#crypto	    65
```

## 🧑‍💻 Contributors
👨‍🎓 Member 1 : [Ekanayaka S.A.N.D.](https://github.com/EkanayakaSAND/) 

👩‍🎓 Member 2 : [Senevirathna A.C.](https://github.com/ACSENEVIRATHNA)

👨‍🎓 Member 3 : [Rathnayake R.M.K.N.](#)


