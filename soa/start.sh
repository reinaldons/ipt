#!/bin/bash
#1. cd ws && mvn clean install
#2. cd ws && ws.sh h2
#3. cd bpel && mvn clean package tomcat7:run
#4. mvn tomcat7:deploy -DwarFile=ode/ode-1.3.6.war

# BEGIN_JAVA_CONF
JAVA_VERSION=java-7-oracle
JAVA_DIR=/usr/lib/jvm
MVN_DIR=/opt/java/maven
# END_JAVA_CONF

SCRIPTS_PATH="$PWD/scripts"

source $SCRIPTS_PATH/_jdk.sh $JAVA_VERSION $JAVA_DIR $MVN_DIR

if [ $? -ne 0 ]; then

echo ""
echo "ERROR: Maven not found."
exit -1

fi

source $SCRIPTS_PATH/_open.sh

echo ""

echo "STARTING H2..."
open_tab h2 $SCRIPTS_PATH

echo "STARTING WS..."
open_tab ws $SCRIPTS_PATH

echo "STARTING ODE..."
open_tab ode $SCRIPTS_PATH
