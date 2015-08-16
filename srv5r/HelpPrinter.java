package srv5r;

public class HelpPrinter {
    public static void printHelp() {
        System.out.println("---Command Reference---");
        System.out.println();
        System.out.println("In place of single-letter placeholders, a basic expression can be used.");
        System.out.println("\tExamples: 9+5 or 8 or 4-2-1 or -8+5*4");
        System.out.println("\tOnly addition, subtraction, and multiplication operations are supported.");
        System.out.println("\tMuliplication operations are, of course, done before addition and subtraction.");
        System.out.println("\tnd6 may be embedded in expressions to include a hit roll, ex. 5000+100*4d6");
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
        System.out.println();
        System.out.println("raw <n>: Roll nd6, display results of each roll.");
        System.out.println("dice <n>: Roll nd6, display sum of rolls.");
        System.out.println("roll <rolltype>: Calculate hits.");
        System.out.println("\tn: Roll nd6, display hits.");
        System.out.println("\tn[l]: Roll nd6 with limit l, display hits.");
        System.out.println("\tGlitch status is printed in all cases where applicable.");
        System.out.println("test <test>: Perform a success or opposed test.");
        System.out.println("\tn(t): Attempt a success test with threshold t and no limit.");
        System.out.println("\tn(t)[l]: Attempt a success test with threshold t and limit l.");
        System.out.println("\ta v. b: Attempt an opposed test without limits.");
        System.out.println("\ta[l] v. b: Attempt an opposed test with the left side limited.");
        System.out.println("\ta[l] v. b[L]: Attempt an opposed test, each side with their own limit.");
        System.out.println("\tGlitch status is printed in all cases where applicable.");
        System.out.println("calc <s>: Evaluate an expression, ignoring glitches.");
        System.out.println("attack <atk> v. <def>: Perform an attack roll sequence.");
        System.out.println("\tIf the attack hits, a damage roll will be performed.");
        System.out.println("\tatk and def may be expressions.");
        System.out.println("damage <dmg> <ap> <target_armor> <target_body>: Calculate damage done.");
        System.out.println("\t<damage>: Should be of the form nP or nS for physical or stun damage.");
        System.out.println("\tAll other arguments may be expressions.");
        System.out.println("\tOutput also indicates damage type.");
        System.out.println("set self <attribute> <v>: Set an attribute's value.");
        System.out.println("\tLimits cannot be set, rather they are calculated from the attributes.");
        System.out.println("\tRemember that v can absolutely be another attribute, useful for technomancers.");
        System.out.println("get self <attribute>: Recall an attribute's value.");
        System.out.println("\tThis includes limits.");
        System.out.println("cls: Clear the screen.");
        System.out.println("quit: Exactly what it says on the tin.");
        System.out.println();
        System.out.println("Enter any command to return to prompt...");
        ShadowrunRoller.getWindow().input();
        ShadowrunRoller.getWindow().clear();
        System.out.println("Enter command...");
    }
}
