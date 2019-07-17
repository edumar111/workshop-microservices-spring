# workshop-microservices-spring


## Requirements

- Java 8 
- Spring Boot 2
- Gradle 
- CURL
- JQ

## Services


### Config Service
http://localhost:8090/customer-service/default

### Discovery  Service (Eureka)
    
http://localhost:8099/

### Gateway Service 

Customer

    curl -X GET http://localhost:8080/customers    -H 'Accept: application/json' | jq '.'

Products

    curl -X GET http://localhost:8080/products \
    -H 'Accept: application/json' | jq '.'


Invoices

    curl -X GET http://localhost:8080/invoices/1 -H 'Accept: application/json' | jq '.'



