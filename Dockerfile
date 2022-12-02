From openjdk:8-jdk-alpine
COPY covidApp/build/libs/covidApp-0.0.1-SNAPSHOT.jar covidApp/build/libs/covidApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/covidApp-0.0.1-SNAPSHOT.jar"]