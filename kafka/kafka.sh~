kafka-topics --list --zookeeper localhost:2181/kafka

kafka-topics --zookeeper localhost:2181/kafka --create --topic myTopic --partitions 1 --replication-factor 1

kafka-topics --list --zookeeper master:2181/kafka

kafka-console-consumer --from-beginning --zookeeper localhost:2181/kafka --topic myTopic

kafka-console-producer --topic myTopic --broker-list localhost:9092

kafka-console-consumer --zookeeper localhost:2181/kafka --topic myTopic

kafka-topics --zookeeper master:2181/kafka --delete --topic myTopic 
