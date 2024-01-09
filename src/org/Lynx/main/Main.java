package org.Lynx.main;

import org.Lynx.main.abilities.exempleability;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;
import org.Lynx.main.gamemode.BoardMode;
import org.Lynx.main.gamemode.CardFight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int chooseability;
    public static Player touractuel;
    public static List<Player> tours = new ArrayList<>();
    public static List<exemplecard> registeredacrds = new ArrayList<>();
    public static List<exempleability> registeredabilities = new ArrayList<>();

    //make print easy to use
    public static void print(Object str) {
        System.out.println(str);
    }
    public static void separators(int nbr, int n){
        int i=0;
        while( i != nbr) {
            print("=".repeat(n));
            i++;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        registerylist.init();

        //board tests
        board Blueside= new board();
        Blueside.Boardgen(Blueside.Board,2, 6);
        Blueside.display();
        separators(1,18);
        board Redside = new board();
        Redside.Boardgen(Redside.Board,2,6);
        Redside.display();

        print("Choisissez le mode de jeu");
        int mode = sc.nextInt();
        switch(mode){
            case 1 -> {
                separators(2,30);
                print("Mode plateau");
                BoardMode.init();

            } case 2 -> {
                separators(2,30);
                print("Mode card-fight");
                CardFight.init();
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
        tours.remove(0);
        tours.add(touractuel);
        Player msg =tours.get(0);
        print(" Au tour de "+ msg.getNom()+" de jouer");
        return tours;
    }
    public static int NextTurn(int nt,List<Player> tours, Player touractuel){
        nt+=1;
        print("tour numéro "+nt);
        tours = NextPlayer(tours, touractuel);
        return nt;
    }

    public static void LifeCheck(exemplecard cible){
        if (cible.getPv() <=0){
            print("La carte "+cible.getNom()+" ne possède plus de vie elle est donc inutilisable.");
            cible.setAlive(false);


        }else{
            print("Il reste "+cible.getPv()+" Pv à "+cible.getNom()+".");
            cible.setAlive(true);
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
            if (p != atour) {
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
        if(!touractuel.getcard(selectedcard).isAlive()){
            print("cette carte est morte vous ne pouvez pas faire d'actions avec celle ci");
            selectedcard = CardSelection(touractuel);
        }else{
            return selectedcard;
        }

        return selectedcard;
    }
    public static Boolean winverification(){
        int f=tours.size();
        int i =0;
        while(i!=f){
            if(i>f){
                i = f;
            }else{
                Player p = tours.get(i);
                p.alldead();
                f=tours.size();
                i++;
            }

        }
        if (tours.size()==1){
            print(tours.get(0).getNom()+" a gagné la partie GG!");

            return true;
        }else{
            return false;
        }
    }
    public static Boolean nextgame(){
        print("Voulez vous rejouer? (oui non)");
        Scanner sc = new Scanner(System.in);
        String rep = sc.next();
        if(rep.equalsIgnoreCase("oui")){
            return true;
        }else if(rep.equalsIgnoreCase("non")){
            return false;
        }else{
            print("veuillez entrer un réponse valide");
            return nextgame();
        }

    }

    public static void generatePlayer(int playerNumber, int cardNumber){
            int bc = 0;

            while (bc != playerNumber) {
                Scanner sc = new Scanner(System.in);
                print("Veuillez entrez le nom du joueur : ");
                String name = sc.next();
                Player player = new Player(name);
                print(player.getNom() + " veuillez ajouter " + cardNumber + " une carte : ");
                int nbcc = 0;
                while (cardNumber != nbcc) {
                    Main.cardLister(Main.registeredacrds);
                    int card = sc.nextInt();
                    player.AddCard(player.getCardList(), Main.registeredacrds.get(card));
                    print("Carte suivante");
                    nbcc++;
                }
                print("Joueur suivant");
                tours.add(player);
                bc++;
            }
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