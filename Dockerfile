FROM openjdk:11
EXPOSE 8089
ADD target/kaddem-4.0.jar kaddem-4.0.jar
ENTRYPOINT ["java", "-jar", "kaddem-3.0.jar"]