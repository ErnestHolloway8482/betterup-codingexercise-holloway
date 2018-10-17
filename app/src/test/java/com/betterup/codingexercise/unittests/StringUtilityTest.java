package com.betterup.codingexercise.unittests;

import com.betterup.codingexercise.BaseUnitTest;
import com.betterup.codingexercise.utilities.StringUtility;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringUtilityTest extends BaseUnitTest {

    @Test
    public void stringIsEmptyTest() {
        //Null
        String string = null;
        Assert.assertTrue(StringUtility.isEmpty(string));

        //Empty
        string = "";
        Assert.assertTrue(StringUtility.isEmpty(string));
    }

    @Test
    public void stringIsNotEmptyTest() {
        String string = " ";
        Assert.assertFalse(StringUtility.isEmpty(string));

        //Empty
        string = "123";
        Assert.assertFalse(StringUtility.isEmpty(string));
    }
}
