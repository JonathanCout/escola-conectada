**Repositório Escola Conectada**

- Repositório responsável pelo armazenamento do código-fonte do projeto Escola-Conectada, realizado para a disciplina PROJETO DE EXTENSÃO EM SOFTWARE FULL STACK 2024/2.

**Tecnologias utilizadas:**

- React
- Java SpringBoot
- PostgreSQL

**Requerimentos funcionais:**

- Docker (responsável por criar todas as imagens necessárias)

**Como utilizar?**

Para rodar o projeto, é necessário clonar o repositório e, com o terminal na pasta raiz, executar o comando:

- docker-compose up --build

A partir desse comando, serão criados 3 contâiners:

- FrontEnd: Podendo ser acessado a partir da rota localhost:3000
- BackEnd: Estará disponível para chamadas na porta 8082 caso seja necessário
- PostgreSQL: Estará disponível na porta 5432

Durante o build, o sciprt 'table-script.sql' presente na pasta ./sql é executado, assegurando a criação de todas as tabelas necessárias e com os seus constraints.
