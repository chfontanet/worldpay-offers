Overview
========
I have developed my test in Java 8.
Is a Spring Boot application with maven, with the following endPoints:

- **GET** [all]: Returns a list of OfferDTO with HttpStatus *200 (OK)*.
- **GET(id)**: Returns the specified OfferDTO with HttpStatus *200 (OK)*, or a HttpStatus *404 (Not found)* if the specified Offer does not exists in
Database.
- **POST**: Returns the OfferDTO created with HttpStatus *201 (Created)*, or HttpStatus *409 (Conflict)* if the Offer already exists in Database. If
any of the required values (id, price, currency, expiringDate) are not informed, you will get a *400 (Bad request)*.
- **DELETE(id)**: Returns a HttpStatus *204 (No Content)* or a *404 (Not Found)* if Offer does not exists in Database.

## Data
DTO class includes the following attributes:

**OfferDTO**
- **id** | String | *required*
- **name** | *String*
- **description** | *String*
- **price** | Double | *required*
- **currency** | Currency | *required*
- **expiringDate** | DateTime | *required*

This is an example of JSON:
```
{
    "id": "1",
    "name": "product 1",
    "description": "description for product 1",
    "price": 9.99,
    "currency": "GBP",
    "expiringDate": "2018-12-05T17:23:02.231Z"
}
```

## Persistence
I have chosed to persist data in a ConcurrentHashMap with manual expiration. I was checking existent libraries but all of them expired all the
entities at the same time. In order to save overload in CPU I am not executing a constant thread to remove expired values, and I check the values when
 I access to the Map when adding, cancelling or getting offers.

## Libraries
I have used joda-time library in order to use DateTime, and jackson-datatype-joda to serialize and deserialize the date from/to JSON with this format
`yyyy-MM-ddTHH:mm:ss.SSSZ`.

I also have used guava from Google to make String validations, compare Objects in equals methods and work with ImmutableList in tests.
## Exceptions
I created three exception classes to manage the HttpStatus I want to return in any case.  
For an argument exception I return a *400 (Bad Request)*.  
For an existing exception I return a *409 (Conflict)*
For an offer not found exception I return a *404 (Not Found)*

If you use an invalid currency, or date format, the application returns a *400 (Bad Request)*.

## Installation and running
You can install the application making a package of the project and output to a "jar" file with the command `mvn clean package`.

To start the application, you can execute `java -jar target/worldpay-offers-0.0.1-SNAPSHOT.jar`.

Once the application is started, you can access to it trough the following URL's:

### GET
http://localhost:8080/offers/  
http://localhost:8080/offers/{id}/

### POST
http://localhost:8080/offers/

### DELETE
http://localhost:8080/offers/{id}/
