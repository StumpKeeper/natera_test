package com.natera.qa.unit;

import com.natera.qa.triangle.model.Triangle;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

    @Test
    public void checkTriangleValidation() {
        Triangle validTriangle = Triangle.builder().firstSide(10.5).secondSide(12.3).thirdSide(21.3).build();
        Triangle invalidTriangle = Triangle.builder().firstSide(10.5).secondSide(20.3).thirdSide(40.1).build();

        Assert.assertTrue(validTriangle.isValid());
        Assert.assertFalse(invalidTriangle.isValid());
    }

    @Test
    public void checkValidTriangleGeneration() {
        Assert.assertTrue(Triangle.randomValid().isValid());
    }

    @Test
    public void checkTriangleAreaCalculation() {
        Triangle validTriangle = Triangle.builder().firstSide(10.5).secondSide(12.3).thirdSide(21.3).build();
        double validArea = 43.154712590283815;

        Assert.assertEquals(validTriangle.area(), validArea);
    }

    @Test
    public void checkTrianglePerimeterCalculation() {
        Triangle validTriangle = Triangle.builder().firstSide(70.5).secondSide(32.3).thirdSide(83.7).build();
        double validPerimeter = 186.5;

        Assert.assertEquals(validTriangle.perimeter(), validPerimeter);
    }
}
