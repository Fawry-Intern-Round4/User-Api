# User API

The `User API` service manages user operations such as registration, authentication, and user activation/deactivation. It is part of the organization's microservices architecture.

## Overview

The `User API` provides functionality for:
- User registration and authentication.
- User activation and deactivation.
- Retrieving a list of all users.

## Endpoints

### Token Validation

- **URL**: `/token/validation`
- **Method**: `GET`
- **Description**: Validates a token's authenticity.
- **Response**: `200 OK`

---

### Sign Up

- **URL**: `/user`
- **Method**: `POST`
- **Description**: Registers a new user.
- **Request Body**: 
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response**: 
  ```json
  {
    "id": "long",
    "username": "string",
    "status": "string"
  }
  ```
- **Response Code**: `201 Created`

---

### Login

- **URL**: `/user/login`
- **Method**: `POST`
- **Description**: Authenticates a user and returns a JWT token.
- **Request Body**: 
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response**: 
  ```json
  {
    "token": "string",
    "expiresAt": "datetime"
  }
  ```
- **Response Code**: `200 OK`

---

### Activate User

- **URL**: `/user/activation/{id}`
- **Method**: `PUT`
- **Description**: Activates a user account by ID.
- **Path Variable**: `id` (Long)
- **Response**: 
  ```json
  {
    "id": "long",
    "username": "string",
    "status": "string"
  }
  ```
- **Response Code**: `200 OK`

---

### Deactivate User

- **URL**: `/user/deactivation/{id}`
- **Method**: `PUT`
- **Description**: Deactivates a user account by ID.
- **Path Variable**: `id` (Long)
- **Response**: 
  ```json
  {
    "id": "long",
    "username": "string",
    "status": "string"
  }
  ```
- **Response Code**: `200 OK`

---

### Find All Users

- **URL**: `/user`
- **Method**: `GET`
- **Description**: Retrieves a list of all registered users.
- **Response**: 
  ```json
  [
    {
      "id": "long",
      "username": "string",
      "status": "string"
    }
  ]
  ```
- **Response Code**: `200 OK`

---

## Configuration

- Configure the database connection in the `application.properties` file:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
  spring.datasource.username=your_db_username
  spring.datasource.password=your_db_password
  ```
