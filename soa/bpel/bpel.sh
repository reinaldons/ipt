#!/bin/bash
TC_DIR=${TOMCAT_HOME:=/opt/tomcat}

if [ "$1" == "deploy" ]; then

  rm -r $TOMCAT_HOME/webapps/ode/WEB-INF/processes/bpelContent
  sleep 5
  cp -r bpelContent/ $TOMCAT_HOME/webapps/ode/WEB-INF/processes/

else

  cd bpelContent
  wget http://localhost:8181/bestdog/Estoque?wsdl -O Estoque.wsdl
  wget http://localhost:8181/bestdog/Milhagem?wsdl -O Milhagem.wsdl

fi
