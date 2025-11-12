# Imagem base GlassFish com JDK 24
FROM ghcr.io/eclipse-ee4j/glassfish:7.0.25-jdk24
# Copia o WAR gerado pelo Maven para o diretório de autodeploy do GlassFish
COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /glassfish7/glassfish/domains/domain1/autodeploy/ROOT.war

# Expõe portas HTTP e admin
EXPOSE 8080 4848

# Inicia o domínio do GlassFish em modo verbose
CMD ["/glassfish7/bin/asadmin", "start-domain", "-v"]
