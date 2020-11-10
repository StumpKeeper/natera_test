package com.natera.qa.unit;

import com.natera.qa.utils.CollectionUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CollectionUtilsTest {

    @Test
    public void joinToStringTest1() {
        List<Object> objects = List.of();
        String result = CollectionUtils.joinToString(objects, ",");
        Assert.assertEquals(result, "");
    }

    @Test
    public void joinToStringTest2() {
        List<Object> objects = List.of(2.5D, 5.2F, 8);
        String result = CollectionUtils.joinToString(objects, ",");
        Assert.assertEquals(result, "2.5,5.2,8");
    }
}
