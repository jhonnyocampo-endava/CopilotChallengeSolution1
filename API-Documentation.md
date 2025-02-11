# API Documentation - Banking Portal

**Introduction:**
The Banking Portal API allows users to perform various banking operations, including managing accounts, fund transfers, and transactions. This document provides details on each API endpoint, its request parameters, response structure, and authentication requirements. It also includes information on error handling and status codes.

**Authentication:**
The API endpoints require bearer token authentication. Users must obtain a valid access token and include it in the "Authorization" header with the "Bearer" scheme to access protected endpoints.

**Base URL:**
`http://localhost:8180`

### 1. Account Endpoints

#### 1.1. Check PIN Created
- **Endpoint:** `/api/account/pin/check`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Request Body:** None
- **Description:** Checks if a PIN has been created for the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```
    PIN has been created for this account
    ```
    or
    ```
    PIN has not been created for this account
    ```

**Possible Status Codes:**
- 200 OK: The PIN status was successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.

#### 1.2. Create PIN
- **Endpoint:** `/api/account/pin/create`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "accountNumber": "236480",
      "pin": "1234",
      "password": "Secretpassword3*"
  }
  ```
- **Description:** Creates a new PIN for the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "hasPIN": true,
        "msg": "PIN created successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The PIN was successfully created.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated or the provided password is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.3. Update PIN
- **Endpoint:** `/api/account/pin/update`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "accountNumber": "236480",
      "oldPin": "1234",
      "newPin": "4321",
      "password": "Secretpassword3*"
  }
  ```
- **Description:** Updates the existing PIN for the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "hasPIN": false, 
        "msg": "PIN updated successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The PIN was successfully updated.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated, the provided password is incorrect, or the old PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.4. Withdraw
- **Endpoint:** `/api/account/withdraw`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "accountNumber": "236480",
      "pin": "1234",
      "amount": 10.0      
  }
  ```
- **Description:** Withdraws the specified amount from the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "msg": "Cash withdrawn successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The cash withdrawal was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.5. Deposit
- **Endpoint:** `/api/account/deposit`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "accountNumber": "236480",
      "pin": "1234",
      "amount": 10.0      
  }
  ```
- **Description:** Deposits the specified amount into the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "msg": "Cash deposited successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The cash deposit was successful.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.6. Fund Transfer
- **Endpoint:** `/api/account/fund-transfer`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "sourceAccountNumber": "784893",
      "targetAccountNumber": "556704",
      "amount": 10.0,
      "pin": "1234"
  }
  ```
- **Description:** Transfers the specified amount to another user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "msg": "Fund transferred successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The fund transfer was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's source or target account was not found.

#### 1.7. Transactions
- **Endpoint:** `/api/account/transactions`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves the list of transactions for the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body: List of transaction objects.

**Possible Status Codes:**
- 200 OK: The list of transactions was successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.

#### 1.8. Account Details
- **Endpoint:** `/api/dashboard/account`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves details of the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body: Account details object.

**Possible Status Codes:**
- 200 OK: The account details were successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

### 2. User Endpoints

#### 2.1. Register User
- **Endpoint:** `/api/users/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "name": "John",
      "password": "Secretpassword3*",
      "email": "john@yopmail.com",
      "countryCode": "US",
      "phoneNumber": "2154567890",
      "address": "123 Main Street"
  }
  ```
- **Description:** Registers a new user with the provided details.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "name": "John",
        "email": "john@yopmail.com",
        "countryCode": "US",
        "phoneNumber": "2154567890",
        "address": "123 Main Street",
        "accountNumber": "236480",
        "ifscCode": "[IFSCCODE]",
        "branch": "[BRANCH]",
        "accountType": "[ACCOUNTTYPE]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The user was successfully registered.
- 400 Bad Request: The request body is missing or invalid.
- 404 Not Found: The user's account was not found.

#### 2.2. Get User Details
- **Endpoint:** `/api/dashboard/user`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves details of the authenticated user.
- **Response:**
  - Status Code: 200 OK
  - Body: User details object.

**Possible Status Codes:**
- 200 OK: The user details were successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 2.3. Login
- **Endpoint:** `/api/users/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "identifier": "236480",
      "password": "Secretpassword3*"
  }
  ```
- **Description:** Logs in the user with the provided account number and password.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "token": "[JWT_TOKEN]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The login was successful, and a JWT token is provided in the response body.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The provided account number or password is incorrect.
- 404 Not Found: The user's account was not found.

#### 2.4. Logout
- **Endpoint:** `/api/users/logout`
- **Method:** `GET`
- **Request Body:** None
- **Description:** Logs out the user and invalidates the existing bearer token.
- **Response:**
  - Status Code: 200 OK

**Possible Status Codes:**
- 200 OK: The login was successful, and a JWT token is provided in the response body.
- 401 Unauthorized: The provided account number or password is incorrect.
- 404 Not Found: The user's account was not found.

#### 2.5. Update User
- **Endpoint:** `/api/users/update`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "name": "Abhishek a",
      "password": "secretpassword2",
      "email": "jonoe@gmail.com",
      "countryCode": "US",
      "phoneNumber": "134566690",
      "address": "123 Main Street"
  }
  ```
