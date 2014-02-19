1. cd ws && mvn clean install
2. cd ws && ws.sh h2
3. mvn install:install-file -Dfile=ode.war -DgroupId=local.apache -DartifactId=ode -Dversion=1.3.6 -Dpackaging=war
4. java -jar ode
5. 
