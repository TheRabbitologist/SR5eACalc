/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

public class HelpPrinter {

    public static void printHelp() {
        System.out.println("---" + Main.NAME + " Manual/Reference---");
        System.out.println();
        System.out.println("In place of single-letter placeholders, a basic expression can be used.");
        System.out.println("\tExamples: 9+5 or 8 or 4-2-1 or -8+5*4");
        System.out.println("\tOnly addition, subtraction, and multiplication operations are supported.");
        System.out.println("\tMuliplication operations are, of course, done before addition and subtraction.");
        System.out.println("\tExpressions should generally not have spaces anywhere in them.");
        System.out.println("\tnd6 may be embedded in expressions to include a hit roll, ex. 5000+100*4d6");
        System.out.println("\tnr6 may similarly be used for a Rule of 6 roll, for Push The Limit.");
        System.out.println();
        System.out.println("Certain attributes and such can be stored and recalled.");
        System.out.println("These values can be used within expressions and tests, ex. CHA+WIL+2");
        System.out.println("\tAttributes: First three letters of the attribute name.");
        System.out.println("\tSpecial attributes: Same.");
        System.out.println("\tEssence: ESS (rounded down when used in expressions).");
        System.out.println("\tLimits: L + first letter of the limit name, ex. LS for the social limit.");
        System.out.println("\tMatrix Data Processing: MXP");
        System.out.println("\tMatrix Device Rating: MXR");
        System.out.println("\tOther Matrix Attributes: MX + first letter of the attribute name.");
        System.out.println("\tResult of the last roll: LAST");
        System.out.println("\tNumber of misses from the last roll: MISS");
        System.out.println("\tArmor value: ARMOR");
        System.out.println();
        System.out.println("raw <n>: Roll nd6, display results of each roll.");
        System.out.println("dice <n>: Roll nd6, display sum of rolls.");
        System.out.println("roll <rolltype>: Calculate hits.");
        System.out.println("\tn: Roll nd6, display hits.");
        System.out.println("\tn[l]: Roll nd6 with limit l, display hits.");
        System.out.println("\tnd6: This is probably not what you want.");
        System.out.println("\tGlitch status is printed in all cases where applicable.");
        System.out.println("test <test>: Perform a success or opposed test.");
        System.out.println("\tn(t): Attempt a success test with threshold t and no limit.");
        System.out.println("\tn(t)[l]: Attempt a success test with threshold t and limit l.");
        System.out.println("\ta v. b: Attempt an opposed test without limits.");
        System.out.println("\ta[l] v. b: Attempt an opposed test with the left side limited.");
        System.out.println("\ta[l] v. b[L]: Attempt an opposed test, each side with their own limit.");
        System.out.println("\tGlitch status is printed in all cases where applicable.");
        System.out.println("test-ro6 <test>: Perform a success or opposed test with Rule of 6 rolling rules.");
        System.out.println("\tRule of 6 is primarily used with the edge benefit Push The Limit.");
        System.out.println("\tNote that this command neither ignores limits nor adds edge rating.");
        System.out.println("reroll: Apply a second chance effect to the last roll.");
        System.out.println("\tRerolls all the dice from the last roll that did not result in a hit.");
        System.out.println("\tFor opposed tests, rerolls the dice of the left hand side.");
        System.out.println("calc <s>: Evaluate an expression, ignoring glitches.");
        System.out.println("attack <atk> v. <def>: Perform an ADA attack roll sequence.");
        System.out.println("\tIf the attack hits, a damage roll will be performed.");
        System.out.println("\tatk and def may be expressions.");
        System.out.println("damage <dmg> <ap> <target_armor> <target_body>: Calculate damage done and its type.");
        System.out.println("\t<damage>: Should be of the form nP or nS for physical or stun damage.");
        System.out.println("\tAll other arguments may be expressions.");
        System.out.println("\tNote that where other commands are lenient about spacing, this one is very strict.");
        System.out.println("\tRerolling displays additional damage resisted.");
        System.out.println("set self <attribute> <v>: Set an attribute's value.");
        System.out.println("\tLimits cannot be set, rather they are calculated from the attributes.");
        System.out.println("\tRemember that v can be another attribute, useful for technomancers.");
        System.out.println("get self <attribute>: Recall an attribute's value.");
        System.out.println("\tThis includes limits.");
        System.out.println("save <file>: Save the set of attributes to a file in your home directory.");
        System.out.println("load <file>: Load a previously saved set of attriubtes.");
        System.out.println("cls: Clear the screen.");
        System.out.println("quit: Exactly what it says on the tin.");
        System.out.println();
        System.out.println("Enter any command to return to prompt...");
        Main.getWindow().input();
        Main.getWindow().clear();
        System.out.println("Enter command...");
    }

    public static void printCopyright() {
        System.out.println("Copyright (c) 2015 TheRabbitologist\n"
                + "All rights reserved.\n"
                + "\n"
                + "Redistribution and use in source and binary forms, with or without\n"
                + "modification, are permitted provided that the following conditions are met:\n"
                + "1. Redistributions of source code must retain the above copyright\n"
                + "   notice, this list of conditions and the following disclaimer.\n"
                + "2. Redistributions in binary form must reproduce the above copyright\n"
                + "   notice, this list of conditions and the following disclaimer in the\n"
                + "   documentation and/or other materials provided with the distribution.\n"
                + "3. All advertising materials mentioning features or use of this software\n"
                + "   must display the following acknowledgement:\n"
                + "   This product includes software developed by the TheRabbitologist.\n"
                + "4. Neither the name of the " + Main.NAME + " nor the\n"
                + "   names of its contributors may be used to endorse or promote products\n"
                + "   derived from this software without specific prior written permission.\n"
                + "\n"
                + "THIS SOFTWARE IS PROVIDED BY THERABBITOLOGIST ''AS IS'' AND ANY\n"
                + "EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED\n"
                + "WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE\n"
                + "DISCLAIMED. IN NO EVENT SHALL THERABBITOLOGIST BE LIABLE FOR ANY\n"
                + "DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES\n"
                + "(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;\n"
                + "LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND\n"
                + "ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n"
                + "(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS\n"
                + "SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.");
        System.out.println();
        System.out.println("Enter any command to return to prompt...");
        Main.getWindow().input();
        Main.getWindow().clear();
        System.out.println("Enter command...");
    }
}
