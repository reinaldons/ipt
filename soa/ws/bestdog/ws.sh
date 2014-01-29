#!/bin/bash
if [ "$1" == "h2" ]; then
   H2_DIR=target/bestdog.ws-1.0-SNAPSHOT/WEB-INF/lib/
   java -cp $H2_DIR/h2*.jar org.h2.tools.RunScript -url jdbc:h2:~/test -script src/test/resources/db.sql
   java -cp $H2_DIR/h2*.jar org.h2.tools.Server
else
   mvn clean package tomcat7:run-war
fi
