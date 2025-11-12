FROM eclipse-temurin:21-jdk

COPY payara-micro-7.2025.1.jar /opt/payara-micro.jar
COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /opt/app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/payara-micro.jar", "--deploy", "/opt/app.war", "--contextRoot", "/", "--port", "8080"]
