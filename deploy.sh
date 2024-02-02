#! /bin/bash
source ~/bash_profile
source /etc/profile
#docker rm -f jenkins-demo
#docker run --name jenkins-demo -d -p 80:80 -v /usr/local/jars:/usr/local/jars openjdk:11-jdk java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar /usr/local/jars/jenkins-demo-1.0.0.jar
java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar /opt/sky-server-1.0-SNAPSHOT.jar