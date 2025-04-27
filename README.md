MovieMail - Online DVD Rental System



Project Overview

MovieMail is an online DVD rental system built with Spring Boot, MySQL, and Docker. It allows users to register, log in, subscribe to DVD rental plans, browse DVDs, and manage their personal watchlists. The project demonstrates a real-world application with authentication, role-based access control, and relational database design.

The application is containerized and can be easily deployed to cloud services such as AWS EC2 and AWS RDS.



Features

User Registration & Authentication (JWT-based security)

Subscription Management (choose DVD rental plans)

DVD Browsing (view list and detailed info)

Watchlist Management (add DVDs with priority)

Admin and User Roles (role-based authorization)

RESTful APIs built with Spring Boot

Database interaction using Spring Data JPA

Containerization with Docker



Technologies Used

Java 17

Spring Boot 3.2.5

Spring Security (JWT Authentication)

Spring Data JPA

RDS 

Docker & Docker Compose

JUnit 5, Mockito for Testing

AWS EC2 and RDS



Entity Relationships

Customer

Represents a user registered in the system.

Relationships:

One-to-Many with Subscription (a customer can have multiple subscriptions)

Many-to-Many with DVD through WatchListEntry

Subscription

Represents a DVD rental plan.

Fields: name, dvdsAtHome, dvdsPerMonth, pricePerMonth, active, linked customer.

Relationships:

Many-to-One with Customer

DVD

Represents a DVD available for rent.

Fields: title, genre, releaseYear, etc.

Relationships:

Many-to-Many with Customer via WatchListEntry

WatchListEntry

Join entity for managing customer's watchlists.

Fields: customer_id, dvd_id, priority.

Relationships:

Many-to-One with Customer

Many-to-One with DVD

Diagram of Relationships:

Customer (1) ------> (Many) Subscription
Customer (Many) <---> (Many) DVD (via WatchListEntry)



Deployment Instructions

Build the application

mvn clean install

Create Docker Images

docker build -t moviemail-app .

Run using Docker Compose

docker-compose up -d

Access the Application

http://<EC2-Public-IP>:8080




API Endpoints

Authentication

POST /api/v1/auth/register - Register new user

POST /api/v1/auth/login - Login and get JWT token

Customer

POST /api/v1/customer - Create customer (requires authentication)

GET /api/v1/customer/profile - Get customer profile

Subscription

POST /api/v1/subscriptions - Subscribe to a plan

DVD

GET /api/v1/dvds - Get all DVDs

GET /api/v1/dvds/{id} - Get DVD by ID

Watchlist

POST /api/v1/watchlist - Add DVD to watchlist

DELETE /api/v1/watchlist/{dvdId} - Remove DVD from watchlist



Author

Nahom HailemariamContact: nhailemariam24@gmail.com
