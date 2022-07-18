#FROM adoptopenjdk/openjdk:jre-11.0.13_8-alpine
FROM openjdk:17-jdk-alpine

EXPOSE 8082

COPY  ./target/Cloudstorage-0.0.1-SNAPSHOT.jar ./app.jar
COPY  ./wait-for.sh ./

CMD ["java", "-jar", "./app.jar"]


##############################

#FROM openjdk:17-jdk-alpine
# docker pull mysql/mysql-server:latest

