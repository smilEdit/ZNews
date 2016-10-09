package com.zzz.news.widegt;

import java.util.Random;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 9:19
 */

public class MathHelper {
    public static Random rand = new Random();
    public static float randomRange(float min, float max) {


        int randomNum = rand.nextInt(((int) max - (int) min) + 1) + (int) min;

        return randomNum;
    }
}
