package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import org.junit.Assert;
import org.testng.annotations.Test;

public class DeleteTriangleTest extends BaseTriangleTest {

    @Test
    public void deleteTriangleTest() {
        var validTestTriangle = Triangle.randomValid();
        var createdTriangle = TriangleService.createTriangle(validTestTriangle);

        Assert.assertTrue(TriangleService.deleteTriangle(createdTriangle.getId()));
        TriangleService.getTriangle(createdTriangle.getId(), 404);
    }
}
