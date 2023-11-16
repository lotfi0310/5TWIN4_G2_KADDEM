FROM openjdk:11
EXPOSE 8089
COPY target/5TWIN4-ADEOS-Kaddem-2.0.jar 5TWIN4-ADEOS-Kaddem.jar
ENTRYPOINT ["java", "-jar", "5TWIN4-ADEOS-Kaddem.jar"]
