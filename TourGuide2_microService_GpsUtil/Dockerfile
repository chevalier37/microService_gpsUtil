FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
# Faire d'abord : mvn package pour créer le jar, puis mettre le nom du jar ici
ARG JAR_FILE=target/TourGuide2_microService_GpsUtil-0.0.1-SNAPSHOT.jar
#ARG GPSUTIL=libs/gpsUtil.jar

#ENV PORT 9001
#EXPOSE 9001

# cd /opt/app
WORKDIR /opt/app


# cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${GPSUTIL} /libs/gpsUtil.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]


#FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
#MAINTAINER JB ROUSSAT
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn package
#FROM openjdk:8-jre-alpine
#WORKDIR /app
#COPY --from=MAVEN_BUILD /build/target/TourGuide2_microService_GpsUtil-0.0.1-SNAPSHOT.jar /app/
#ENTRYPOINT ["java", "-jar", "TourGuide2_microService_GpsUtil-0.0.1-SNAPSHOT.jar"]