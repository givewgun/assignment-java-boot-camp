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
    "token": "Bearer TOKEN",
    "userid": 1
    }
    ```


**2. search product with keyword: `shoes` will be partially or exactly matched and return the following json**
- request
  - GET /products?keyword=shoe
- response
    ```json
    {
    "products": [
        {
            "id": 1,
            "name": "shoes 1",
            "quantity": 2,
            "price": 10.00,
            "image": "a.jpg"
        },
        {
            "id": 2,
            "name": "this is the best SHOES",
            "quantity": 1,
            "price": 500.00
            "image": "b.jpg"
        }
    ]
    ```



**3. select product with id**
- request
  - GET /products/{id}
- response
    ```json
    {
        "id": 1,
        "name": "shoe1",
        "price": 100.0,
        "quantity": 1,
        "image": "img.jpg"
    }
    ```

**4. add to cart**
- request
  - POST /cart/items
    ```json
    {
        "userId": 1,
        "productid": 1,
        "quantity": 1
    }
    ```
- response
    ```json
    {
        "message": "add to basket successful"
    }
    ```

**5. delete from cart**
- request
  - POST /cart/items
    ```json
    {
        "userId": 1,
        "productid": 1,
        "quantity": 1
    }
    ```
- response
    ```json
    {
        "message": "add to basket successful"
    }
    ```

**6. select all cart items**
- request
  - GET /cart/items?userId=1
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

**7. checkout**

**8. user use default address for shipping (Assuming user set his/her address before hand in other api section**

- request
  - GET /address?userid=userid
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

**9. process with default address get from server**

**10. user add payment info (credit card) and pay**
- request
  - POST /payment
    ```json
    {
        "userid": "userid",
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

**11. user view receipt after paid**
- request
  - POST /receipt
    ```json
    {
        "userid": 1,
        "paymentId": 1
    }
    ```
- response
    ```json
    {
    "payer": "John Doe",
    "products": [
        {
        "name": "Shoe 1",
        "quantity": 1
        }
    ],
    "amount": 399.00,
    "address": "addr",
    "district": "district",
    "provice": "province",
    }
    ```

### Auxiliary API
- add address
  - for user to add his address