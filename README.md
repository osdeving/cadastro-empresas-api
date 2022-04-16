# API para Cadastro de Empresas
## API Avaliativa para empresa Broadfactor 

Esse projeto foi criado para um processo seletiovo/avaliativo. Consiste de uma API para cadastro de empresas com busca de informações extras via web service externo.

### DER da Aplicação

![der](https://github.com/osdeving/cadastro-empresas-api/blob/dfe260b632a7d7fd8a1ce4f0db4c4a03af1c362b/src/main/resources/doc/diagrama-er-cadempresas.png)

### Log de commits

![git log](https://github.com/osdeving/cadastro-empresas-api/blob/dfe260b632a7d7fd8a1ce4f0db4c4a03af1c362b/src/main/resources/doc/git-log.jpg)

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

## Deploy do MySQL

### Rodando um conteiner mysql 

```docker run --name=mysql1 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql/mysql-server:8.0```

### Acessando o conteiner para configurar o mysql

```docker exec -it mysql1 mysql -uroot -p```

### Configurando mysql

```
mysql> create user 'admin'@'localhost' identified by '123456';
Query OK, 0 rows affected (0.03 sec)

mysql> grant all privileges on *.* to 'admin'@'localhost' with grant option;
Query OK, 0 rows affected, 1 warning (0.02 sec)

mysql> create user 'admin'@'%' identified by '123456';
Query OK, 0 rows affected (0.03 sec)

mysql> grant all privileges on *.* to 'admin'@'%' with grant option;
Query OK, 0 rows affected (0.03 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.02 sec)

mysql> update mysql.user set host='%' where user='root';
Query OK, 1 row affected (0.03 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

## Deploy da API

Criando uma imagem docker da API. 

```docker build -t cadastro-empresas-api:test .```

Agora liste as imagens com

```docker image list```

Deverá aparecer na lista a imagem recém criada:

```cadastro-empresas-api:test```

Essa imagem pode ser executada em um container com o comando

```docker run -p 8080:8080 cadastro-empresas-api:test```

## Testando

Com a imagem rodando, podemos chamar os endpoints da aplicação, p.ex.:

### Criar um novo cadastro

Não é necessário passar um token JWT para cadastrar

```
curl --location --request POST 'http://localhost:8080/usuarios/cadastrar' --header 'Content-Type: application/json' --data-raw 
'{
 "nome": "Willams S. de Sousa",
"email": "novo@email.com",
"cnpj": "13995981000104",
"senha": "123456"
}'
```
### Fazer login

Precisamos usar o email do usuário cadastrado para obter um token JWT e chamar outros endpoints

```
curl --location --request POST 'http://localhost:8080/login' --header 'Content-Type: application/json' --data-raw 
'{
    "login":"novo@email.com",
    "password":"123456"
}'
```
### Consultar

A consulta retorna os dados apenas para o usuário logado, isto é, o usuário a qual o token pertence

```
curl --location --request GET 'http://localhost:8080/usuarios/empresa' --header 'Authorization: Bearer token_obtido_no_login'
```

### Atualizar

A atualização é aplicada nos dados apenas para o usuário logado, isto é, o usuário a qual o token pertence

```
curl --location --request PUT 'http://localhost:8080/usuarios/empresa' --header 'Authorization: Bearer token_obtido_no_login' --header 'Content-Type: application/json' --data-raw 
'{
  "nome": "Novo nome de usuário",
  "senha": "nova senha",
  "empresa": {
    "nome": "Novo nome da empresa",
    
    "contato" : {
      "telefone": "novo telefone"
    },
    
    "endereco" : {
       "bairro": "novo bairro"
    }
  }
}'
```


## Considerações e Melhorias

Os passos para criação da API estão mais ou menos expressos com o git log, pois foi usado gitflow. Não deu tempo para um MVP decente, 
então segue algumas considerações e melhorias:

- adicionar testes de unidade e de integração
- adicionar logs
- fazer deploy do mysql junto com com a aplicação via docker-compose
- configurar profile de produção com propriedades encriptadas com jasypt
- gerar documentação do swagger
- implementar a camada transversal de segurança e/ou log com AOP
- criar consultas separadas para as tabelas de contato, endereco, empresa, usuário
- monitoramento 
- etc...
