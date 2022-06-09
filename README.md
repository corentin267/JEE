# Project tp8/tp8

Steps to run this project:

1. Start your Docker daemon
2. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
3. Wait until Open Liberty is up- and running (e.g. use `docker logs -f CONTAINER_ID`)
4. Visit http://localhost:9080/resources/sample


Start project: 
mvn wildfly:run

endpoint :
http://localhost:8080/tp8/resources/drivers
http://localhost:8080/tp8/resources/drivers/add
http://localhost:8080/tp8/resources/drivers/update
http://localhost:8080/tp8/resources/drivers/delete/{id}

{
"firstname": "Lewis",
"lastname": "Hamilton"
}

http://localhost:8080/tp8/resources/users/add
{
"username": "corentin",
"password": "test"
}

bdd:
http://localhost:8888/phpMyAdmin5/index.php?route=/sql&db=jee&table=Driver&pos=0