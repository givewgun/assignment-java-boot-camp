# Week1 Project

##  User flow summary
This flow with breifly summarize the flow that user will be using

**1. login with username: `user` password: `password`**
- request
  - POST /user/login
    ```json
    {
        "user":"user",
        "password":"password"
    }
    ```
- response
  - TOKEN will consist of username and id
    ```json
    {
    "token": "Bearer TOKEN"
    }
    ```


**2. search product with keyword: `shoes` will be partially or exactly matched and return the following json**
- request
  - GET /products?name=shoe
- response
    ```json
    {
    "products": [
        {
        "id": 1,
        "name": "shoes 1",
        "quantity": 2,
        "price": 10.00
        }
    ],
    "products": [
        {
        "id": 2,
        "name": "this is the best SHOES",
        "quantity": 1,
        "price": 500.00
        }
    ],
    }
    ```



**3. select product with id**
- request
  - GET /products/{id}
- response
    ```json
    {
        "name": "shoes 1",
        "quantity": 2,
        "price": 10.00
    }
    ```

**4. add to basket**
- request
  - POST /basket
    ```json
    {
        "user": "username",
        "productid": 1
        "quantity": 1
    }
    ```
- response
    ```json
    {
        "message": "add to basket successful"
    }
    ```

**5. select basket**
- request
  - GET /basket?user=username
- response
    ```json
    {
    "products": [
        {
        "name": "shoes 2",
        "quantity": 1,
        "price": 10.00
        }
    ],
    "totalPrice": 10.00
    }
    ```

**6. checkout**

**7. user use default address for shipping (Assuming user set his/her address before hand in other api section**

- request
  - GET /address?user=username
- response
    ```json
    {
        "fullName": "John Doe",
        "address": "address",
        "district":"district",
        "province": "province",
        "postCode": 11111,
        "phone": 0800000000
    }
    ```

**8. process with default address get from server**

**9. user add payment info (credit card) and pay**
- request
  - POST /payment
    ```json
    {
        "user": "username",
        "fillName": "address",
        "cardNumber":"1111222233334444",
        "expiry": "02/25",
        "cvv": 000
    }
    ```
- response
    ```json
    {
        "message": "payment successful",
        "paymentId": 1
    }
    ```

**10. user view receipt after paid**
- request
  - POST /receipt
    ```json
    {
        "username": "username",
        "paymentId": 1
    }
    ```
- response
    ```json
    {
    "payer": "John Doe",
    "products": [
        {
        "name": "POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White",
        "quantity": 1
        }
    ],
    "amount": 399.00,
    "address": "Somewhere in Thailand",
    "cardNumber": "1111222233334444"
    }
    ```