# PalindromeChecker

Topics:
1. [Config](https://github.com/ArunSb28/PalindromeChecker#configuraion)
2. [Running with Docker](https://github.com/ArunSb28/PalindromeChecker#how-to-run-using-docker)
3. [Testing](https://github.com/ArunSb28/PalindromeChecker/blob/main/README.md#testing)
4. [Sample Data](https://github.com/ArunSb28/PalindromeChecker/blob/main/README.md#examples)


## Configuraion:

- Spring:
  - Port : 8088

- Redis:
  - Hostname: redis
  - Port: 6379
 
Other details can be found under [Properties](/src/main/resources/application.properties)


## How to Run using docker
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

## Testing: 

1. Open [localhost](http://localhost:8088) in browser and login with your name
2. Test using the below end point to save the request and also see the request data it in the above window
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
3. The Data flow will also be broadcasted to the opened window for each step
4. To retrive all the saved data with palindrome lenght. __*longest_palindrome_size*__ represents the lenght of palindrom substring :
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
```
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
