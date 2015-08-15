/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srv5r;

public class HelpPrinter {
    public static void printHelp() {
        System.out.println("---Command Reference---");
        System.out.println();
        System.out.println("In place of single-letter placeholders, a basic sum can be used.");
        System.out.println("\tExamples: 9+5 or 8 or 4-2-1 or -8+5+4");
        System.out.println("\tOnly subtraction and addition are supported..");
        System.out.println();
        System.out.println("raw <n>: Roll nd6, display results of each roll.");
        System.out.println("dice <n>: Roll nd6, display sum of rolls.");
        System.out.println("roll <test>: Perform a certain roll or success test...");
        System.out.println("\tn: Roll nd6, display hits.");
        System.out.println("\tn[l]: Roll nd6 with limit l, display hits.");
        System.out.println("\tn(t): Attempt a success test with threshold t.");
        System.out.println("\tn(t)[l]: Attempt a success test with threshold t and limit l.");
        System.out.println("\tGlitch status is printed in all cases where applicable.");
        System.out.println("test <atest> v. <btest>: Perform an opposed test.");
        System.out.println("\tatest and btest should be of form n or n[l].");
        System.out.println("attack <atk> v. <def>: Perform an attack roll sequence.");
        System.out.println("\tIf the attack hits, a damage roll will be performed.");
        System.out.println("damage <dmg> <ap> <target_armor> <target_body>: Calculate damage done.");
        System.out.println("\t<damage>: Should be of the form nP or nS for physical or stun damage.");
        System.out.println("\tOutput also indicates damage type.");
        System.out.println("cls: Clear the screen.");
    }
}
