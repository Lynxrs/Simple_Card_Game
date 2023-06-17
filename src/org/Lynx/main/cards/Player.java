package org.Lynx.main.cards;

import org.Lynx.main.abilities.exempleability;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String nom;
    private int playeraction;
    private exemplecard selectedcard;
    private List<exemplecard> cardlist = new ArrayList<>();
    private exempleability selectedabl;

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

    public int getPlayeraction() {
        return playeraction;
    }

    public void setPlayeraction(int playeraction) {
        this.playeraction = playeraction;
    }

    public exemplecard getSelectedcard() {
        return selectedcard;
    }

    public void setSelectedcard(exemplecard selectedcard) {
        this.selectedcard = selectedcard;
    }

    public exempleability getSelectedabl() {
        return selectedabl;
    }

    public void setSelectedabl(exempleability selectedabl) {
        this.selectedabl = selectedabl;
    }
}
