FROM openjdk:11
EXPOSE 8089:8089
ADD target/kaddem-2.0.jar 5TWIN4-G2-kaddem-2.0.jar
ENTRYPOINT ["java", "-jar", "5TWIN4-G2-kaddem-2.0.jar"]
