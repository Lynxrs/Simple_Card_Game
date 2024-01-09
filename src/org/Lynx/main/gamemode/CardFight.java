package org.Lynx.main.gamemode;

import org.Lynx.main.Main;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.Lynx.main.Main.generatePlayer;

public class CardFight {
    public static void print(Object str) {
        System.out.println(str);
    }

    public static void separators(int nbr, int n) {
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

        //main game
        while (game) {
            separators(2, 30);
            print("Nombre de joueurs");
            int a = sc.nextInt();
            print("Nombre de card par joueur");
            int nbcard = sc.nextInt();
            generatePlayer(a,nbcard);
            //Générateur de joueur
            //démarrage du jeu
            Player touractuel = Main.touractuel;
            touractuel = tours.get(0);
            separators(1, 30);
            print(" 1er tour");
            boolean win = false;
            //boucle condition de victoire
            while (!win) {
                int f = 0;
                while (f < a) {

                    separators(1, 30);
                    int selectedcard = Main.CardSelection(touractuel);
                    touractuel.getcard(selectedcard).DislpayStats();
                    print("Que voulez vous faire? : ");
                    print("1: capacité 2: vous reposer 3: passer votre tour");
                    int playeraction = sc.nextInt();
                    //actions
                    switch (playeraction) {
                        case 1 -> {
                            print("slectionez une compétence");
                            Main.abilitylister(touractuel);
                            Main.chooseability = sc.nextInt();
                            touractuel.setSelectedabl(touractuel.getSelectedcard().getAbilityList().get(Main.chooseability));
                            touractuel.setPlayeraction(1);
                            print("Sélectionnez un joueur");
                            Main.playerlister(touractuel, tours);
                            int pcible = sc.nextInt();
                            print("Sélectionnez la cible");
                            Main.cardLister(tours.get(pcible).getCardList());
                            int cible = sc.nextInt();
                            exemplecard rcible = (exemplecard) tours.get(pcible).getCardList().get(cible);
                            touractuel.getSelectedabl().use(touractuel.getcard(selectedcard), rcible, touractuel, tours.get(pcible));
                            Main.LifeCheck(rcible);
                        }
                        case 2 -> {
                            touractuel.setPlayeraction(2);
                            Main.heal(touractuel.getcard(0), 10);
                            Main.manaheal(touractuel.getcard(0), 12);
                        }
                        case 3 -> {
                            touractuel.setPlayeraction(3);
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
                            nt = Main.NextTurn(nt, tours, touractuel);
                            touractuel = tours.get(0);
                        }

                    } else {
                        separators(2, 30);
                        win = Main.winverification();

                        if (win) {
                            f = a;
                            game = Main.nextgame();
                        } else {
                            tours = Main.NextPlayer(tours, touractuel);
                            touractuel = tours.get(0);
                        }
                    }

                }
            }
        }
    }
}