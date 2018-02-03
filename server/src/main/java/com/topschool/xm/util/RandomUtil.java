package com.topschool.xm.util;

import java.util.Collection;
import java.util.Random;

public class RandomUtil {

    public static int generationRandom(int min, int max) {
        Random random = new Random();
        return Math.abs(random.nextInt() % (max + 1 - min)) + min;
    }
}
