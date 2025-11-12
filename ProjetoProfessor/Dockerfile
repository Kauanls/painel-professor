FROM ghcr.io/eclipse-ee4j/glassfish:7.0.25-jdk24

COPY target/ProjetoProfessor-1.0-SNAPSHOT.war /glassfish7/glassfish/domains/domain1/autodeploy/ProjetoProfessor.war

EXPOSE 8080 4848

CMD ["/glassfish7/bin/asadmin", "start-domain", "-v"]

