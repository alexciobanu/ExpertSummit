# ExpertSummit

In this repository you will find skeletal code used to demonstrate Near Real Time data processing using Flume, Spark Streaming and Apache Kafka

There were several examples presented during the session and here you will find the instructions for running each of the examples.

Most of the examples below will use the notation T1, T2, T3. This refers to different terminal windows, and the command to run in each. For example, if you see

T1
    *command 1*

T2
    *command 2*

That translates to: Run *command 1* on terminal 1, then run *command 2* in terminal 2. These should be distinct bash terminal windows open on the Cloudera quickstart VM downloadable here. 

1. Data Generation

T1)
    nc -lk 12345
T2) 
    cd ~/ExpertSummit/generator
    ./dataGen.py


This will run the data generator  in T1 and display the results in T2.

2. Simple flume Channel

T1) 
    cd ~/ExpertSummit/flume
    flume-ng agent -n agent1 -f simple.conf --name a1
T2) 
    cd ~/ExpertSummit/generator
    ./dataGen.py

3. Complex flume Example

T1)
    cd ~/ExpertSummit/flume
    flume-ng agent -n agent1 -f complex.conf --name a1
T2)
    cd ~/ExpertSummit/generator
    ./dataGen.py

4. Simple Kafka Commands

T1) 
    kafka-topics --list --zookeeper quickstart:2181/kafka
    kafka-topics --zookeeper quickstart:2181/kafka --create --topic myTopic --partitions 1 --replication-factor 1
    kafka-topics --list --zookeeper quickstart:2181/kafka

5. Kafka consumer producer example

T1) 
    kafka-console-producer --topic myTopic --broker-list quickstart:9092
    <type arbitrary info in terminal>
T2) 
    kafka-console-consumer --zookeeper quickstart:2181/kafka --topic myTopic

6. Kafka cleanup

T1) 
    kafka-console-consumer --from-beginning --zookeeper quickstart:2181/kafka --topic myTopic
    kafka-topics --zookeeper quickstart:2181/kafka --delete --topic myTopic 


7. Flume and Kafka Example

T1)
    cd ~/ExpertSummit/flume 
    flume-ng agent -n agent1 -f kafka.conf --name a1
T2) 
    cd ~/ExpertSummit/generator
    ./dataGen.py
T3) 
    kafka-console-consumer --from-beginning --zookeeper quickstart:2181/kafka --topic mychannel

8. Spark Example

T4) 
    cd ~/ExpertSummit/Streeming 
    mvn clean package
    spark-submit --master yarn-client --class simpleSpark sparkStreeming-1.0.jar

9) Spark Streaming Example
 
T1)
    cd ~/ExpertSummit/flume 
    flume-ng agent -n agent1 -f kafka.conf --name a1
T3) 
    kafka-console-consumer --from-beginning --zookeeper quickstart:2181/kafka --topic mychannel
T4)
    cd ~/ExpertSummit/Streeming
    spark-submit --master yarn-client --class sparkStreeming sparkStreeming-1.0.jar"
T2) 
    cd ~/ExpertSummit/generator
    ./dataGen.py


When running the final example, if you would like to reduce the clutter on the terminal window, you can change the spark output to only present warning and not informational data.

You can edit the file /etc/spark/conf log4j.properties, and change the log4j property from INFO to WARN. More info here. 

   https://community.cloudera.com/t5/Advanced-Analytics-Apache-Spark/Logging-level-in-Spark/td-p/21947/page/2




