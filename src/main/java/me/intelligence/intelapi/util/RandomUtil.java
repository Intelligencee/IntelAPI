package me.intelligence.intelapi.util;

import java.util.Random;

public class RandomUtil {

    private static Random random;

    public static Random getRandom() {
        return RandomUtil.random;
    }

    static {
        RandomUtil.random = new Random();
    }
}
