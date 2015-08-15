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
public class Parser {
    public static int sum(String str) {
        String values[] = str.split("\\+");
        int retval = 0;
        for(String value : values) {
            String subs[] = value.split("\\-");
            retval += Integer.parseInt(subs[0]);
            for(int i = 1; i < subs.length; ++i)
                retval -= Integer.parseInt(subs[i]);
        }
        return retval;
    }
    public static int[] testSingle(String str) {
        int[] retval;
        int D=0, T=0, L=255;
        int beginT = str.indexOf('(');
        int beginL = str.indexOf('[');
        int endD = Math.min(beginT, beginL);
        if(endD == -1)
            endD = Math.max(beginT, beginL);
        if(endD == -1) {
            retval = new int[1];
            retval[0] = sum(str);
            return retval;
        }
        D = sum(str.substring(0,endD));
        if(beginL >= 0) {
            int endL = str.indexOf(']');
            L = sum(str.substring(beginL+1,endL));
        }
        if(beginT >= 0) {
            int endT = str.indexOf(')');
            T = sum(str.substring(beginT+1,endT));
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
}
