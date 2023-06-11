package org.Lynx.main.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String nom;
    private List<exemplecard> cardlist = new ArrayList<>();

    public Player(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public List getCardList(){
        return cardlist;
    }

    public static void AddCard(List cardlist,exemplecard card){
        cardlist.add(card);
    }
    public static void RemoveCard(List cardlist,exemplecard card){
        cardlist.remove(card);
    }

    public exemplecard getcard(int numcarte){
        return cardlist.get(numcarte);
    }
}
