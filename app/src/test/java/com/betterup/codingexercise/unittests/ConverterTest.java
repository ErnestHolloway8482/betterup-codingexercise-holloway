package com.betterup.codingexercise.unittests;

import com.betterup.codingexercise.BaseUnitTest;
import com.betterup.codingexercise.bindings.Converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ConverterTest extends BaseUnitTest {

    @Test
    public void convertStringToIntegerTest() {
        Assert.assertEquals(10, Converter.convertStringToInteger("10"));
        Assert.assertEquals(0, Converter.convertStringToInteger("ee"));
    }

    @Test
    public void convertIntegerToStringTest() {
        Assert.assertEquals("10", Converter.convertIntegerToString(10));
    }
}
