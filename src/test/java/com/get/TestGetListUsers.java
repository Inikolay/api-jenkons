package com.get;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.specifications.Specifications.specForGet;
import static io.restassured.RestAssured.given;

public class TestGetListUsers {

    @Test
    public void testGet(){
        given()
                .spec(specForGet())
                .when()
                .log().all()
                .get("/users?page=2")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}
