#!/bin/bash

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

cd bpel

mvn package

cd ..

cd ws/bestdog

mvn package

cd ..
cd ..

source $SCRIPTS_PATH/_open.sh

echo ""

echo "STARTING H2..."
open_tab h2 $SCRIPTS_PATH

echo "STARTING WS..."
open_tab ws $SCRIPTS_PATH

echo "STARTING ODE..."
open_tab ode $SCRIPTS_PATH


#firefox -new-tab http://localhost:8181/bestdog/ &
#firefox -new-tab http://localhost:8080/bpel/ &

#/bpel/bpel.sh
