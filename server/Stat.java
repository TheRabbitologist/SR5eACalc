/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package server;

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
    RES,
    MXA,
    MXP,
    MXR,
    MXF,
    MXS,
    ARMOR;
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
            case "MXA": return MXA;
            case "MXP": return MXP;
            case "MXR": return MXR;
            case "MXF": return MXF;
            case "MXS": return MXS;
            case "ARMOR": return ARMOR;
            default: return null;
        }
    }
}
