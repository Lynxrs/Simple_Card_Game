package org.Lynx.main.cards;

public class Player {
    private final String nom;
    private exemplecard[] cardlist;

    public Player(String nom, exemplecard[] cardlist) {
        this.nom = nom;
        this.cardlist = cardlist;
    }

    public String getNom() {
        return nom;
    }

    public exemplecard getcard(){
        return cardlist[0];
    }
}
