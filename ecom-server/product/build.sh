#!/bin/bash

# compile
mvn install -DskipTests

# build docker
docker build --build-arg JAR_FILE=target/product-0.0.1-SNAPSHOT.jar --tag tinnt/nab-test-product:latest .

# push docker to dockerhub
docker push tinnt/nab-test-product:latest