- **Description:** Updates an existing logged in user with new details. The "password" field for this endpoint is the account password to verify the account before updates are saved.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
      "name": "Abhishek a",
      "email": "jonoe@gmail.com",
      "countryCode": "US",
      "phoneNumber": "134566690",
      "address": "123 Main St",
      "accountNumber": "236480",
      "ifscCode": "NIT001",
      "branch": "NIT",
      "accountType": "Savings"
    }
    ```

**Possible Status Codes:**
- 200 OK: The user was successfully registered.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The provided account number or password is incorrect.
- 404 Not Found: The user's account was not found.
- 500 Internal Server Error: The email or phone number for the user information being updated is already in use by another user in the database.

<!-- Commenting out unused endpoints 2.6 and 2.7 for an unused OTP function, but leaving in case they are used in the future
#### 2.6. Generate OTP for Login (Under Review)
- **Endpoint:** `/api/users/generate-otp`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "identifier": "236480"
  }
  ```
- **Description:** Sends an OTP (6-digit integer string) to the email for the identifier account number.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "message": "OTP sent successfully to: [user email]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The OTP was generated successfully.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 2.7. Verify OTP for User Login (Under Review)
- **Endpoint:** `/api/users/verify-otp`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "identifier": "236480",
      "otp": "123456"
  }
  ```
- **Description:** Verifies the OTP (6-digit integer string) in the message body matches the OTP sent to the email for the identifier account number, and provides an updated login bearer token.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "token": "[JWT_Token]"
    }
    ```
-->
### 3. Authentication Endpoints

#### 3.1. Send OTP for Password Reset
- **Endpoint:** `/api/auth/password-reset/send-otp`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "identifier": "236480"
  }
  ```
- **Description:** Sends an OTP (6-digit integer string) to the email for the identifier account number.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "message": "OTP sent successfully to: jonoe@gmail.com"
    }
    ```

**Possible Status Codes:**
- 200 OK: The OTP was generated successfully.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 3.2. Verify OTP for Password Reset
- **Endpoint:** `/api/auth/password-reset/verify-otp`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "identifier": "236480",
      "otp": "123456"
  }
  ```
- **Description:** Verifies the OTP (6-digit integer string) in the message body OTP field matches the OTP sent to the email for the identifier account number, then provides a password reset token.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "passwordResetToken": "[Password Reset Token]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The OTP was generated successfully.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 3.3. Reset Password
- **Endpoint:** `/api/auth/password-reset/verify-otp`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "identifier": "236480",
      "resetToken": "[Reset Token from 3.2. Verify OTP]"
      "newPassword": "newsecretpassword2"
  }
  ```
- **Description:** Resets the password for the identifier account to the new password.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "message": "Password reset successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The OTP was generated successfully.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

**Error Handling:**
The API implements global exception handling for the following scenarios:
- NotFoundException: Returns 404 Not Found with an error message.
- UnauthorizedException: Returns 401 Unauthorized with an error message.
- InsufficientBalanceException: Returns 400 Bad Request with an error message.

Please note that this API documentation provides an overview of the available endpoints, request parameters, and response structures, along with additional details on error handling and status codes. For a comprehensive understanding of the API, further details such as possible response data and detailed error messages should be included in the final API documentation.