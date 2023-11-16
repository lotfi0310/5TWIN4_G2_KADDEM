FROM openjdk:11
EXPOSE 8089:8089
ADD target/5TWIN4-G2-kaddem-1.0.jar 5TWIN4-G2-kaddem.jar
ENTRYPOINT ["java", "-jar", "5TWIN4-G2-kaddem.jar"]
