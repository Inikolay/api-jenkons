package com.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    public static RequestSpecification specForGet() {
        return new RequestSpecBuilder()
                .setBaseUri("")
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .addHeader("User-Agent", "BSK_API_TESTS")
                .build()
                .filter(new AllureRestAssured());
    }
}
