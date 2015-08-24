/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package server;

import client.SR5eAC;

public class Parser {

    private static int parseField(String str) {
        if(str == null || str.isEmpty())
            return 0;
        if(str.endsWith("d6"))
            return Roller.roll(parseField(str.substring(0,str.length()-2)));
        if(str.endsWith("h6"))
            return Roller.hits(parseField(str.substring(0,str.length()-2)),false);
        if(str.endsWith("r6"))
            return Roller.hits(parseField(str.substring(0,str.length()-2)),true);
        if(str.endsWith("?g"))
            return (Roller.glitched()?parseField(str.substring(0,str.length()-2)):0);
        //if(str.charAt(0) == '$' && str.length() > 1)
            //return SR5eAC.getMemory().macro(str.substring(1));
        if(str.toUpperCase().contentEquals("LAST"))
            return SR5eAC.getResult();
        if(str.toUpperCase().contentEquals("MISS"))
            return SR5eAC.getMisses();
        Object o = SR5eAC.getSelf().get(str);
        if(o instanceof Integer)
            return (Integer)o;
        if(o instanceof Double)
            return (int)Math.floor((Double)o);
        return Integer.parseInt(str);
    }
    
    public static int sum(String str) {
        String values[] = str.split("\\+");
        int retval = 0;
        for (String value : values) {
            String subs[] = value.split("\\-");
            retval += sumHandle(subs[0]);
            for (int i = 1; i < subs.length; ++i)
                retval -= sumHandle(subs[i]);
        }
        return retval;
    }
    
    private static int sumHandle(String s) {
        int mindex = s.indexOf('*');
        if(mindex != -1) {
            String mults[] = s.split("\\*");
            int retval = parseField(mults[0]);
            for (int i = 1; i < mults.length; ++i)
                retval *= parseField(mults[i]);
            return retval;
        } else return parseField(s);
    }

    public static int[] testSingle(String str) {
        int[] retval;
        int D = 0, T = 0, L = 255;
        int beginT = str.indexOf('(');
        int beginL = str.indexOf('[');
        int endD = Math.min(beginT, beginL);
        if (endD == -1)
            endD = Math.max(beginT, beginL);
        if (endD == -1) {
            retval = new int[1];
            retval[0] = sum(str);
            return retval;
        }
        D = sum(str.substring(0, endD));
        if (beginL >= 0) {
            int endL = str.indexOf(']');
            L = sum(str.substring(beginL + 1, endL));
        }
        if (beginT >= 0) {
            int endT = str.indexOf(')');
            T = sum(str.substring(beginT + 1, endT));
            retval = new int[3];
            retval[0] = D;
            retval[1] = L;
            retval[2] = T;
        } else {
            retval = new int[2];
            retval[0] = D;
            retval[1] = L;
        }
        return retval;
    }

    public static String[] testOpposed(String[] split, int start) {
        String retval[] = new String[2];
        retval[0] = "";
        byte w = 0;
        for (int i = start; i < split.length; ++i) {
            if (split[i].contentEquals("v.")) {
                w = 1;
                retval[1] = "";
                continue;
            }
            retval[w] += split[i];
        }
        return retval;
    }
    
    public static String[] testOpposed(String[] split) {
        return testOpposed(split,1);
    }
    
    public static String[] testOpposed(String input) {
        return testOpposed(input.split("\\s+"), 0);
    }

    public static String[] testDamage(String str) {
        return null;
    }
}
