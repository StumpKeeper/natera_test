package com.natera.qa.utils;

import java.util.Collection;

public class CollectionUtils {

    public static String joinToString(Collection<Object> objects, String separator) {
        StringBuilder result = new StringBuilder();
        var objectsArray = objects.toArray();

        for (int i = 0; i < objectsArray.length; i++) {
            result.append(objectsArray[i]);
            if (i != objectsArray.length - 1) {
                result.append(separator);
            }
        }
        return result.toString();
    }
}
