# Usar uma imagem base do JDK
FROM openjdk:17-jdk-slim AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar arquivos de configuração do Maven ou Gradle (se necessário)
COPY pom.xml .  
# Para Maven
# ou
# COPY build.gradle .  # Para Gradle

# Copiar o restante dos arquivos de código
COPY src ./src

# Construir o JAR (ajuste o comando de acordo com o seu sistema de build)
RUN ./mvnw package -DskipTests  # Para Maven
# ou
# RUN ./gradlew build -x test  # Para Gradle

# Usar uma imagem base do JDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado pelo Maven ou Gradle
COPY --from=build /app/target/app.jar ./app.jar  
# Para Maven
# ou
# COPY --from=build /app/build/libs/app.jar ./app.jar  # Para Gradle

# Expor a porta da aplicação
EXPOSE 8082

# Comando para rodar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]
