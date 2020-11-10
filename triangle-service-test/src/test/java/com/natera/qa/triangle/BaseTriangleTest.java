package com.natera.qa.triangle;

import com.natera.qa.triangle.service.TriangleService;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class BaseTriangleTest {

    @BeforeSuite
    public void cleanDb() {
        //TODO Could be replaced with direct table drop to remove dependency from API logic
        TriangleService
                .getAllTriangles()
                .forEach(triangle -> TriangleService.deleteTriangle(triangle.getId()));

        Assert.assertTrue(TriangleService.getAllTriangles().isEmpty(), "BeforeSuite - DB cleanup failed...");
    }

}
