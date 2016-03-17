#!/bin/sh

mvn clean package

if [ $? -eq 0 ]; then 
   scp target/sparkStreeming-1.0.jar cloudera@quickstart:
   ssh cloudera@quickstart "spark-submit --master yarn-master --class sparkTest sparkStreeming-1.0.jar"
fi
