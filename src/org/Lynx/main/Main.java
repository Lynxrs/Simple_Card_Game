package org.Lynx.main;

import org.Lynx.main.cards.exemplecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        List<exemplecard> tours = new ArrayList<>();
        List<exemplecard> registerdacrds = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int nt = 0;
        //test program  for all the features
        String separator = "==============================";
        exemplecard test2 = new exemplecard("test2", 55, 65, 8, 16, 6, 3, 75,60, false);
        exemplecard test1 = new exemplecard("test1",26,30,4,5,8,6,55, 45, true);
        tours.add(test1);
        tours.add(test2);
        registerdacrds.add(test1);
        registerdacrds.add(test2);
        exemplecard touractuel = tours.get(0);
        System.out.println(separator);
        System.out.println("nom :"+test1.getNom());
        System.out.println("pv :"+test1.getPv());
        System.out.println("pv max: "+test1.getMaxpv());
        System.out.println(separator);
        System.out.println("nom :"+test2.getNom());
        System.out.println("pv :"+test2.getPv());
        System.out.println("pv max: "+test2.getMaxpv());

        System.out.println(separator);
        nt = NextTurn(nt);
        NextPlayer(tours, nt, touractuel);
        Baseattack(test2,test1);
        System.out.println(separator);
        NextPlayer(tours, nt, touractuel);
        Baseattack(test1,test2);
        System.out.println(separator);
        nt = NextTurn(nt);
        heal(test2,20);


        System.out.println(test1.getLvl());
        LvlUp(test1);
        System.out.println(test1.getLvl());
        System.out.println("Que voulez vous faire? : ");
        System.out.println("1: attacker 2: vous reposer 3:passer votre tour");
        int action = sc.nextInt();

        switch (action){
            case 1:
                System.out.println("selectionnez la cible");
                int i = 0;
                for (exemplecard exc : registerdacrds){
                    System.out.println("n°"+i+" : "+exc.getNom());
                    i++;
                }
                int cible = sc.nextInt();
                exemplecard rcible = registerdacrds.get(cible);
                Baseattack(touractuel,rcible);
                break;
            case 2:
                Baseattack(test2,test1);
                break;
            default:
                System.out.println("Séléctionnez une action valide");

        }
    }

    public static void heal(exemplecard target, float percentage){
        //calculating new hp amount
        float pvtoset = Math.round(target.getPv()*(1.0+(percentage/100)));
        int newhp = (int) pvtoset;

        //setting the new hp
        if(newhp>target.getMaxpv()){
            target.setPv( target.getMaxpv() );
            System.out.println(target.getPv());
        }else{
            target.setPv(newhp);
            System.out.println(target.getPv());
        }

    }
    public static void Baseattack(exemplecard caster, exemplecard target){
        if (caster == target){
            System.out.println("vous ne pouvez vous infliger des dégats");
            return;
        }
        if (caster.getBaIsmagic()){
            System.out.println();
            caster.magdmg(target, caster.getBaseAttack().getManacost());
            System.out.println(" Pv restants de "+target.getNom()+": "+target.getPv());

        } else if (!caster.getBaIsmagic()) {
            caster.physdmg(target);
            System.out.println(" Pv restants de "+target.getNom()+": "+target.getPv());

        }

    }
    public static void NextPlayer(List tours, int nt, exemplecard touractuel){
        //
        exemplecard temp = touractuel;
        tours.remove(0);
        tours.add(temp);
        System.out.println(" Au tour de "+touractuel.getNom()+" de jouer");
    }
    public static int NextTurn(int nt){
        nt+=1;
        System.out.println("tour numéro "+nt);
        return nt;
    }

    public static void LvlUp(exemplecard card){
        card.setLvl(card.getLvl()+1);
        card.setMaxpv(card.getPv()+2);
        if(card.getLvl()%5 ==0){
            card.setDefmag(card.getDefmag()+2);
            card.setDefphys(card.getDefphys()+2);
        }
        if (card.getBaIsmagic()) {
            card.setDmgmag(card.getDmgmag() + 2);
            card.setMaxmana(card.getMaxmana() + 2);
            card.getBaseAttack().setManacost(card.getBaseAttack().getManacost() + 1);
        }else{
            card.setDmgphys(card.getDmgphys()+2);
        }
    }
}