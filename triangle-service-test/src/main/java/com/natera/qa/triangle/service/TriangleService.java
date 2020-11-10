package com.natera.qa.triangle.service;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.model.TriangleInput;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.UUID;

public class TriangleService {

    public static final String BASE_URL = "https://qa-quiz.natera.com/triangle/";
    private static final String DEFAULT_USER_TOKEN = "2f8c29ff-110f-4b7b-acb0-9fe53ddda592";

    public static List<Triangle> getAllTriangles() {
        return List.of(RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .get("all")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Triangle[].class));
    }

    public static Triangle createTriangle(Triangle triangle) {
        return createTriangle(triangle, 200);
    }

    public static Triangle createTriangle(Triangle triangle, int expectedStatus) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .contentType(ContentType.JSON)
                .body(TriangleInput.fromTriangle(triangle, ";"))
                .post()
                .then()
                .statusCode(expectedStatus)
                .extract()
                .body()
                .as(Triangle.class);
    }

    public static boolean deleteTriangle(UUID id) {
        int responseStatus = RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .delete(id.toString())
                .getStatusCode();
        return responseStatus == 200;
    }

    public static RequestSpecification getAuthorizedRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("X-User", DEFAULT_USER_TOKEN)
                .build();
    }
}
