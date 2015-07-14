### Product-Api
A RESTful API that serves product data. Currently only supports GET requests.
### Pre Requisite
  - JDK 7
  - Maven 3.x or higher
  - Git

### Installation
```sh
$ git clone git@github.com:sanwar/product-api.git
$ cd product-api
$ mvn clean install
$ mvn spring-boot:run
```
This will run the embedded Tomcat Server on port 8080
and uses embedded H2 database. Once the application starts up, the following endpoints are available for you to access.
  - http://localhost:8080/v1/products - Returns all products in the system. Currently limited to 20 records per request.
   The response also contains pagination details for the client to make subsequent requests.
  - http://localhost:8080/v1/{productId} - Returns a specific product.
  - http://localhost:8080/v1/products?categoryId=? - Returns all products belonging to a specific category id. Again records are limited by 20 per request.

  An example to request records by page and sorting is as follows :
    - http://localhost:8080/v1/products?page=0&size=10&sort=name,DESC.
      Page always starts at 0 and maximum page size is 20.
      Sort accepts name of the property of the entity, for eg: name of product.
      
The project is organized as follows:
* src/main/java/com/anwar/domain - Contains domain classes for the application
* src/main/java/com/anwar/service - Service interfaces
* src/main/java/com/anwar/repository - Data Access API
* src/main/java/com/anwar/web - View / Controller logic
* src/main/resources - Application configuration files and sample data loading scripts

The in-memory H2 db is configured with some sample data. More can be added by updating the following data file:

* src/main/resources/data-h2.sql

Some additional endpoints of interest are :
  - http://localhost:8080/health - Provides heartbeat for the application
  - http://localhost:8080/metrics - General metrics around resource utilization and so on

### Development

Want to contribute? Great! Please create a pull request

### Todo's
  - Add POST and PUT support
  - Add Swagger API documentation
  - Add Authentication and Authorization support
