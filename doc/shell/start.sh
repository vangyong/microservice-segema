#!/bin/bash
#nohup java -jar ../../microservice-system/target/microservice-system-0.0.1.jar &

nohup java -jar ../../microservice-discovery/target/microservice-discovery-0.0.1.jar --server.port=8761 &