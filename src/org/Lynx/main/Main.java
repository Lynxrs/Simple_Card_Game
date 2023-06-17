package org.Lynx.main;

import org.Lynx.main.abilities.exempleability;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;
import org.Lynx.main.files.registerylist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int chooseability;
    private static Player touractuel;
    public static List<Player> tours = new ArrayList<>();
    public static List<exemplecard> registeredacrds = new ArrayList<>();
    public static List<exempleability> registeredabilities = new ArrayList<>();

    //make print easy to use
    public static void print(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int nt = 1;
        boolean game = true;
        registerylist.init();

        //test program  for all the features
        String separator = "==============================";




        //main game
        while(game) {
            print(separator);
            print(separator);
            print("Nombre de joueurs");
            int a = sc.nextInt();
            int bc = 0;
            print("Nombre de card par joueur");
            int nbcard = sc.nextInt();
            //Générateur de joueur
            while (bc !=a){
                print(a);
                print("Veuillez entrez le nom du joueur : ");
                String name = sc.next();
                Player player = new Player(name);
                print(player.getNom()+" veuillez ajouter "+nbcard+" une carte : ");
                int nbcc =0;
                while(nbcard != nbcc) {
                    cardLister(registeredacrds);
                    int card = sc.nextInt();
                    player.AddCard(player.getCardList(),registeredacrds.get(card));
                    print("Carte suivante");
                    nbcc++;
                }
                print("Joueur suivant");
                tours.add(player);
                bc++;
            }
            //démarrage du jeu
            touractuel = tours.get(0);
            print(separator);
            print(" 1er tour");
            boolean win = false;
            //boucle condition de victoire
            while (!win) {
                int f=0;
                while(f <a ) {

                    print(separator);
                    int selectedcard = CardSelection(touractuel);
                    touractuel.getcard(selectedcard).DislpayStats();
                    print("Que voulez vous faire? : ");
                    print("1: capacité 2: vous reposer 3: passer votre tour");
                    int playeraction = sc.nextInt();
                    //actions
                    switch (playeraction) {
                        case 1 -> {
                            print("slectionez une compétence");
                            abilitylister(touractuel);
                            chooseability = sc.nextInt();
                            touractuel.setSelectedabl(touractuel.getSelectedcard().getAbilityList().get(chooseability));
                            touractuel.setPlayeraction(1);
                            print("Sélectionnez un joueur");
                            playerlister(touractuel,tours);
                            int pcible = sc.nextInt();
                            print("Sélectionnez la cible");
                            cardLister(registeredacrds);
                            int cible = sc.nextInt();
                            exemplecard rcible = (exemplecard) tours.get(pcible).getCardList().get(cible);
                            touractuel.getSelectedabl().use(touractuel.getcard(selectedcard), rcible, touractuel, tours.get(pcible));
                            LifeCheck(tours.get(pcible), rcible);
                        }
                        case 2 -> {
                            touractuel.setPlayeraction(2);
                            heal(touractuel.getcard(0), 10);
                            manaheal(touractuel.getcard(0), 12);
                        }
                        case 3 -> {
                            touractuel.setPlayeraction(3);
                            print("Vous passez votre tour");
                        }
                        default -> print("Sélectionnez une action valide");


                    }
                    f++;


                    if (f==a){
                        print(" Fin du tour");
                    } else {
                        print(separator);
                        tours = NextPlayer(tours, touractuel);
                        touractuel = tours.get(0);
                    }

                }

                //condition de victoire debug
                if(nt == 8){
                    win = true;
                    game = false;
                }else {
                    print(separator);
                    print(separator);
                    nt = NextTurn(nt, tours, touractuel);
                    touractuel = tours.get(0);
                }
            }
        }
    }

    public static void registercard(exemplecard card, List<exemplecard> registeredcards){
        registeredcards.add(card);
    }

    public static void heal(exemplecard target, float percentage){
        //calculating new hp amount
        float pvtoset = Math.round(target.getMaxpv()*(1.0+(percentage/100)));
        int newhp = (int) pvtoset;

        //setting the new hp amount
        if(newhp>target.getMaxpv()){
            target.setPv( target.getMaxpv() );
            print(target.getPv());
        }else{
            target.setPv(newhp);
            print(target.getPv());
        }

    }

    public static void manaheal(exemplecard target, float percentage){
        //calculating new mana amount
        float manatoset = Math.round(target.getMana()*(1.0+(percentage/100)));
        int newMana = (int) manatoset;

        //setting the new mana
        if(newMana>target.getMaxmana()){
            target.setMana( target.getMaxmana() );
            print(target.getMana());
        }else{
            target.setMana(newMana);
            print(target.getMana());
        }
    }

    public static List<Player> NextPlayer(List<Player> tours, Player touractuel){
        //
        Player temp = touractuel;
        tours.remove(0);
        tours.add(temp);
        Player msg = (Player) tours.get(0);
        print(" Au tour de "+ msg.getNom()+" de jouer");
        return tours;
    }
    public static int NextTurn(int nt,List tours, Player touractuel){
        nt+=1;
        print("tour numéro "+nt);
        tours = NextPlayer(tours, touractuel);
        return nt;
    }

    //if you use the alive state change function from void to boolean
    public static void LifeCheck(Player player, exemplecard cible){
        if (cible.getPv() <=0){
            print(cible+" ne possède plus de vie il est donc éliminé.");
            //return false; (for alive state)
            player.RemoveCard(player.getCardList(), cible);

        }else{
            print("Il reste "+cible.getPv()+" Pv à "+cible.getNom()+".");
            //return true; (for alive state)
        }
    }

    public static void cardLister(List<exemplecard> registered){
        int i = 0;
        for (exemplecard exc : registered) {
            print("n°" + i + " : " + exc.getNom());
            i++;
        }
    }

    public static void playerlister(Player atour,List<Player> registered){
        int i = 0;
        for (Player p : registered){
            if (p == atour) {
                //basically this "if" does nothing it just skips the player that is currently playing
            } else {
                print("n°" + i + " : " + p.getNom());
            }
            i++;
        }
    }
    public static void abilitylister(Player playing){
        int i=0;
        for(exempleability ab : playing.getSelectedcard().getAbilityList()) {
            print(" n°" + i + " : " + ab.getName());
            i++;
        }
    }


    public static Player gettouractuel(){
        return touractuel;
    }
    public static List<Player>getours(){
        return tours;
    }

    public static void Statutschecker(Player touractuel){
        if(touractuel.getSelectedcard().isHaseffect()) {
            for (String e : touractuel.getSelectedcard().getEffectlist()){
                touractuel.getSelectedcard().executeffect(e);
            }
        }
    }
    public static int CardSelection(Player touractuel){
        print("Sélectionnez une carte de votre main");
        cardLister(touractuel.getCardList());
        Scanner sc= new Scanner(System.in);
        int selectedcard = sc.nextInt();
        touractuel.setSelectedcard(touractuel.getcard(selectedcard));
        Statutschecker(touractuel);
        return selectedcard;
    }

    /*made the lvl system way too early I won't use it until I have fix players registered in a  file
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
    }*/
}