package com.topschool.xm.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void generationRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.generationRandom(3, 5));
        }
    }
}