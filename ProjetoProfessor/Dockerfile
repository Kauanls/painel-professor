FROM eclipse-temurin:21-jdk

# Copia o Payara Micro local
COPY payara-micro-7.2025.1.jar /opt/payara-micro.jar

# Copia o WAR gerado
COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /opt/app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/payara-micro.jar", "--deploy", "/opt/app.war", "--nocluster"]
