/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    public Stats stats;
    public Map<String,Integer> skills;

    public Entity() {
        this(null);
        skills = new HashMap();
    }

    public Entity(Map<Stat, Integer> init) {
        stats = new Stats();
        if (init != null) {
            for (Stat s : init.keySet())
                stats.setStat(s, init.get(s));
        }
    }

    public void set(String[] s) {
        if(s.length <= 1) {
            System.out.println("Error: Insufficient parameters for set operation.");
            return;
        }
        String key = s[0].toUpperCase();
        switch (key) {
            case "ESS":
                stats.setESS(Float.parseFloat(s[1]));
                break;
            default:
                Stat stat = Stat.get(key);
                if (stat != null)
                    stats.setStat(stat, Parser.sum(s[1]));
                else if(key.startsWith("%") && key.length() >= 2) {
                    int value = Parser.sum(s[1]);
                    if(value != -1)
                        skills.put(key, value);
                    else
                        skills.remove(key);
                }
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
                else if(prop.startsWith("%") && prop.length() >= 2) {
                    if(skills.containsKey(prop))
                        return skills.get(prop);
                    else
                        return -1;
                }
                return null;
        }
    }
}
