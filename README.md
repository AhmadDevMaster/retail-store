### Retail - Discount Service
This is simple Implementation for Discount Service, that will take the bill and calculate net payable amount according to customer type, consider ignoring groceries items

## This Implementation covers the following Technologies & Activities:
* Maven Project
* Java 17
* Spring Boot 3.3.0
* Spring Security
* Restful API
* Docker
* MongoDB for persistence
* docker compose file
* Unit Testing using Mockito
* JaCoCo plugin for code coverage

## Business requirements

Retail store have many strategies to keep customers loyal to our store by making different discounts rules.
The Discount Rules are described as following:
1. If the user is an employee of the store, he gets a 30% discount.
2. If the user is an affiliate of the store, he gets a 10% discount.
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as
a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

## Implementation Details

I built the solution using Spring Boot, Spring Security, MongoDB, and Docker.
with the following main components: <br>
1- Discount Service: Spring Boot application that will calculate the discount for the bill. <br>
2- User Service: Spring Boot application that will authenticate the user and return token. <br>
3- API Gateway: Spring Boot application that will authenticate the user and route the request to the proper service.<br>
4- MongoDB: to store the discount rules.<br>
5- Docker: to run the services.<br>

## 	Discount Service implementation details 

I built all mentioned above rules in generic and dynamic way; so the rules can be modified or extended.
The following are sample of employee customer business rule that already stored in MongoDB
	
	{
		ruleType: 'Employee',
	    discountType: 'Percentage',
    	threshold: 0,
	    discountValue: 0.3
	}

### Run Services
In order to run the services, please do the following:
* mvn clean install in the parent folder
* docker compose up in the parent folder

### API Documentation
first you need to get the token from the user service by calling the following API
POST http://localhost:8085/api/v1/auth/login <br>

Content-Type: application/json <br>
```{

"username": "ahmad",

"password": "ahmaD"

}```
<br>
Then you can call the discount service API

POST http://localhost:8085/api/v1/bills/discount/calculate <br>
Content-Type: application/json <br>

{
    "id": "5",
    "totalAmount": 1821,
    "customer": {
        "customerId": 5,
        "customerType": "Employee"
    },
    "items": [
        {
            "itemId": 6,
            "itemType": "WIRELESS",
            "price": 660.5,
            "quantity": 2
        },
        {
            "itemId": 7,
            "itemType": "GROCERIES",
            "price": 500,
            "quantity": 1
        }
    ]
}
