# API para Cadastro de Empresas
## API Avaliativa para empresa Broadfactor 

## Tecnologias
- Java 11
- Spring Framework
  - core
  - data-jpa
  - webflux
  - security
- MySQL
- H2 Database
- Docker

## Build e Execução do projeto

Execute o comando na raiz do projeto

```mvn clean package```

O comando acima vai criar o seguinte .jar

```./target/cadastro-empresas-api-0.0.1-SNAPSHOT.jar```

É possível executar diretamente com o comando

```java -jar cadastro-empresas-api-0.0.1-SNAPSHOT.jar```

## Deploy

docker-compose --build up
