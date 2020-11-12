package com.natera.qa.triangle;

import com.natera.qa.triangle.service.TriangleService;
import com.natera.qa.utils.MathUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;

class BaseTriangleTest {

    @BeforeMethod
    public void cleanDb() {
        //TODO Could be replaced with direct table drop to remove dependency from API logic
        TriangleService.deleteAllTriangles();
        Assert.assertTrue(TriangleService.getAllTriangles().isEmpty(), "BeforeSuite - DB cleanup failed...");
    }

    protected BigDecimal roundDouble(double value) {
        return BigDecimal.valueOf(value).round(MathUtils.DEFAULT_MATH_CONTEXT);
    }

}
