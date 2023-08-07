# Chatop Rental Api

This project was generated with Spring Tool Suite version 4.19.0.RELEASE. Download link `https://spring.io/tools`

## Technologies

- Spring Boot version 3.1.1
- Java version 17
- maven version 4.0.0
- Swagger 3
- MySQL server version 8.0.33

## Dependencies

- spring boot starter data jpa
- spring boot starter security
- spring boot starter web
- springdoc openapi starter webmvc ui (version 2.1.0)
- mysql connector j
- lombok
- spring boot starter test
- spring security test
- spring boot configuration processor
- springdoc openapi ui (version 1.7.0)
- modelmapper (version 3.1.1)
- jjwt api (version 0.11.5)
- jjwt impl (version 0.11.5)
- jjwt jackson (version 0.11.5)

## MySQL Database Installation

First, install MySQL Server from `https://dev.mysql.com/downloads/installer/`, configure it and open a MySQL Command Line Client.

Then do :
- mysql> \. /Path/To/database.sql
The `database.sql` file path correspond to the project directory path.

Next, create a spring_user account to access to the database like :
- mysql> create user 'springuser'@'%' identified by 'password';
- mysql> grant all on chatop.* to 'springuser'@'%';

If you want to change springuser name and password, be carrefull to change it also in the application.properties file (spring.datasource.username and spring.datasource.password parameters).

## Development server

Run as `Spring Boot App` in the IDE for a dev server. The api tomcat server starts on `port 3001`. Access link on `http://localhost:3001/`.

## Build

- Run `mvn package` or `mvn clean package` on Command Line in the project directory to build the project (needs Maven CLI installed). 
OR
- Run as `Maven Build` in the IDE. 

The build artifacts will be stored in the `target/` directory as a .jar file.

Please, before each critical build, make sure that the api version has been changed in the pom.xml file. If you do not, the previous version will be erased.

## API Documentation

While api's running, go to `http://localhost:3001/swagger-ui/index.html` to get the html Swagger documentation for all api roots or `http://localhost:3001/v3/api-docs` to get the JSON format. No authentication needed.


