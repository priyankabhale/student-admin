# Student-admin 
This readme shall explain, how to get the project up an running.

# Preperation
Clone this repository and import it as existing maven project into an IDE of your choice.

# Preparing the Database
The database used is mysql server. Create a schema of name **studentadmin** in your mysql using a mysql-workbench or command-line.
In the *src/main/resources/application.properties* of the project change the *spring.datasource.username=
spring.datasource.password=*. Set the username and password according to your local mysql.
If you need a local copy of the application database schemas, you can find a complete initialisation sql script in the DDL folder.

# API's
**GET http://localhost:8080/student/id/{id} :** 
Fetch Student information of the id received as PathVariable

**DELETE http://localhost:8080/student/id/{id} :** 
Delete Student information of the id received as PathVariable

**POST http://localhost:8080/student :** 
Save Student object received in RequestBody

**PUT http://localhost:8080/student :** 
Update Student object received in RequestBody

**GET http://localhost:8080/student/all/{classNumber} :** 
Fetch information of all Students in a class. The classNumber is received in PathVariable

**GET http://localhost:8080/student/all :** 
Fetch information of all Students




