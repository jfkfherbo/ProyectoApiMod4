package client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestPut implements IRequest{
    @Override
    public Response send(RequestInfo info) {
        return given()
                    .header("Token",info.getToken())
                    .contentType("application/json")
                    .body(info.getBody())

                .when()
                    .put(info.getUrl());
    }
}
