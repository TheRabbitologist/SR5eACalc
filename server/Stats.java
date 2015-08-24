/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package server;

import java.util.HashMap;

public class Stats {
    protected HashMap<Stat,Integer> stat;
    protected HashMap<Stat,Integer> mod;
    protected double ESS;
    public Stats() {
        stat = new HashMap<>();
        mod = new HashMap<>();
        for(Stat s : Stat.values()) {
            stat.put(s, 1);
            mod.put(s, 0);
        }
        stat.put(Stat.MAG, 0);
        stat.put(Stat.RES, 0);
        stat.put(Stat.MXA, 0);
        stat.put(Stat.MXS, 0);
        stat.put(Stat.ARMOR, 0);
        ESS = 6;
    }
    public int getStat(Stat s) {
        return Math.max(0,stat.get(s) + mod.get(s));
    }
    public double getESS() {
        return ESS;
    }
    public Stats setStat(Stat s, int val) {
        stat.put(s, Math.max(1,val));
        return this;
    }
    public Stats setStatMod(Stat s, int val) {
        mod.put(s, val);
        return this;
    }
    public Stats setStatMod(Stat s) {
        return setStatMod(s, 0);
    }
    public Stats setESS(double val) {
        ESS = Math.max(0,val);
        return this;
    }
    public int getLimitM() {
        int base = stat.get(Stat.LOG)*2+stat.get(Stat.INT)+stat.get(Stat.WIL);
        return (int)Math.ceil(((double)base)/3);
    }
    public int getLimitP() {
        int base = stat.get(Stat.STR)*2+stat.get(Stat.BOD)+stat.get(Stat.REA);
        return (int)Math.ceil(((double)base)/3);
    }
    public int getLimitS() {
        double base = stat.get(Stat.CHA)*2+stat.get(Stat.WIL)+getESS();
        return (int)Math.ceil(base/3);
    }
    public int rollInitPhys() {
        return rollInitPhys(0);
    }
    public int rollInitPhys(int bonusDice) {
        return getStat(Stat.INT)+getStat(Stat.REA)+Roller.hits(1+bonusDice);
    }
    public int rollInitAstr() {
        return Math.max(0, getStat(Stat.INT)*2+Roller.hits(2));
    }
    public int rollInitMtrxAR() {
        return getStat(Stat.INT)+getStat(Stat.REA)+Roller.hits(1);
    }
    public int rollInitMtrxVR() {
        return rollInitMtrxVR(false);
    }
    public int rollInitMtrxVR(boolean hot) {
        return getStat(Stat.LOG)+getStat(Stat.INT)+Roller.hits(hot?4:3);
    }
}
