# React Spring example
Origin: https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot

This is an example project where group, event and user data is stored to database (H2 is a memory based DB) and requestred initially using a REST client and in branch React with a React Web -client.

## Build and run
mvn install
java -jar target/reactspring-0.0.1-SNAPSHOT.jar 

(or mvn spring-boot:run)

* [Get all] (http://localhost:8080/api/groups)

* [Get one] (http://localhost:8080/api/group/1)

### To POST, PUT and DELETE

Download and install a Rest Client -plugin to Firefox and
* Headers-Customer headers set Content-type/Application/JSON

### In case React pakages are missing (at app-folder)

cd app

npm install

npm install reactstrap

npm install react-router-dom

The library Lombok can cause problems in IDE, then just reinstall Lombok and restart the IDE.

