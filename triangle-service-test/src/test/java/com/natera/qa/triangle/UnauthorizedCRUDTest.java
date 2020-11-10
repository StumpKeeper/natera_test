package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

public class UnauthorizedCRUDTest {

    @Test
    public void checkCreateTriangleUnauthorized() {
        Triangle validTriangle = Triangle.randomValid();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(validTriangle)
                .post(TriangleService.BASE_URL)
                .then()
                .statusCode(401);
    }

    @Test(dataProvider = "getEndpointProvider")
    public void checkGetTriangleUnauthorized(String endpoint) {
        RestAssured
                .given()
                .baseUri(TriangleService.BASE_URL)
                .get(endpoint)
                .then()
                .statusCode(401);
    }

    @Test
    public void checkDeleteTriangleUnauthorized() {
        RestAssured
                .given()
                .baseUri(TriangleService.BASE_URL)
                .delete(UUID.randomUUID().toString())
                .then()
                .statusCode(401);
    }

    @DataProvider
    private Object[] getEndpointProvider() {
        return new Object[]{
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString() + "/all",
                UUID.randomUUID().toString() + "/area",
                UUID.randomUUID().toString() + "/perimeter"
        };
    }
}
