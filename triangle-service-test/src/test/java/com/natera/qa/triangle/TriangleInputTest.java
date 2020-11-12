package com.natera.qa.triangle;

import com.natera.qa.triangle.model.TriangleInput;
import com.natera.qa.triangle.service.TriangleService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleInputTest {

    @BeforeClass
    public void cleanUp() {
        TriangleService.deleteAllTriangles();
    }

    @Test(dataProvider = "triangleInputProvider")
    public void checkTriangleRawInputTest(TriangleInput triangleInput, int expectedStatus) {
        TriangleService.createTriangle(triangleInput, expectedStatus);
    }

    @DataProvider
    private Object[][] triangleInputProvider() {
        return new Object[][]{
                {new TriangleInput(null, null), 422},
                {new TriangleInput("null", null), 422},
                {new TriangleInput(null, "null"), 422},
                {new TriangleInput("", ""), 422},
                {new TriangleInput(";", ""), 422},
                {new TriangleInput(";", "123"), 422},
                {new TriangleInput(null, "11.11;22.22;33.11"), 200},
                {new TriangleInput(null, "11.11,22.22,33.11"), 422},
                {new TriangleInput(".", "11.11.22.22.33.11"), 422},
                {new TriangleInput(",", "11.11;22.22;33.11"), 422}
        };
    }
}
