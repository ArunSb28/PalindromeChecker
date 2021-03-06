# PalindromeChecker

This application is used to store the strings in Redis DB and while retrieving the stored data it is enriched with longest palindromic substring persent in the content. The input should be only alphabets, so numbers/ special characters will not be taken into consideration for palindrome calculation. Having the special character/number in the middle of string will reset the palindrome length of that particular string. 

  - __*Tech Stack:*__ _Java 11, Spring Boot, Redis as DB & Pub-Sub and WebSocket to broadcast the input received._
  - *High level design diagram is available [here](https://github.com/ArunSb28/PalindromeChecker/blob/main/src/main/resources/static/images/HighLevel_Design.png)*


## Configuraion:

- Spring:
  - Port : 8088
  
  _Note: The port can be modified in the docker-compose file if 8088 is already in-use_

- Redis:
  - Hostname: redis
  - Port: 6379
 
Other details can be found under [Properties](/src/main/resources/application.properties)

## Running on docker
1. Clone the project
2. Build the project

``` Java
mvn clean install
```
3. Run using docker-compose
```
docker-compose up --build  -d
```
*You can install docker compose from [here](https://docs.docker.com/compose/install/#install-compose)*


## Running Locally:

If you want to run the Spring appliation in your local and Redis on Docker follow the below steps:
  
  - Clone the project 
  - Import to your favouite IDE
  - Setup Redis docker with below command:
  ```
  docker run -d -p 6379:6379 --name redis1 redis
  ```
  This will start the redis image on port 6379
  
  - Alternatively you can run Redis on your local machine, *the Springboot app configuration remains same for both*.
  
  - Modify the following property in _application.properties_ :
  ```
  From:
  redis.hostname = redis
  redis.port = 6379
  
  To:
  redis.hostname = localhost
  redis.port = <6379 or Port of your choice as configured while Redis setup>
  ```
  - To change the Springboot applicatoin port :
  ```
  From:
  server.port = 8088
  
  To:
  server.port = <Port of your choice>
  ```
  - Execute the below command
  
  ```
  mvn clean install
  ```
    
  - If you get this error *'perhaps you are running on a jre rather than a jdk maven'*, then import JDK instead by following [this](https://stackoverflow.com/questions/13635563/setting-jdk-in-eclipse)
  
  - Run the Springboot application in IDE
  
  _You can start testing!_

*Note: Make sure to update the docker-compose.yml file accordingly with modified port if needed*

## Testing: 

1. Open [localhost:8088](http://localhost:8088) in browser and login with your name

 ![Login Page](https://github.com/ArunSb28/PalindromeChecker/blob/main/src/main/resources/static/images/LoginPage_small.JPG)


2. Test using the below end point to save the request and also see the request data after login as below:
```curl
curl --location --request POST 'http://localhost:8088/palindrome/save'
```
  - Sample Request:
```json
{
    "content": "malayalam",
    "timestamp": "2021-10-09 00:12:12+0100"
}
```
![websocketClient](https://github.com/ArunSb28/PalindromeChecker/blob/main/src/main/resources/static/images/webSocket_Client.JPG)

3. To retrieve all the saved data with palindrome length use below endpoint. __*longest_palindrome_size*__ represents the length of palindrome substring :

```curl
curl --location --request GET 'http://localhost:8088/palindrome/findAll'
```
  - Sample Response:
  ```json
  [    
    {
        "content": "malayalam",
        "timeStamp": "2021-10-09 00:12:12+0100",
        "longest_palindrome_size": 9
    }
]
```
#### **_Note:_** _The palindrome checker is case-insensitive and it will not take number & special charactes into palidrome calculation_
##### Examples:

Request:
```json

{
    "content": "malayalam",
    "timestamp": "2021-10-09 00:12:12+0100"
}
```
Response:
```json
  [    
    {
        "content": "malayalam",
        "timeStamp": "2021-10-09 00:12:12+0100",
        "longest_palindrome_size": 9
    }
]
```
__With Special Character__

Request:
```json
{
    "content": "malay@alam",
    "timestamp": "2021-10-09 00:12:12+0100"
}
```
Response:
```json
  [    
    {
        "content": "malay@alam",
        "timeStamp": "2021-10-09 00:12:12+0100",
        "longest_palindrome_size": 3
    }
]
```
Here *ala* is the palindrome string. Same goes for number too.


