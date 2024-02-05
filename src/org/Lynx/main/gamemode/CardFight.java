package org.Lynx.main.gamemode;

import org.Lynx.main.Main;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;

import java.util.List;
import java.util.Scanner;


public class CardFight {
    static int cible;
    static int targetPlayer;
    public static void print(Object str) {
        System.out.println(str);
    } //simplifier le prinln

    public static void separators(int nbr, int n) { //Affiche des lignes de "=" pour séparer les textes. n = le nombre de "=" par ligne nbr = le nombre de lignes
        int i = 0;
        while (i != nbr) {
            print("=".repeat(n));
            i++;
        }
    }

    public CardFight() {
        super();
    }

    public static void init() {
        int nt = 1;
        boolean game = true;
        Scanner sc = new Scanner(System.in);
        List<Player> tours = Main.tours;
        Player tourActuel = Main.touractuel;
        //main game
        while (game) {
            separators(2, 30); //séparateurs ==
            //Générateur de joueur
            int a = generatePlayer(); //retourne le nombre de joueurs
            //démarrage du jeu
            tourActuel = tours.get(0); //récupération du joueur 1
            separators(1, 30);
            print(" 1er tour");
            boolean win = false;
            //boucle condition de victoire
            while (!win) {
                int f = 0;
                while (f < a) {

                    separators(1, 30);
                    int selectedCard = Main.CardSelection(tourActuel);
                    tourActuel.getcard(selectedCard).DislpayStats();
                    print("Que voulez vous faire? : ");
                    print("1: capacité 2: vous reposer 3: passer votre tour");
                    int playeraction = sc.nextInt();
                    //actions
                    switch (playeraction) {

                        case 1 -> {

                            abilityselect();
                            targetselect();
                            tourActuel.setPlayeraction(1);
                            exemplecard targetCard = (exemplecard) tours.get(targetPlayer).getCardList().get(cible);
                            tourActuel.getSelectedabl().use(tourActuel.getcard(selectedCard), targetCard, tourActuel, tours.get(targetPlayer));
                            Main.LifeCheck(targetCard);
                        }
                        case 2 -> {

                            tourActuel.setPlayeraction(2);
                            Main.heal(tourActuel.getcard(0), 10);
                            Main.manaheal(tourActuel.getcard(0), 12);
                        }
                        case 3 -> {

                            tourActuel.setPlayeraction(3);
                            print("Vous passez votre tour");
                        }
                        default -> print("Sélectionnez une action valide");


                    }
                    f++;

                    if (f == a) {
                        print(" Fin du tour");
                        separators(2, 30);
                        win = Main.winverification();
                        if (win) {
                            f = a;
                            game = Main.nextgame();
                        } else {
                            nt = Main.NextTurn(nt, tours, tourActuel);
                            tourActuel = tours.get(0);
                        }

                    } else {
                        separators(2, 30);
                        win = Main.winverification();

                        if (win) {
                            f = a;
                            game = Main.nextgame();
                        } else {
                            tours = Main.NextPlayer(tours, tourActuel);
                            tourActuel = tours.get(0);
                        }
                    }

                }
            }
        }
    }

    public static int generatePlayer(){
        Scanner sc = new Scanner(System.in);
        print("Nombre de joueurs");
        int a = sc.nextInt();
        print("Nombre de card par joueur");
        int nbcard = sc.nextInt();
        int bc = 0;
        while (bc != a) {
            print(a);
            print("Veuillez entrez le nom du joueur : ");
            String name = sc.next();
            Player player = new Player(name);
            print(player.getNom() + " veuillez ajouter " + nbcard + " une carte : ");
            int nbcc = 0;
            while (nbcard != nbcc) {
                Main.cardLister(Main.registeredacrds);
                int card = sc.nextInt();
                if(!(card > nbcard)){
                    player.AddCard(player.getCardList(), Main.registeredacrds.get(card));
                    print("Carte suivante");
                    nbcc++;
                }else{
                    print("la carte séléctionée st invalide");
                }


            }
            print("Joueur suivant");
            Main.tours.add(player);
            bc++;
        }
        return a;
    }

    public static void abilityselect() {
        Scanner sc = new Scanner(System.in);
        print("slectionez une compétence");
        Main.abilitylister(Main.touractuel);
        Main.chooseability = sc.nextInt();
        Main.touractuel.setSelectedabl(Main.touractuel.getSelectedcard().getAbilityList().get(Main.chooseability));

    }

    public static void targetselect() {
        Scanner sc = new Scanner(System.in);
        print("Sélectionnez un joueur");
        Main.playerlister(Main.touractuel, Main.tours);
        targetPlayer = sc.nextInt();
        print("Sélectionnez la cible");
        Main.cardLister(Main.tours.get(targetPlayer).getCardList());
        cible = sc.nextInt();

    }

}