FROM openjdk:11
EXPOSE 8089:8089
ADD target/5TWIN4-G2-kaddem-0.0.1-SNAPSHOT.jar 5TWIN4-G2-kaddem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "5TWIN4-G2-kaddem-0.0.1-SNAPSHOT.jar"]
