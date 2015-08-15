/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srv5r;

import java.util.Random;

public class Roller {
    private static boolean glitch = false;
    private static Random r = new Random();
    
    public static boolean glitched() {return glitch;}
    public static int hits(int dice) {
        int hits = 0;
        int zeroes = 0;
        glitch = false;
        for(int i = 0; i < dice; ++i) {
            int roll = r.nextInt(6);
            if(roll >= 4)
                ++hits;
            else if(roll == 0)
                ++zeroes;
        }
        if(zeroes > dice/2)
            glitch = true;
        return hits;
    }
    public static int hits(int dice, int limit) {
        return Math.min(hits(dice), limit);
    }
    public static int roll(int dice) {
        int val = 0;
        for(int i = 0; i < dice; ++i)
            val += 1+r.nextInt(6);
        return val;
    }
    public static int test(int dice, int limit, int thresh) {
        return hits(dice,limit)-thresh;
    }
    public static int test(int diceA, int limitA, int diceB, int limitB) {
        return hits(diceA,limitA)-hits(diceB,limitB);
    }
}
