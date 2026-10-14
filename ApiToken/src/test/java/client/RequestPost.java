package client;

import config.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestPost implements IRequest{
    @Override
    public Response send(RequestInfo info) {
        if (info.getUrl().equals(Configuration.BASE_URL + "api/user.json")) {
            return given()
                    .contentType("application/json")
                    .body(info.getBody())
                .when()
                    .post(info.getUrl());
        }
        return given()
                    .header("Token",info.getToken())
                    .contentType("application/json")
                    .body(info.getBody())

                .when()
                    .post(info.getUrl());
    }
}
