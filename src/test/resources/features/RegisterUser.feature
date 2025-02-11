Feature: Register User

  Scenario: Successful user registration
    Given the user details are provided
    When the user sends a registration request
    Then the user should be registered successfully