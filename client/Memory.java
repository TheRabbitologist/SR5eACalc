/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package client;

import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Memory {

    public Entity self;
    public Map<String,String> macros;

    public Memory() {
        self = new Entity();
        macros = new HashMap<>();
    }

    public void save(BufferedWriter w) {
        try {
            for (Stat s : Stat.values())
                w.append("self " + s.name() + ' ' + self.stats.getStat(s) + '\n');
            w.append("self ESS " + self.stats.getESS() + '\n');
            for (String s : self.skills.keySet())
                w.append("self " + s + ' ' + self.skills.get(s) + '\n');
        } catch (Exception e) {
            System.out.println("Error: Could not save stats - " + e.getLocalizedMessage());
        }
    }
    
    public void load(Scanner s) {
        try {
            while(s.hasNextLine()) {
                String[] data = s.nextLine().split("\\s+");
                if(data[0].contentEquals("self"))
                    self.set(new String[]{data[1], data[2]});
            }
        } catch (Exception e) {
            System.out.println("Error: Could not load stats - " + e.getLocalizedMessage());
        }
    }
    
    public int macro(String s) {
        if(macros.containsKey(s))
            return Parser.sum(macros.get(s));
        return 0;
    }
}
