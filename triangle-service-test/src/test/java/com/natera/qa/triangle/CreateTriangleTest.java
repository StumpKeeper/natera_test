package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.natera.qa.triangle.service.TriangleService.MAXIMUM_TRIANGLE_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateTriangleTest extends BaseTriangleTest {

    @Test
    public void createValidTriangleTest() {
        var validTestTriangle = Triangle.randomValid();
        var createdTriangle = TriangleService.createTriangle(validTestTriangle);

        assertThat(createdTriangle)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(validTestTriangle);
    }

    @Test(dataProvider = "invalidTriangleProvider")
    public void createInvalidTriangleTest(Triangle invalidTriangle) {
        TriangleService.createTriangle(invalidTriangle, 422);
    }

    @Test
    public void createMaximumTrianglesAmountTest() {
        for (int i = 0; i < MAXIMUM_TRIANGLE_AMOUNT + 1; i++) {
            TriangleService.createTriangle(Triangle.randomValid());
        }

        Assert.assertEquals(TriangleService.getAllTriangles().size(), MAXIMUM_TRIANGLE_AMOUNT);
    }

    @DataProvider
    private Object[] invalidTriangleProvider() {
        return new Object[]{
                Triangle.builder().firstSide(11D).secondSide(22D).thirdSide(33D).build(),
                Triangle.builder().firstSide(0D).secondSide(22D).thirdSide(33D).build(),
                Triangle.builder().firstSide(11D).secondSide(0D).thirdSide(33D).build(),
                Triangle.builder().firstSide(11D).secondSide(22D).thirdSide(0D).build(),
                Triangle.builder().firstSide(-11D).secondSide(22D).thirdSide(33D).build(),
                Triangle.builder().firstSide(11D).secondSide(-22D).thirdSide(33D).build(),
                Triangle.builder().firstSide(11D).secondSide(22D).thirdSide(-33D).build(),
                Triangle.builder().firstSide(0D).secondSide(22D).thirdSide(32D).build(),
                Triangle.builder().firstSide(11D).secondSide(0D).thirdSide(32D).build(),
                Triangle.builder().firstSide(11D).secondSide(22D).thirdSide(0D).build(),
                Triangle.builder().firstSide(Double.MAX_VALUE).secondSide(Double.MAX_VALUE).thirdSide(Double.MAX_VALUE).build(),
        };
    }
}
