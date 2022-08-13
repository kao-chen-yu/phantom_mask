FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine

ENV JAVA_OPTS="-server -XX:+UseG1GC -verbose:gc -Xlog:gc:stdout -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=90 -XX:MinRAMPercentage=50"
VOLUME /data

ARG JAR_FILE=target/*.jar

ARG Pharmacies_FILE=data/pharmacies.json
ARG User_File=data/users.json

COPY ${JAR_FILE} app.jar
COPY ${Pharmacies_FILE} data/pharmacies.json
COPY ${User_File} data/users.json

ADD ${Pharmacies_FILE} data/pharmacies.json
ADD ${User_File} data/users.json

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar