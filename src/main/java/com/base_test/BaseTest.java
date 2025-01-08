package com.base_test;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;

public class BaseTest {

    @AfterClass
    public void closeSession() {
        RestAssured.reset();
    }

}
