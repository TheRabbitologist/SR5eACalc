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
                    TestHandler.testSimple(test);
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
                case "test":
                    String a = "", b = "";
                    boolean opponent = false;
                    for(int i = 1; i < split.length; ++i) {
                        if(split[i].contentEquals("v.")) {
                            opponent = true;
                            continue;
                        }
                        if(opponent)
                            b+=split[i];
                        else
                            a+=split[i];
                    }
                    if(a.isEmpty())
                        System.out.println("Error: No parameters found for opposed test.");
                    else if(b.isEmpty())
                        System.out.println("Error: Missing parameters for opponent.");
                    else
                        TestHandler.testOpposed(a, b);
                    System.out.println();
                    break;
                case "help":
                    System.out.println("Info: Help? You're in the shadows now, buddy.");
                    System.out.println();
                    break;
            }
            
        } while(!input.contentEquals("exit") && !input.contentEquals("quit"));
        System.exit(0);
    }
    
}
