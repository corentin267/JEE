#!/bin/sh
mvn clean package && docker build -t tp8/tp8 .
docker rm -f tp8 || true && docker run -d -p 9080:9080 -p 9443:9443 --name tp8 tp8/tp8