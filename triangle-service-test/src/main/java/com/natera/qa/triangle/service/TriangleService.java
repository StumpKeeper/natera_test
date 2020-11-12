package com.natera.qa.triangle.service;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.model.TriangleInput;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.UUID;

public class TriangleService {

    public static final String BASE_URL = "https://qa-quiz.natera.com/triangle/";
    public static final int MAXIMUM_TRIANGLE_AMOUNT = 10;
    private static final String DEFAULT_USER_TOKEN = "2f8c29ff-110f-4b7b-acb0-9fe53ddda592";

    public static void deleteAllTriangles() {
        TriangleService
                .getAllTriangles()
                .forEach(triangle -> TriangleService.deleteTriangle(triangle.getId()));
    }

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

    public static Triangle getTriangle(UUID id) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .get(id.toString())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Triangle.class);
    }

    public static ValidatableResponse getTriangle(UUID id, int expectedStatus) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .get(id.toString())
                .then()
                .statusCode(expectedStatus);
    }

    public static Double getTriangleArea(UUID id) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .get(id.toString() + "/area")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getDouble("result");
    }

    public static Double getTrianglePerimeter(UUID id) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .get(id.toString() + "/perimeter")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getDouble("result");
    }

    public static Triangle createTriangle(Triangle triangle) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .contentType(ContentType.JSON)
                .body(TriangleInput.fromTriangle(triangle, ";"))
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Triangle.class);
    }

    public static ValidatableResponse createTriangle(Triangle triangle, int expectedStatus) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .contentType(ContentType.JSON)
                .body(TriangleInput.fromTriangle(triangle, ";"))
                .post()
                .then()
                .statusCode(expectedStatus);
    }

    public static ValidatableResponse createTriangle(TriangleInput triangleInput, int expectedStatus) {
        return RestAssured
                .given()
                .spec(getAuthorizedRequestSpec())
                .contentType(ContentType.JSON)
                .body(triangleInput)
                .post()
                .then()
                .statusCode(expectedStatus);
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
