package com.natera.qa.triangle;

import com.natera.qa.triangle.model.TriangleInput;
import com.natera.qa.triangle.service.TriangleService;
import com.natera.qa.utils.CollectionUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TriangleInputTest extends BaseTriangleTest {

    @Test(dataProvider = "triangleInputProvider")
    public void checkTriangleRawInputTest(TriangleInput triangleInput, int expectedStatus) {
        TriangleService.createTriangle(triangleInput, expectedStatus);
    }

    @Test
    public void checkDefaultSeparatorValueTest() {
        String input = CollectionUtils
                .joinToString(List.of("11.11", "22.22", "33.11"), TriangleService.DEFAULT_SEPARATOR);
        TriangleService.createTriangle(new TriangleInput(null, input), 200);
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
                {new TriangleInput(null, "11.11,22.22,33.11"), 422},
                {new TriangleInput(".", "11.11.22.22.33.11"), 422},
                {new TriangleInput(",", "11.11;22.22;33.11"), 422}
        };
    }
}
