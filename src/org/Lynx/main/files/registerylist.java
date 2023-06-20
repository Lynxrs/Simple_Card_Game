package org.Lynx.main.files;

import org.Lynx.main.Main;
import org.Lynx.main.abilities.exempleability;
import org.Lynx.main.cards.exemplecard;
import org.Lynx.main.effects.effects;

import static org.Lynx.main.Main.registercard;

public class registerylist {
    public static void init(){
        //register all the cards
        exemplecard test2 = new exemplecard("test2", 55, 65, 8, 16, 6, 3, 75,60, false);
        registercard(test2, Main.registeredacrds);

        exemplecard test1 = new exemplecard("test1",26,30,4,5,8,6,55, 45, true);
        registercard(test1, Main.registeredacrds);

        exemplecard paladin = new exemplecard("Paladin", 65, 65, 12, 2, 10,10,15,15,false);
        registercard(paladin, Main.registeredacrds);
        //register Abilities
        //n0
        exempleability electrocution = new exempleability("eleectrocution",true,2,0,5,true, "paralysis");
        test2.addability(electrocution);
        //n1


        //register all the effects
        effects paralysis = new effects("paralysis",1,1);
        paralysis.registerEffect(paralysis);


    }
}
