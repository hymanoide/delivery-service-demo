# Delivery Service 

This demo project that let you call to an endpoint with a POST HTTP request indicating an order number and customer type, and will return a Json with the estimated delivery date from an OrderService (hypothetically connected with a shipping courier).

## Requirements
* Java SDK 20 / JDK 20
* Apache Maven >= 3.8.7
* Windows 11 (Developed with it, but surely you can adapt commands to Linux if needed)

## How to Run

- Clone the project
- Build and run the application
```
.\mvnw spring-boot:run
```
## Use endpoint
If built finished and application is running after that, you'll be able to call
```
http://localhost:8080/index
```
Therefore, you can send a Post Request like: 

```
POST http://localhost:8080/index
Content-Type: application/json

{
"orderId": 15816519,
"customerType": "GOLD"
}
```


**OrderId** not handled at all and is only required but all values are possible. <br />
**CustomerTypes** : GOLD, SILVER, STANDARD``

The expected response should be a JSON similar to following or an error if occurred: 

``{"estimated_delivery_date":"2023-06-21"}``

## Test

Also project provide a Junit and Mockito testing unit, so you can run it with: 

``
.\mvnw test -Dtest=testDaysLeftToDelivery
``

It's composed by 4 steps, and if you run you should see 4/4 passed in terminal console.


## Authors
- [Cesar Benitez](https://github.com/hymanoide)
