package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserAccountSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private String token;

    @Given("the user details are provided")
    public void theUserDetailsAreProvided() {
        requestBody = new HashMap<>();
        requestBody.put("name", "John");
        requestBody.put("password", "Secretpassword3*");
        requestBody.put("email", "john" + System.currentTimeMillis() + "@yopmail.com");
        requestBody.put("countryCode", "US");
        requestBody.put("phoneNumber", "215" + (int)(Math.random() * 9000000 + 1000000));
        requestBody.put("address", "123 Main Street");
    }

    @When("the user sends a registration request")
    public void theUserSendsARegistrationRequest() {
        RestAssured.baseURI = "http://localhost:8180";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/api/users/register")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Then("the user should be registered successfully")
    public void theUserShouldBeRegisteredSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("John"));
    }

    @When("the user logs in with the registered details")
    public void theUserLogsInWithTheRegisteredDetails() {
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("identifier", requestBody.get("email"));
        loginRequest.put("password", requestBody.get("password"));

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginRequest)
                .log().all()
                .when()
                .post("/api/users/login")
                .then()
                .log().all()
                .extract()
                .response();

        token = response.jsonPath().getString("token");
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(token);
    }

    @When("the user creates a PIN")
    public void theUserCreatesAPIN() {
        Map<String, Object> pinRequest = new HashMap<>();
        pinRequest.put("accountNumber", "236480");
        pinRequest.put("pin", "1234");
        pinRequest.put("password", requestBody.get("password"));

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(pinRequest)
                .log().all()
                .when()
                .post("/api/account/pin/create")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Then("the PIN should be created successfully")
    public void thePINShouldBeCreatedSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("PIN created successfully"));
    }

    @Given("the user logs in with the identifier {string} and password {string}")
    public void theUserLogsInWithTheIdentifierAndPassword(String identifier, String password) {
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("identifier", identifier);
        loginRequest.put("password", password);

        RestAssured.baseURI = "http://localhost:8180";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginRequest)
                .log().all()
                .when()
                .post("/api/users/login")
                .then()
                .log().all()
                .extract()
                .response();

        token = response.jsonPath().getString("token");
    }

    @When("the user deposits {int}")
    public void theUserDeposits(int amount) {
        Map<String, Object> depositRequest = new HashMap<>();
        depositRequest.put("accountNumber", "236480");
        depositRequest.put("pin", "1234");
        depositRequest.put("amount", amount);

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(depositRequest)
                .log().all()
                .when()
                .post("/api/account/deposit")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Then("the deposit should be successful")
    public void theDepositShouldBeSuccessful() {
        Assert.assertEquals(200, response.getStatusCode());
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Cash deposited successfully"));
    }
}