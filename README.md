# Product Management System
The API
 allows users to perform CRUD operations on products while enforcing validation
rules and adhering to best practices and design patterns.

# Technology Stack Used : 

Java 17,Springboot 3.2.4,Spring Security,MongoDb Atlas,Swagger API Documentation


# Run The Application : 

1. To Run the application you can access this project through this github link : https://github.com/kanishkapanchal1199/product-inventory.git 
2. After pulling the repository to local environment , through any IDE you can import the project source code and with <b>ProductInventoryApplication</b> named main class you can run it.
3. Now for testing the REST API endpoints you can use POSTMAN or also can access the swagger-ui with this link http://localhost:8080/swagger-ui/index.html which will lead to all API endpoints defined in the application.

# Spring Security Basic Authentication :

Before Accessing below all REST API endpoints Authorization needs to done with **Basic Auth** , Below are the credentails to access all of the REST API Endpoints.

Username : Kanishka
<Br/>
Password : admin@123


# REST API Endpoints :

1. GET - localhost:8080/products 
   <ul>
   <li>This is GET API which will give the all products available in the database with 200 Response.</li>
   </ul>
   <br/>
2. GET - localhost:8080/products/6c7883ec
   <ul>
   <li>This is GET API which will give the  products based on the given product id in URL in the database with 200 Response.</li>
   </ul>

   <br/>
3. POST - localhost:8080/products/add
   <ul>
   <li>This is POST API which will add the product into database with 201 Response.</li>
   </ul>
    <br/>
4. PUT - localhost:8080/products/update/6c7883ec
   <ul>
   <li>This is PUT API which will Update the product into database based on product id given with 200 Response.</li>
   </ul>
    <br/>
5. DELETE - localhost:8080/products/f44d602a
    <ul>
   <li>This is DELETE API which will Delete the product into database based on product id given with 200 Response.</li>
   </ul>
    <br/>





