/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srv5r;

/**
 *
 * @author amayas
 */
public class TestHandler {

    public static int testSimple(String input) {
        int[] testvals = Parser.testSingle(input);
        int res;
        if (testvals.length == 3) {
            res = Roller.test(testvals[0], testvals[1], testvals[2]);
            if (res >= 0)
                System.out.println("Passed: +" + res);
            else
                System.out.println("Failed: " + (testvals[2] + res));
            res = testvals[2] + res; //For glitch testing.
        } else {
            if (testvals.length == 2)
                res = Roller.hits(testvals[0], testvals[1]);
            else
                res = Roller.hits(testvals[0]);
            System.out.println("Hits: " + res);
        }
        if (Roller.glitched()) {
            if (res == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        return res;
    }

    public static int testOpposed(String attack, String target) {
        int[] atk = Parser.testSingle(attack);
        int[] def = Parser.testSingle(target);
        int resA, resB, res;
        boolean glitchA, glitchB;
        if (atk.length >= 2)
            resA = Roller.hits(atk[0], atk[1]);
        else
            resA = Roller.hits(atk[0]);
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

    public static boolean attack(String attack, String target) {
        int[] atk = Parser.testSingle(attack);
        int[] def = Parser.testSingle(target);
        int resA, resB, res;
        boolean glitchA, glitchB;
        if (atk.length >= 2)
            resA = Roller.hits(atk[0], atk[1]);
        else
            resA = Roller.hits(atk[0]);
        glitchA = Roller.glitched();
        if (def.length >= 2)
            resB = Roller.hits(def[0], def[1]);
        else
            resB = Roller.hits(def[0]);
        glitchB = Roller.glitched();
        res = resA - resB;
        while (true) {
            if (res < 0)
                System.out.println("Missed: " + res);
            else if (res == 0) {
                System.out.println("Grazed");
                String resp = ShadowrunRoller.getInput("Continue");
                if (resp.toUpperCase().charAt(0) == 'Y')
                    break;
            } else {
                System.out.println("Hit: +" + res);
                break;
            }
            return false;
        }
        if (glitchA) {
            System.out.print("ATTACKING ");
            if (resA == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
            String resp = ShadowrunRoller.getInput("Continue");
            if (resp.toUpperCase().charAt(0) != 'Y')
                return false;
        }
        if (glitchB) {
            System.out.print("DEFENDING ");
            if (resB == 0)
                System.out.print("CRITICAL ");
            System.out.println("GLITCH!");
        }
        String dmg = ShadowrunRoller.getInput("Damage Parameters");
        String[] dp = dmg.split("\\s+");
        if(dp.length < 4) {
            System.out.println("Error: Insufficient parameters.");
            return false;
        }
        damage(res, dp[0], dp[1], dp[2], dp[3]);
        return true;
    }
    
    public static void damage(int res, String dmg, String ap, String armor, String body) {
        int atk = Parser.sum(dmg.substring(0,dmg.length()-1))+res;
        int arm = Parser.sum(armor);
        int bod = Parser.sum(body);
        if(arm > 0)
            arm = Math.max(0,arm+Parser.sum(ap));
        char type;
        if(atk >= arm)
            type = Character.toUpperCase(dmg.charAt(dmg.length()-1));
        else
            type = 'S';
        atk -= Roller.hits(arm+bod);
        if(atk <= 0)
            System.out.println("Blocked.");
        else
            System.out.println(""+atk+type);
    }
}
