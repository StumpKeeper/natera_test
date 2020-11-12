package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class GetTrianglePerimeterTest extends BaseTriangleTest {

    @Test
    public void checkTrianglePerimeterTest() {
        Triangle triangle = Triangle.randomValid();
        Triangle createdTriangle = TriangleService.createTriangle(triangle);

        BigDecimal actualArea = roundDouble(TriangleService.getTrianglePerimeter(createdTriangle.getId()));
        BigDecimal expectedArea = roundDouble(triangle.perimeter());

        Assert.assertEquals(expectedArea, actualArea);
    }
}
