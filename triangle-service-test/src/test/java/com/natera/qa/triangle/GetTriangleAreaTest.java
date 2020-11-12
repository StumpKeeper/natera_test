package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class GetTriangleAreaTest extends BaseTriangleTest {

    @Test
    public void checkTriangleAreaTest() {
        Triangle triangle = Triangle.randomValid();
        Triangle createdTriangle = TriangleService.createTriangle(triangle);

        BigDecimal actualArea = roundDouble(TriangleService.getTriangleArea(createdTriangle.getId()));
        BigDecimal expectedArea = roundDouble(triangle.area());

        Assert.assertEquals(expectedArea, actualArea);
    }
}
