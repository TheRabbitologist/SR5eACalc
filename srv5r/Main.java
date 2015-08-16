/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static final String NAME = "Shadowrun 5e Advanced Calculator";

    private static Window w;
    private static Scanner in;
    private static Memory elephant;

    public static Memory getMemory() {
        return elephant;
    }

    public static Entity getSelf() {
        return elephant.self;
    }

    public static Window getWindow() {
        return w;
    }

    public static void main(String[] args) {
        w = null;
        elephant = new Memory();
        if (System.console() == null) {
            if (!GraphicsEnvironment.isHeadless()) {
                w = new Window();
            } else
                System.exit(1);
        }
        System.out.println(NAME+". Never make a deal with a dragon.");
        System.out.println("For help, enter 'help'. For copyright info, enter 'copyright'.");
        System.out.println("Enter command...");
        String input;
        in = new Scanner(System.in);
        try {
            do {
                System.out.println();
                System.out.print('#');
                if (w == null)
                    input = in.nextLine();
                else {
                    System.out.flush();
                    w.scroll();
                    input = w.input();
                    System.out.println(input);
                }
                String[] split = input.split("\\s+");
                if (split.length == 0)
                    continue;
                switch (split[0]) {
                    case "calc":
                        String calc = "";
                        for (int i = 1; i < split.length; ++i)
                            calc += split[i];
                        if (calc.isEmpty())
                            System.out.println("Error: Missing parameters for calculation.");
                        else
                            System.out.println("Result: "+Parser.sum(calc));
                        break;
                    case "test":
                        String[] tests = Parser.testOpposed(split);
                        if (tests[1] != null) {
                            if(tests[1].isEmpty())
                                System.out.println("Error: Missing parameters for opponent.");
                            else
                                TestHandler.testOpposed(tests[0], tests[1]);
                            break;
                        } else if(tests[0].isEmpty()) {
                            System.out.println("Error: Missing parameters for test.");
                            break;
                        }
                    case "roll":
                        String test = "";
                        for (int i = 1; i < split.length; ++i)
                            test += split[i];
                        if (test.isEmpty())
                            System.out.println("Error: Missing parameters for roll.");
                        else
                            TestHandler.testSimple(test);
                        break;
                    case "raw":
                        if (split.length == 1) {
                            System.out.println("Error: Missing dice count for roll.");
                            continue;
                        }
                        for (int i = 0; i < Parser.sum(split[1]); ++i)
                            System.out.print(Roller.roll(1) + " ");
                        System.out.println();
                        break;
                    case "dice":
                        if (split.length == 1) {
                            System.out.println("Error: Missing dice count for roll.");
                            continue;
                        }
                        System.out.println("Roll: " + Roller.roll(Parser.sum(split[1])));
                        break;
                    case "attack":
                        String[] attack = Parser.testOpposed(split);
                        System.err.println(Arrays.toString(attack));
                        if (attack[0].isEmpty()) {
                            System.out.println("Error: Missing parameters for attacker.");
                            continue;
                        }
                        if (attack[1] == null || attack[1].isEmpty()) {
                            System.out.println("Error: Missing parameters for defender.");
                            continue;
                        }
                        TestHandler.attack(attack[0], attack[1]);
                        break;
                    case "damage":
                        if (split.length < 5)
                            System.out.println("Error: Insufficient parameters.");
                        else
                            TestHandler.damage(0, split[1], split[2], split[3], split[4]);
                        break;
                    case "set":
                        if (split.length < 2)
                            System.out.println("Error: Missing register for set operation.");
                        else
                            switch (split[1]) {
                                case "self":
                                    getSelf().set(Arrays.copyOfRange(split, 2, split.length));
                                    break;
                                default:
                                    System.out.println("Error: Unknown register '" + split[1] + "'.");
                            }
                        break;
                    case "get":
                        if (split.length < 2)
                            System.out.println("Error: Missing register for get operation.");
                        else
                            switch (split[1]) {
                                case "self":
                                    System.out.println(split[2]+": "+getSelf().get(split[2]));
                                    break;
                                default:
                                    System.out.println("Error: Unknown register '" + split[1] + "'.");
                            }
                        break;
                    case "help":
                        w.clear();
                        HelpPrinter.printHelp();
                        break;
                    case "copyright":
                        w.clear();
                        HelpPrinter.printCopyright();
                        break;
                    case "cls":
                        w.clear();
                        System.out.println("Enter command...");
                        break;
                    default:
                        System.out.println("Error: Unknown command '" + split[0] + "'.");
                }
            } while (!input.contentEquals("exit") && !input.contentEquals("quit"));
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
        String retval;
        System.out.flush();
        if (w != null) {
            w.scroll();
            retval = w.input();
            System.out.println(retval);
        } else 
            retval = in.nextLine();
        return retval;
    }
}
