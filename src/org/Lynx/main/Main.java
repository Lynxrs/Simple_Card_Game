package org.Lynx.main;

import org.Lynx.main.cards.exemplecard;

public class Main {
    public static void main(String[] args){
        //test program  for all the features
        exemplecard test2 = new exemplecard("prout", 55, 65, 8, 16, 6, 3, 75,60);
        exemplecard test1 = new exemplecard("wesh",26,30,4,5,8,6,55, 45);
        System.out.println("nom :"+test1.getNom());
        System.out.println("pv :"+test1.getPv());
        System.out.println("pv max: "+test1.getMaxpv());
        System.out.println("nom :"+test2.getNom());
        System.out.println("pv :"+test2.getPv());
        System.out.println("pv max: "+test2.getMaxpv());


        test1.physdmg(test2);
        System.out.println("pv : "+test1.getPv());
        System.out.println("pv : "+test2.getPv());

        test1.magdmg(test2);
        System.out.println("pv : "+test1.getPv());
        System.out.println("pv : "+test2.getPv());


    }
}