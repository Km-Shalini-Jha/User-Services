FROM openjdk:11-jre
EXPOSE 9090
ADD target/UserServices-0.0.1-SNAPSHOT.jar UserServices-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/UserServices-0.0.1-SNAPSHOT.jar"]