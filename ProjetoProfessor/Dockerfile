FROM eclipse-temurin:21-jdk

# Baixa o Payara Micro durante o build
RUN curl -L -o /opt/payara-micro.jar https://repo1.maven.org/maven2/fish/payara/distributions/payara-micro/7.2025.1/payara-micro-7.2025.1.jar

# Copia o .war gerado pelo Maven para dentro do container
COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /opt/app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/payara-micro.jar", "--deploy", "/opt/app.war", "--nocluster"]
