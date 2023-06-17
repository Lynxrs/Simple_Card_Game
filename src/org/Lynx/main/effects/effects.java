package org.Lynx.main.effects;

import java.util.ArrayList;
import java.util.List;

public class effects {
    private String name;
    private int ID;
    private int time;
    private int power;
    private List<effects> registeredeffects = new ArrayList<>();

    public effects(String name, int time, int power) {
        this.name = name;
        this.time = time;
        this.power = power;
    }



    public void  registerEffect(effects effect){
        generateeffectID(registeredeffects, effect);
        registeredeffects.add(effect);
    }

    public static void generateeffectID(List<effects> effectlist, effects effect){
        effect.setID(effectlist.size());
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    };

    public String getName() {
        return name;
    }


}
