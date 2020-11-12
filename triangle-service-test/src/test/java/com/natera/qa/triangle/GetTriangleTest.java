package com.natera.qa.triangle;

import com.natera.qa.triangle.model.Triangle;
import com.natera.qa.triangle.service.TriangleService;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTriangleTest extends BaseTriangleTest {

    @Test
    public void getTriangleTest() {
        var validTestTriangle = Triangle.randomValid();
        var createdTriangle = TriangleService.createTriangle(validTestTriangle);

        assertThat(TriangleService.getTriangle(createdTriangle.getId()))
                .isEqualTo(createdTriangle);
    }

    @Test
    public void getMissingTriangleTest() {
        TriangleService.getTriangle(UUID.randomUUID(), 404);
    }
}
