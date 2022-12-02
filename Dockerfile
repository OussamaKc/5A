# temp container to build using gradle
FROM gradle:5.3.0-jdk-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    
# actual container
FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=pokerstats-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
From openjdk:8-jdk-alpine
COPY covidApp/build/libs/covidApp-0.0.1-SNAPSHOT.jar covidApp/build/libs/covidApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/covidApp-0.0.1-SNAPSHOT.jar"]