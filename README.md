# Simple Backend E-Commerce

## Introduction
This repository is used for building a simple backend e-commerce system. Currently, I had implemented 2 microservices:
* product
* order

The sequence diagram of system like below:
![alt text](https://github.com/kyotin/simple-ecom/blob/master/sq_diagram.jpg?raw=true)

## Product Service
Product service has responsible for display categories, products, as well as images of product,... 

### ERD
![alt text](https://github.com/kyotin/simple-ecom/blob/master/erd_product.png?raw=true)

### Components involved
* MySQL: as database
* Redis: for caching and improve search.

### How to run it on your laptop
1. Set up corresponding env parameters needed by `resource/application.yaml`
2. Then run it

### How to run it on your docker minikuber or k8s
1. Update config parameters in `build/product/values.yaml` file
2. Then run `helm install product .`

### How to test API(s)
After running services successfully, you can go to `localhost:9991/swagger-ui.html` to test API
![alt text](https://github.com/kyotin/simple-ecom/blob/master/prod_swagger.png?raw=true)

### To list products
Request
```
curl -X GET "http://localhost:9991/api/v1/product?categoryId=2&page=0&pageSize=10" -H "accept: */*"
```

### To create product
Request:
```
curl -X POST "http://localhost:9991/api/v1/product" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"category\": { \"id\": 2 }, \"description\": \"Tin Test\", \"name\": \"New Product\", \"price\": 122342.0, \"quantity\": 20, \"thumbnail\": \"...\"}"
```

Response:
```
{
  "code": "OK",
  "message": "Success",
  "data": {
    "id": 15,
    "name": "New Product",
    "description": "Tin Test",
    "thumbnail": "...",
    "price": 122342,
    "quantity": 20,
    "category": {
      "id": 2
    },
    "valid": true
  },
  "timestamp": 1624819350213
}
```

## Order Service
Order service has responsible for customer place an order. Because there is a simple e-commerce business,  
so the idea is we don't have authN/authZ system, just let customer input their personal info on each place order
request.

### ERD
![alt text](https://github.com/kyotin/simple-ecom/blob/master/erd_order.png?raw=true)

### Components involved
* MySQL: as database
* ActiveMQ: my idea is when order status had been changed, Order microservice will fire message to ActiveMQ, so that
notification system can process one by one, like send email to customer for notify that products they bought are shipping now.

### How to run it on your laptop
1. Set up corresponding env parameters needed by `resource/application.yaml`
2. Then run it

### How to run it on your docker minikuber or k8s
1. Update config parameters in `build/order/values.yaml` file
2. Then run `helm install product .`

### How to test API(s)
After running services successfully, you can go to `localhost:9991/swagger-ui.html` to test API
![alt text](https://github.com/kyotin/simple-ecom/blob/master/order_swagger.png?raw=true)

When you place order, you can go to active mq to verify:
![alt text](https://github.com/kyotin/simple-ecom/blob/master/activemq_test.png?raw=true)

### How to place order
Curl request
```
curl -X POST "http://localhost:9992/api/v1/order" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"buyer\": { \"email\": \"nguyentrongtin89@gmail.com\", \"homeAddress\": \"Era\", \"name\": \"Nguyen Trong Tin\", \"phoneNumber\": \"0908235566\" }, \"products\": [ { \"name\": \"KIA Morning\", \"price\": 21230, \"productId\": 1, \"quantity\": 10 } ], \"status\": \"NEW\"}"
```

Response:
```
{
  "code": "OK",
  "message": "Success",
  "data": {
    "status": "NEW",
    "totalPrice": 21230,
    "buyer": {
      "name": "Nguyen Trong Tin",
      "phoneNumber": "0908235566",
      "email": "nguyentrongtin89@gmail.com",
      "homeAddress": "Era",
      "valid": true
    },
    "products": [
      {
        "productId": 1,
        "name": "KIA Morning",
        "price": 21230,
        "quantity": 10,
        "valid": true
      }
    ],
    "valid": true
  },
  "timestamp": 1624819097024
}
```