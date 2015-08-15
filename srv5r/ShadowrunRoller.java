/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srv5r;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author amayas
 */
public class ShadowrunRoller {
    private static Window w;
    private static Scanner in;
    public static void main(String[] args) {
        w = null;
        if(System.console() == null) {
            if(!GraphicsEnvironment.isHeadless()) {
                w = new Window();
            } else
                System.exit(1);
        }
        System.out.println("Shadowrun v5 Dice Roller. Never make a deal with a dragon.");
        String input;
        in = new Scanner(System.in);
        try {
        do {
            System.out.println();
                System.out.print('#');
            if(w == null)
                input = in.nextLine();
            else {
                System.out.flush();
                w.scroll();
                input = w.input();
                System.out.println(input);
            }
            String[] split = input.split("\\s+");
            if(split.length == 0)
                continue;
            switch (split[0]) {
                case "roll":
                    String test = "";
                    for(int i = 1; i < split.length; ++i)
                        test += split[i];
                    if(test.isEmpty())
                        System.out.println("Error: No parameters found for roll.");
                    else
                        TestHandler.testSimple(test);
                    break;
                case "raw":
                    if(split.length == 1) {
                        System.out.println("Error: No dice count found for roll.");
                        continue;
                    }
                    for(int i = 0; i < Parser.sum(split[1]); ++i)
                        System.out.print(Roller.roll(1) + " ");
                    break;
                case "dice":
                    if(split.length == 1) {
                        System.out.println("Error: No dice count found for roll.");
                        continue;
                    }
                    System.out.println("Roll: " + Roller.roll(Parser.sum(split[1])));
                    break;
                case "test":
                    String[] tests = Parser.testOpposed(split);
                    if(tests[0].isEmpty())
                        System.out.println("Error: Missing parameters for tester.");
                    else if(tests[1].isEmpty())
                        System.out.println("Error: Missing parameters for opponent.");
                    else
                        TestHandler.testOpposed(tests[0], tests[1]);
                    break;
                case "attack":
                    String[] attack = Parser.testOpposed(split);
                    System.err.println(Arrays.toString(attack));
                    if(attack[0].isEmpty()) {
                        System.out.println("Error: Missing parameters for attacker.");
                        continue;
                    }
                    if(attack[1].isEmpty()) {
                        System.out.println("Error: Missing parameters for defender.");
                        continue;
                    }
                    TestHandler.attack(attack[0], attack[1]);
                    break;
                case "damage":
                    if(split.length < 5)
                        System.out.println("Error: Insufficient parameters");
                    else
                        TestHandler.damage(0, split[1], split[2], split[3], split[4]);
                    break;
                case "help":
                    w.clear();
                    HelpPrinter.printHelp();
                    break;
                case "cls":
                    w.clear();
                    break;
                default:
                    System.out.println("Error: Unknown command '" + split[0] + "'.");
            }
        } while(!input.contentEquals("exit") && !input.contentEquals("quit"));
        } catch (Exception e) {
            w.clear();
            System.out.println("FATAL ERROR");
            e.printStackTrace(System.out);
            System.out.println();
            System.out.println("Enter any command to exit...");
            w.input();
        }
        System.exit(0);
    }
    public static String getInput(String query) {
        System.out.print(query + "? ");
            System.out.flush();
        if(w != null) {
            w.scroll();
            String retval = w.input();
            System.out.println(retval);
            return retval;
        } else
            return in.nextLine();
    }
}
