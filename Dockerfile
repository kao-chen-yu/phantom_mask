FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine

VOLUME /data

ARG JAR_FILE=target/*.jar

ARG Pharmacies_FILE=data/pharmacies.json
ARG User_File=data/users.json

COPY ${JAR_FILE} app.jar
#COPY ${Pharmacies_FILE} data/pharmacies.json
#COPY ${User_File} data/users.json

ADD ${Pharmacies_FILE} data/pharmacies.json
ADD ${User_File} data/users.json


ENTRYPOINT exec java -jar /app.jar

EXPOSE 8080