# Etapa 1: Imagem para Build (Contém Maven e JDK 17)
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Diretório de trabalho no container
WORKDIR /app

# Copiar apenas os arquivos necessários para o Maven resolver dependências
COPY pom.xml ./
COPY src ./src

# Executar o build do projeto (gera o JAR)
RUN mvn clean package -DskipTests

# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado pelo Maven ou Gradle
COPY target/portal-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação
EXPOSE 8082

# Comando para rodar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]
