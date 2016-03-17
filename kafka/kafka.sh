    T1) kafka-topics --list --zookeeper quickstart:2181/kafka
        kafka-topics --zookeeper quickstart:2181/kafka --create --topic myTopic --partitions 1 --replication-factor 1
        kafka-topics --list --zookeeper quickstart:2181/kafka

    T1) kafka-console-producer --topic myTopic --broker-list quickstart:9092
    T2) kafka-console-consumer --zookeeper quickstart:2181/kafka --topic myTopic

    T1) kafka-console-consumer --from-beginning --zookeeper quickstart:2181/kafka --topic myTopic
        kafka-topics --zookeeper quickstart:2181/kafka --delete --topic myTopic
