#!/bin/bash

# compile
mvn install -DskipTests

# build docker
docker build --build-arg JAR_FILE=target/order-0.0.1-SNAPSHOT.jar --tag tinnt/nab-test-order:latest .

# push docker to dockerhub
docker push tinnt/nab-test-order:latest