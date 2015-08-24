/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package client;

import java.util.Random;

public class Roller {
    private static boolean glitch = false;
    private static Random r = new Random();
    
    public static boolean glitched() {return glitch;}
    public static int hits(int dice, boolean ro6) {
        int hits = 0;
        int zeroes = 0;
        glitch = false;
        for(int i = 0; i < dice; ++i) {
            int roll = r.nextInt(6);
            if(ro6 && roll == 5) {
                ++hits;
                roll = r.nextInt(6);
            }
            if(roll >= 4)
                ++hits;
            else if(roll == 0)
                ++zeroes;
        }
        if(zeroes > dice/2)
            glitch = true;
        return hits;
    }
    public static int hits(int dice) {
        return hits(dice,false);
    }
    public static int hits(int dice, int limit) {
        return hits(dice,limit,false);
    }
    public static int hits(int dice, int limit, boolean ro6) {
        return Math.min(hits(dice, ro6), limit);
    }
    public static int roll(int dice) {
        int val = 0;
        for(int i = 0; i < dice; ++i)
            val += 1+r.nextInt(6);
        return val;
    }
    public static int test(int dice, int limit, int thresh) {
        return test(dice,limit,thresh,false);
    }
    public static int test(int dice, int limit, int thresh, boolean ro6) {
        return hits(dice,limit,ro6)-thresh;
    }
    public static int test(int diceA, int limitA, int diceB, int limitB) {
        return test(diceA, limitA, diceB, limitB, false, false);
    }
    public static int test(int diceA, int limitA, int diceB, int limitB, boolean ro6A) {
        return test(diceA, limitA, diceB, limitB, ro6A, false);
    }
    public static int test(int diceA, int limitA, int diceB, int limitB, boolean ro6A, boolean ro6B) {
        return hits(diceA,limitA,ro6A)-hits(diceB,limitB,ro6B);
    }
}
