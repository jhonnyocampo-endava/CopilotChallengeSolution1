Feature: Register, Login, and Create PIN

  Scenario: Successful user registration, login, and PIN creation
    Given the user details are provided
    When the user sends a registration request
    Then the user should be registered successfully
    When the user logs in with the registered details
    Then the user should be logged in successfully
    When the user creates a PIN
    Then the PIN should be created successfully