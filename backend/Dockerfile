# Etapa 1: Imagem para Build (Maven + JDK 17)
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Diretório de trabalho no container
WORKDIR /app

# Copiar os arquivos necessários para o Maven resolver dependências
COPY pom.xml ./
COPY src ./src

# Executar o build do projeto (gera o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem para Execução (Somente JDK)
FROM openjdk:17-jdk-slim

# Diretório de trabalho no container
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta da aplicação
EXPOSE 8082

# Comando para rodar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]