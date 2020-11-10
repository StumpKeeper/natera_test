package com.natera.qa.triangle.model;

import com.natera.qa.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TriangleInput {

    private String separator;
    private String input;

    public static TriangleInput fromTriangle(Triangle triangle, String separator) {
        List<Object> sideValues = List.of(triangle.getFirstSide(), triangle.getSecondSide(), triangle.getThirdSide());
        String input = CollectionUtils.joinToString(sideValues, separator);
        return new TriangleInput(separator, input);
    }

}
