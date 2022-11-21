# Projeto vencedores Golden Raspberry Awards.

Requisitos para rodar o projeto:
- JDK 11 instalado e configurado no JAVA_HOME (https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html#:~:text=To%20set%20JAVA_HOME%2C%20do%20the,Program%20Files%5CJava%5Cjdk1.)
- Maven instalado e configurado no MAVEN_HOME (https://maven.apache.org/install.html)

## Rodando projeto (Executar comandos na raíz do projeto):
### Baixar dependencias 

`mvn clean install`

### Rodar testes de integração

`mvn test`

### Rodar aplicação

`java -jar .\target\quarkus-app\quarkus-run.jar`


## API endpoint:

http://localhost:8080/movies

## Atualizando CSV com a lista de ganhadores:

- Sobrescrever o arquivo movielist.csv no projeto em:

`/src/main/resources/movielist.csv`

- Refazer as estapas de instalacão e execução do projeto.


## Extensões utilizadas:

Agroal - Database connection pool
YAML Configuration
Hibernate ORM with Panache
JDBC Driver - H2
REST Client Classic Jackson
RESTEasy Classic Jackson
Undertow Servlet
Eclipse Vert.x
