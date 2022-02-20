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
    "token": "Bearer {username},{userid}",
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
  - POST /cart/items/add
  - With Header : `Authorization: Bearer {username},{userid}`
    ```json
    {
        "productid": 1,
        "quantity": 1
    }
    ```
- response
    ```json
    {
        "message": "add product to cart successfully"
    }
    ```

**5. delete from cart**
- request
  - POST /cart/items/remove
  - With Header : `Authorization: Bearer {username},{userid}`
    ```json
    {
        "productid": 1,
        "quantity": 1
    }
    ```
- response
    ```json
    {
        "message": "removed product from cart successfully"
    }
    ```

**6. select all cart items**
- request
  - GET /cart/items
  - With Header : `Authorization: Bearer {username},{userid}`
- response
    ```json
    {
        "id": 1,
        "cartItems": [
            {
                "id": 6,
                "productId": 2,
                "quantity": 1
            }
        ],
        "totalPrice": 0.0
    }
    ```

**7. user add payment info (credit card) and address and pay for all items in cart**
- request
  - POST /payment
  - With Header : `Authorization: Bearer {username},{userid}`
    ```json
    {
        "fullName": "full name",
        "cardNumber":"1111222233334444",
        "expiry": "02/25",
        "cvv": "000",
        "address": "address",
        "phone": "0800000000"
    }
    ```
- response
    ```json
    {
        "id": 8,
        "userId": 1,
        "address": "address",
        "phone": "0800000000",
        "totalPrices": 200.0,
        "items": [
            {
                "id": 9,
                "productId": 3,
                "quantity": 1
            },
            {
                "id": 10,
                "productId": 4,
                "quantity": 1
            },
            {
                "id": 11,
                "productId": 2,
                "quantity": 1
            }
        ]
    }
    ```

**8. user view order receipt after paid**
- request
  - GET /orders?orderId=1
  - With Header : `Authorization: Bearer {username},{userid}`
- response
    ```json
     {
        "id": 8,
        "userId": 1,
        "address": "address",
        "phone": "0800000000",
        "totalPrices": 200.0,
        "items": [
            {
                "id": 9,
                "productId": 3,
                "quantity": 1
            },
            {
                "id": 10,
                "productId": 4,
                "quantity": 1
            },
            {
                "id": 11,
                "productId": 2,
                "quantity": 1
            }
        ]
    }
    ```