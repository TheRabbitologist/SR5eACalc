/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package server;

import client.SR5eAC;

public class TestHandler {

    public static int testSimple(String input, boolean ptl) {
        int[] testvals = Parser.testSingle(input);
        int res;
        if (testvals.length == 3) {
            res = Roller.test(testvals[0], testvals[1], testvals[2], ptl);
            SR5eAC.setMisses(Math.min(testvals[0], testvals[1]) - res);
            if (res >= 0)
                System.out.println("Passed: +" + res);
            else
                System.out.println("Failed: " + (testvals[2] + res));
            res = testvals[2] + res; //For glitch testing.
        } else {
            if (testvals.length == 2) {
                res = Roller.hits(testvals[0], testvals[1], ptl);
                SR5eAC.setMisses(Math.min(testvals[0], testvals[1]) - res);
            } else {
                res = Roller.hits(testvals[0], ptl);
                SR5eAC.setMisses(testvals[0] - res);
            }
            System.out.println("Hits: " + res);
        }
        if (Roller.glitched()) {
            if (res == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        return res;
    }

    public static int testOpposed(String attack, String target, boolean ptl) {
        int[] atk = Parser.testSingle(attack);
        int[] def = Parser.testSingle(target);
        int resA, resB, res;
        boolean glitchA, glitchB;
        if (atk.length >= 2) {
            resA = Roller.hits(atk[0], atk[1], ptl);
            SR5eAC.setMisses(Math.min(atk[0], atk[1]) - resA);
        } else {
            resA = Roller.hits(atk[0], ptl);
            SR5eAC.setMisses(atk[0] - resA);
        }
        glitchA = Roller.glitched();
        if (def.length >= 2)
            resB = Roller.hits(def[0], def[1]);
        else
            resB = Roller.hits(def[0]);
        glitchB = Roller.glitched();
        res = resA - resB;
        if (res > 0)
            System.out.println("Passed: +" + res);
        else if (res < 0)
            System.out.println("Failed: " + res);
        else
            System.out.println("Tied");
        if (glitchA) {
            if (resA == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        if (glitchB) {
            System.out.print("OPPONENT ");
            if (resB == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        return res;
    }

    public static int attack(String attack, String target) {
        int[] atk = Parser.testSingle(attack);
        int[] def = Parser.testSingle(target);
        int resA, resB, res;
        boolean glitchA, glitchB;
        if (atk.length >= 2) {
            resA = Roller.hits(atk[0], atk[1]);
            SR5eAC.setMisses(Math.min(atk[0], atk[1]) - resA);
        } else {
            resA = Roller.hits(atk[0]);
            SR5eAC.setMisses(atk[0] - resA);
        }
        glitchA = Roller.glitched();
        if (def.length >= 2)
            resB = Roller.hits(def[0], def[1]);
        else
            resB = Roller.hits(def[0]);
        glitchB = Roller.glitched();
        res = resA - resB;
        if (res < 0)
            System.out.println("Missed: " + res);
        else if (res == 0)
            System.out.println("Grazed");
        else
            System.out.println("Hit: +" + res);
        if (glitchA) {
            System.out.print("ATTACKING ");
            if (resA == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        if (glitchB) {
            System.out.print("DEFENDING ");
            if (resB == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        String resp = SR5eAC.getInput("Continue");
        if (resp.toUpperCase().charAt(0) != 'Y')
            return res;
        String dmg = SR5eAC.getInput("Damage Parameters");
        String[] dp = dmg.split("\\s+");
        if (dp.length < 4) {
            System.out.println("Error: Insufficient parameters.");
            return res;
        }
        return damage(res, dp[0], dp[1], dp[2], dp[3]);
    }

    public static int damage(int res, String dmg, String ap, String armor, String body) {
        int atk = Parser.sum(dmg.substring(0, dmg.length() - 1)) + res;
        int arm = Parser.sum(armor);
        int bod = Parser.sum(body);
        if (arm > 0)
            arm = Math.max(0, arm + Parser.sum(ap));
        char type;
        if (atk >= arm) {
            type = Character.toUpperCase(dmg.charAt(dmg.length() - 1));
            if (type != 'P' && type != 'S')
                type = '?';
        } else
            type = 'S';
        int def = Roller.hits(arm + bod);
        SR5eAC.setMisses(arm + bod - def);
        atk -= def;
        if (atk <= 0)
            System.out.println("Blocked: " + (atk + def) + type + " v. " + def);
        else
            System.out.println("Damage: " + atk + type);
        if (Roller.glitched()) {
            System.out.print("ARMOR ");
            if (def == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        return 0;
    }
}
