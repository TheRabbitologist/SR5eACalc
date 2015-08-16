/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srv5r;

public enum Stat {
    STR,
    BOD,
    REA,
    AGI,
    LOG,
    INT,
    WIL,
    CHA,
    EDG,
    MAG,
    RES;
    public static Stat get(String s) {
        switch(s) {
            case "STR": return STR;
            case "BOD": return BOD;
            case "REA": return REA;
            case "AGI": return AGI;
            case "LOG": return LOG;
            case "INT": return INT;
            case "WIL": return WIL;
            case "CHA": return CHA;
            case "EDG": return EDG;
            case "MAG": return MAG;
            case "RES": return RES;
            default: return null;
        }
    }
}
