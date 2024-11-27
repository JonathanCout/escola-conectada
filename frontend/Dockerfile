# Usar a imagem do Node.js como base
FROM node:16

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de package.json e package-lock.json
COPY package*.json ./

# Instalar as dependências
RUN npm install

# Copiar o restante dos arquivos
COPY . .

# Construir a aplicação para produção
RUN npm run build

# Instalar o servidor http para servir a aplicação
RUN npm install -g serve

# Expor a porta que a aplicação irá rodar
EXPOSE 3000

# Comando para executar a aplicação
CMD ["serve", "-s", "build"]