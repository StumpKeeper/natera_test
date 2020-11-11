package com.natera.qa.triangle;

import com.natera.qa.triangle.service.TriangleService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

class BaseTriangleTest {

    @BeforeMethod
    public void cleanDb() {
        //TODO Could be replaced with direct table drop to remove dependency from API logic
        TriangleService.deleteAllTriangles();
        Assert.assertTrue(TriangleService.getAllTriangles().isEmpty(), "BeforeSuite - DB cleanup failed...");
    }

}
