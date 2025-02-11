Feature: Login and Deposit

  Scenario: Successful login and deposit
    Given the user logs in with the identifier "john1739240430221@yopmail.com" and password "Secretpassword3*"
    When the user deposits 10000
    Then the deposit should be successful