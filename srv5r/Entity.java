/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

import java.util.Map;

public class Entity {

    public Stats stats;

    public Entity() {
        this(null);
    }

    public Entity(Map<Stat, Integer> init) {
        stats = new Stats();
        if (init != null) {
            for (Stat s : init.keySet())
                stats.setStat(s, init.get(s));
        }
    }

    public void set(String[] s) {
        String key = s[0].toUpperCase();
        switch (key) {
            case "ESS":
                stats.setESS(Float.parseFloat(s[1]));
                break;
            default:
                Stat stat = Stat.get(key);
                if (stat != null)
                    stats.setStat(stat, Parser.sum(s[1]));
                else
                    System.out.println("Error: Unknown property '" + key + "'.");
                break;
        }
    }

    public Object get(String str) {
        String prop = str.toUpperCase();
        switch (prop) {
            case "LM":
                return stats.getLimitM();
            case "LP":
                return stats.getLimitP();
            case "LS":
                return stats.getLimitS();
            case "ESS":
                return stats.getESS();
            default:
                Stat s = Stat.get(prop);
                if (s != null)
                    return stats.getStat(s);
                return null;
        }
    }
}
