# Project tp8/tp8

Configuration du datasource : 
- Ajouter dans les dépendances du pom.xml:
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.29</version>
  </dependency>
  
- Ajouter dans les plugins du pom.xml:
  <plugin>
      <groupId>org.wildfly.plugins</groupId>
      <artifactId>wildfly-maven-plugin</artifactId>
      <version>3.0.0.Final</version>
      <configuration>
          <add-user>
              <users>
                  <user>
                      <username>admin</username>
                      <password>admin</password>
                  </user>
              </users>
          </add-user>
      </configuration>
  </plugin>
- http://localhost:9990/console/index.html
- se connecter avec les identifiants préciser dans le pom.xml pour le plugin wildfly
- Aller dans configuration
- Subsystems
- Datasources et drivers
- jdbc drivers
- vérifier que le driver mysql est présent

Start project: 
mvn wildfly:run

# Endpoints :
https://documenter.getpostman.com/view/7655286/UzBtm3yq

#BDD
bdd:
http://localhost:8888/phpMyAdmin5/index.php?route=/sql&db=jee&table=Driver&pos=0