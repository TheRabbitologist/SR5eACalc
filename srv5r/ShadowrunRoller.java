/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srv5r;

import java.awt.GraphicsEnvironment;
import java.util.Scanner;

/**
 *
 * @author amayas
 */
public class ShadowrunRoller {

    public static void main(String[] args) {
        Window w = null;
        if(System.console() == null) {
            if(!GraphicsEnvironment.isHeadless()) {
                w = new Window();
            } else
                System.exit(1);
        }
        System.out.println("Shadowrun v5 Dice Roller. Never make a deal with a dragon.");
        System.out.println();
        String input;
        Scanner in = new Scanner(System.in);
        do {
            if(w == null) {
                System.out.print('#');
                input = in.nextLine();
            }
            else {
                input = w.input();
                System.out.println('#'+input);
            }
            String[] split = input.split("\\s+");
            if(split.length == 0)
                continue;
            switch (split[0]) {
                case "roll":
                    String test = "";
                    for(int i = 1; i < split.length; ++i)
                        test += split[i];
                    int[] testvals = Parser.testSingle(test);
                    int res;
                    if(testvals.length == 3) {
                        res = Roller.test(testvals[0], testvals[1], testvals[2]);
                        if(res >= 0)
                            System.out.println("Passed: +" + res);
                        else
                            System.out.println("Failed: " + (testvals[2]+res));
                        res = testvals[2]+res; //For glitch testing.
                    } else {
                        if(testvals.length == 2)
                            res = Roller.hits(testvals[0], testvals[1]);
                        else
                            res = Roller.hits(testvals[0]);
                        System.out.println("Hits: " + res);
                    }
                    if(Roller.glitched()) {
                        if(res == 0)
                            System.out.print("CRITICAL ");
                        System.out.println("GLITCH!");
                    }
                    System.out.println();
                    break;
                case "raw":
                    for(int i = 0; i < Parser.sum(split[1]); ++i)
                        System.out.print(Roller.roll(1) + " ");
                    System.out.println();
                    System.out.println();
                    break;
                case "dice":
                    System.out.println("Roll: " + Roller.roll(Parser.sum(split[1])));
                    System.out.println();
                    break;
                case "help":
                    System.out.println("Help? You're in the shadows now, buddy.");
                    System.out.println("Ask your resident guardian angel or something.");
                    System.out.println();
                    break;
            }
            
        } while(!input.contentEquals("exit") && !input.contentEquals("quit"));
        System.exit(0);
    }
    
}
