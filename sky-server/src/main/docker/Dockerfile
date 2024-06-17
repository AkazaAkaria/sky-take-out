FROM java:8
VOLUME /tmp
MAINTAINER AkazaAkari
ADD target/sky-server-0.0.1-SNAPSHOT.jar sky-server.jar
ENTRYPOINT ["java","-Dport=8081","-jar","/sky-server.jar"]

