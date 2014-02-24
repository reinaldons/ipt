#/bin/bash
if [ -d /tmp/soapclient ]; then
echo "soapclient already installed"
else
cd /tmp
mkdir soapclient
cd soapclient
wget http://demo.360works.com/wsm/SOAPClient.zip
unzip SOAPClient.zip
rm SOAPClient.zip
fi
cd /tmp/soapclient/build
java -Djava.home=/usr/lib/jvm/java-7-oracle/jre/ -cp *.jar -jar SOAPClient.jar &
