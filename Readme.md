# Assignment description

## Run the project

### Requirements:

* Maven 3.X
* Java 11+
* Tomcat 10

### Install dependency and run tests

`mvn clean install
`

## Project instructions

The project doesn't implement security there is two users seeded in the database 
which their ID are "testUser" and "testUser1".

You will have to pass the user ID in all requests every time as a query param `"user"`

The project have two endpoint.

`"/Document"` which can run in 3 methods :

`POST` and pass 1 a file in form data as parameter called `"file"`.

`GET` and pass file Id as path variable `"/Document/{fileId}"` to retrieve file.

`Delete` and pass file Id as path variable `"/Document/{fileId}"` to delete file.


`"/Profiles"` have only one method.

`POST` and pass profile in body wich have only two fields `"firstname"` and "`lastname"`



