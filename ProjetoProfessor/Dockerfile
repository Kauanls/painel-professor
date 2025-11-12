# Etapa 1: usar uma imagem base com Java
FROM openjdk:17-jdk-slim

# Etapa 2: copiar o Payara Micro
ADD https://repo1.maven.org/maven2/fish/payara/distributions/payara-micro/6.2024.3/payara-micro-6.2024.3.jar /opt/payara-micro.jar

# Etapa 3: copiar o WAR
COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /opt/app.war

# Expor a porta padrão
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "/opt/payara-micro.jar", "--deploy", "/opt/app.war", "--contextRoot", "/"]
