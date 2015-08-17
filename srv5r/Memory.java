/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

public class Memory {

    public Entity self;

    public Memory() {
        self = new Entity();
    }

    public void save(BufferedWriter w) {
        try {
            for (Stat s : Stat.values())
                w.append("self " + s.name() + ' ' + self.stats.getStat(s) + '\n');
            w.append("self ESS " + self.stats.getESS() + '\n');
        } catch (Exception e) {
            System.out.println("Error: Could not save stats - " + e.getLocalizedMessage());
        }
    }
    
    public void load(Scanner s) {
        try {
            while(s.hasNextLine()) {
                String[] data = s.nextLine().split("\\s+");
                if(data[0].contentEquals("self")) {
                    if(data[1].contentEquals("ESS"))
                        self.stats.setESS(Double.parseDouble(data[2]));
                    else
                        self.stats.setStat(Stat.get(data[1]), Integer.parseInt(data[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Could not load stats - " + e.getLocalizedMessage());
        }
    }
}
