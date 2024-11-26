# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado pelo Maven ou Gradle
COPY target/portal-0.0.1-SNAPSHOT.jar portal.jar

# Expor a porta da aplicação
EXPOSE 8082

# Comando para rodar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]
