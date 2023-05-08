package org.Lynx.main;

import org.Lynx.main.abilities.exempleability;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        List<Player> tours = new ArrayList<>();
        List<exemplecard> registeredacrds = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int nt = 1;
        boolean game = true;

        //test program  for all the features
        String separator = "==============================";

        //register all the cards
        exemplecard test2 = new exemplecard("test2", 55, 65, 8, 16, 6, 3, 75,60, false);
        registercard(test2, registeredacrds);

        exemplecard test1 = new exemplecard("test1",26,30,4,5,8,6,55, 45, true);
        registercard(test1, registeredacrds);



        //main game
        while(game) {
            System.out.println(separator);
            System.out.println(separator);
            System.out.println("Nombre de joueurs");
            int a = sc.nextInt();
            int bc = 0;
            while (bc !=a){
                System.out.println(a);
                System.out.println("Veuillez entrez le nom du joueur : ");
                String name = sc.next();
                System.out.println("Veuillez selectionner une carte : ");
                int i = 0;
                for (exemplecard exc : registeredacrds) {
                    System.out.println("n°" + i + " : " + exc.getNom());
                    i++;
                }
                int card = sc.nextInt();
                Player player = new Player(name, new exemplecard[]{registeredacrds.get(card)});
                tours.add(player);
                bc++;
            }
            Player touractuel = tours.get(0);
            System.out.println(separator);
            System.out.println(" 1er tour");
            boolean win = false;
            while (!win) {
                int f=0;

                while(f <a ) {
                    touractuel.getcard().DislpayStats();
                    System.out.println("Que voulez vous faire? : ");
                    System.out.println("1: attacker 2: vous reposer 3: passer votre tour");
                    int action = sc.nextInt();
                    switch (action) {
                        case 1 -> {
                            System.out.println("Selectionnez la cible");
                            int i = 0;
                            for (exemplecard exc : registeredacrds) {
                                System.out.println("n°" + i + " : " + exc.getNom());
                                i++;
                            }
                            int cible = sc.nextInt();
                            exemplecard rcible = registeredacrds.get(cible);
                            Baseattack(touractuel.getcard(), rcible);
                        }
                        case 2 -> {
                            heal(touractuel.getcard(), 10);
                            manaheal(touractuel.getcard(), 12);
                        }
                        case 3 -> System.out.println("Vous passez votre tour");
                        default -> System.out.println("Séléctionnez une action valide");
                    }
                    f++;
                    if (f==a){
                        System.out.println(" Fin du tour");
                    } else {
                        System.out.println(separator);
                        tours = NextPlayer(tours, touractuel);
                        touractuel = tours.get(0);
                    }
                }
                if(nt == 3){
                    win = true;
                    game = false;
                }else {
                    System.out.println(separator);
                    System.out.println(separator);
                    nt = NextTurn(nt, tours, touractuel);
                    touractuel = tours.get(0);
                }
            }
        }
    }

    public static void registercard(exemplecard card, List registeredcards){
        registeredcards.add(card);
    }

    public static void heal(exemplecard target, float percentage){
        //calculating new hp amount
        float pvtoset = Math.round( target.getPv()*(1.0+(percentage/100) ) );
        int newhp = (int) pvtoset;

        //setting the new hp amount
        if(newhp>target.getMaxpv()){
            target.setPv( target.getMaxpv() );
            System.out.println(target.getPv());
        }else{
            target.setPv(newhp);
            System.out.println(target.getPv());
        }

    }
    public static void manaheal(exemplecard target, float percentage){
        //calculating new mana amount
        float manatoset = Math.round(target.getMana()*(1.0+(percentage/100)));
        int newMana = (int) manatoset;

        //setting the new mana
        if(newMana>target.getMaxmana()){
            target.setMana( target.getMaxmana() );
            System.out.println(target.getMana());
        }else{
            target.setMana(newMana);
            System.out.println(target.getMana());
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
    public static List NextPlayer(List tours, Player touractuel){
        //
        Player temp = touractuel;
        tours.remove(0);
        tours.add(temp);
        Player msg = (Player) tours.get(0);
        System.out.println(" Au tour de "+ msg.getNom()+" de jouer");
        return tours;
    }
    public static int NextTurn(int nt,List tours, Player touractuel){
        nt+=1;
        System.out.println("tour numéro "+nt);
        tours = NextPlayer(tours, touractuel);
        return nt;
    }


    //made the lvl system a bit early i won't use it
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