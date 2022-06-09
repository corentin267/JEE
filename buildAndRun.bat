@echo off
call mvn clean package
call docker build -t tp8/tp8 .
call docker rm -f tp8
call docker run -d -p 9080:9080 -p 9443:9443 --name tp8 tp8/tp8