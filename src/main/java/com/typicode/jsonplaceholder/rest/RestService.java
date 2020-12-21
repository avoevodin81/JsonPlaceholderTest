package com.typicode.jsonplaceholder.rest;

import com.typicode.jsonplaceholder.utils.PropertyUtils;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Main Rest Service class
 */
public class RestService {
    protected RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(PropertyUtils.getPropertyData(PropertyUtils.URL_HOST))
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();
}
