package runner;

import client.FactoryRequest;
import client.RequestInfo;
import config.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class MyStepdefsClean {
    Response response;
    RequestInfo info;
    Map<String,String> variables = new HashMap<>();

    @Given("tengo acceso a Todo.ly")
    public void tengoAccesoATodoLy() {
        info = new RequestInfo(Configuration.BASE_URL + "api/authentication/token.json", "");
        response = FactoryRequest.make("GET").send(info);
        response.then()
                .statusCode(200)
                .body("UserEmail", equalTo(Configuration.USER));
        String token = response.then().extract().path("TokenString");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Todo.ly no devolvio un token de autenticacion");
        }
        variables.put("TOKEN", token);
    }

    @When("send {} request {string} with")
    public void sendPOSTRequestWith(String method, String url,String body) {
        info = new RequestInfo(Configuration.BASE_URL+replaceVar(url),body);
        info.setToken(variables.get("TOKEN"));
        response = FactoryRequest.make(method).send(info);
    }

    @Then("code should be {int}")
    public void codeShouldBe(int expectedCode) {
        response.then()
                .statusCode(expectedCode);
    }

    @And("the {string} should be {string}")
    public void theShouldBe(String attribute, String value) {
        response.then()
                .body(attribute,equalTo(value));
    }

    @And("save {string} in {string}")
    public void saveAttribute(String attribute, String variable) {
        int value = response.then().extract().path(attribute);
        variables.put(variable, value+"");
        System.out.println("Variable "+variable+" = "+value);
    }

    private String replaceVar(String value){
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            value = value.replace(entry.getKey(), entry.getValue());
        }
        return value;
    }

}