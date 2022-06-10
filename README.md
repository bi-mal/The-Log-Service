# The-Log-Service
A RESTful Web Service using SpringBoot, Spring data JPA, h2 in memory database to consume, store, and process logs from a frontend application and to make the stored logs retrievable for batch processing.The logs are retrievable by any combination of user, time range and log type. The response is a list of logs. 

Documentation
Name of the Application : openHouseAI/logs

Coding Challenge :

Create a Spring Boot app, Flask app, or use a similar framework that you prefer, as a RESTful Web Service to consume, store, and process logs from a frontend application and to make the stored logs retrievable for batch processing.

Solution :

In order to build the Restful webservice, I created three entities named "Log", "Action" and "Properties". These three entities are linked together with foreign key relationships and mapped by one to many and one to one relation according to the structure of the logs.

I have used h2 in memory database, later we can spin up PostgreSQL or mySQL according to the requirement.
The username and password of the database can be found in application.properties file under the src/main/resources directory.
The H2 console can be accessed at http://localhost:8085/h2-console/ on your local machine

The application has 5 layers namely DAO, LogService,Exceptions, Controller and Entity.

  - The DAO layer uses CrudRepository implementation of spring data JPA as DAO.
  - The Log Service contains an interface which defines the required functionality and a class which implements the functionality
  - The Exception layer provide custom http responses
  - Controller class has mapped all the required end points which includes POST, PUT, DELETE and 7 GET endpoints which are the combinations of log retrieving defined in coding challenge
  - Entity layer contains three classes which are designed based on sample log structure. 

I have also documented the web service using Swagger 2 and Swagger ui which can be accessed at http://localhost:8085/v2/api-docs in postman and localhost:8085/swagger-ui/ on your local machine respectively.

All the endpoints are successfully tested using postman. 

Answer to the follow up question:
We can deploy application to the cloud and use container orchestration tool like kubernetes to scale up or down the instances.



