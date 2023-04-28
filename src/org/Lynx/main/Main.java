package org.Lynx.main;

import org.Lynx.main.cards.exemplecard;

public class Main {
    public static void main(String[] args){
        //test program  for all the features
        String separator = "==============================";
        exemplecard test2 = new exemplecard("test2", 55, 65, 8, 16, 6, 3, 75,60, false);
        exemplecard test1 = new exemplecard("test1",26,30,4,5,8,6,55, 45, true);
        System.out.println(separator);
        System.out.println("nom :"+test1.getNom());
        System.out.println("pv :"+test1.getPv());
        System.out.println("pv max: "+test1.getMaxpv());
        System.out.println(separator);
        System.out.println("nom :"+test2.getNom());
        System.out.println("pv :"+test2.getPv());
        System.out.println("pv max: "+test2.getMaxpv());
        System.out.println(separator);
        Baseattack(test2,test1);
        System.out.println(separator);
        Baseattack(test1,test2);
        System.out.println(separator);


    }

    public static void Baseattack(exemplecard caster, exemplecard target){
        if (caster.getBaIsmagic()){
            System.out.println();
            caster.magdmg(target, caster.getBaseAttack().getManacost());
            System.out.println(" Pv restants de "+target.getNom()+": "+target.getPv());

        } else if (!caster.getBaIsmagic()) {
            caster.physdmg(target);
            System.out.println(" Pv restants de "+target.getNom()+": "+target.getPv());

        }

    }
}