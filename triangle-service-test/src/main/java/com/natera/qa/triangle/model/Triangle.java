package com.natera.qa.triangle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Triangle {

    private UUID id;
    private Double firstSide;
    private Double secondSide;
    private Double thirdSide;

    public static Triangle randomValid() {
        var rnd = new Random().doubles(1, 100).iterator();
        double firstSide = rnd.next();
        double secondSide = rnd.next();
        double thirdSide = (firstSide + secondSide) * 0.99;

        return Triangle
                .builder()
                .id(UUID.randomUUID())
                .firstSide(firstSide)
                .secondSide(secondSide)
                .thirdSide(thirdSide)
                .build();
    }

    @JsonIgnore
    public boolean isValid() {
        return (firstSide + secondSide) > thirdSide &&
                (secondSide + thirdSide) > firstSide &&
                (firstSide + thirdSide) > secondSide;
    }

    public double area() {
        double p = perimeter() / 2D;
        return Math.sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide));
    }

    public double perimeter() {
        return firstSide + secondSide + thirdSide;
    }
}
