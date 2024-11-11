## Stack utilizada

- Java 21
- Spring Boot 3
- Clean Architecture
- Maven
- Postgresql
- Flyway
- Swagger
- JUnit
- Mockito
- Kafka
- Docker

## Requisitos
- Maven 3.8+
- Java 21
- Docker instalado na máquina

## Rodar a aplicação

O Docker deve estar rodando na maquina.

Clone o projeto e navegue até a pasta root do mesmo por um terminal.

Então, execute os comandos na sequencia abaixo para compilar, rodar os testes unitarios da aplicação e gerar as imagens docker:

- mvn clean
- mvn package
- docker-compose up

Caso deseje rodar apenas os testes:
- mvn test

## Acessar a API

Após subir os containers do docker, a api ficará disponivel em:
- http://localhost:8084/api/v1/

Para visualizar a documentação da api:
- http://localhost:8084/swagger-ui.html

OBS: dependendo de como esta configurado o docker, o localhost poderá ser outro IP, então para acessar a aplicação deverá ir pelo ip do docker.

#### Tarefa Bônus 1 - Integração com sistemas externos
    O serviço não foi implementado devido está fora do ar. No entanto foi criada uma classe help que realiza a validação do CPF;

#### Tarefa Bônus 2 - Mensageria e filas
    Foi utilizado o Kafka para mensageria:
    O producer ficou responsável por enviar os resultados das votações;
    O consumer ficou responsável por escutar os resultados das votações e apenas logar em console os resultados.

#### Tarefa Bônus 3 - Performance
    Foi ativada as virtuais threads do projeto Loom, que é uma nova feature do Java 21, que permite a criação de threads virtuais, que são mais leves e mais rápidas que as threads tradicionais.

#### Tarefa Bônus 4 - Versionamento da API
    Para este teste foi escolhido pela url, onde as apis foram construidas como api/v1/ .
    Surgindo a necessidade de uma nova versão, seria criado novos controllers/services e as apis do mesmo ficariam como api/v2/ .