package org.Lynx.main.cards;

public class exemplecard {

    private String nom;
    private int pv;
    private int maxpv;
    private int  dmgphys;
    private int dmgmag;
    private int defphys;
    private int defmag;
    private int maxmana;
    private int mana;

    public exemplecard(String nom, int pv, int maxpv, int dmgphys, int dmgmag, int defphys, int defmag, int maxmana, int mana){
        this.nom = nom;
        this.pv = pv;
        this.maxpv = maxpv;
        this.dmgphys = dmgphys;
        this.dmgmag = dmgmag;
        this.defphys = defphys;
        this.defmag = defmag;
        this.maxmana = maxmana;
        this.mana = mana;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }
    public int getDmgphys() {
        return dmgphys;
    }
    public void setDmgphys(int dmgphys) {
        this.dmgphys = dmgphys;
    }
    public int getDmgmag() {
        return dmgmag;
    }
    public void setDmgmag(int dmgmag) {
        this.dmgmag = dmgmag;
    }
    public int getDefphys() {
        return defphys;
    }
    public void setDefphys(int defphys) {
        this.defphys = defphys;
    }
    public void setDefmag(int defmag) {
        this.defmag = defmag;
    }
    public int getDefmag() {
        return defmag;
    }
    public int getMaxpv() {
        return maxpv;
    }
    public void setMaxpv(int maxpv) {
        this.maxpv = maxpv;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMaxmana() {
        return maxmana;
    }
    public void setMaxmana(int maxmana) {
        this.maxmana = maxmana;
    }


    public int physdmg(exemplecard cible){
        int totalphysdmg = this.dmgphys- cible.getDefphys();
        if (totalphysdmg<=0){
            System.out.println("-0 aucun points de dégats ont été infligés");
        }
        else {
            cible.setPv(cible.getPv() - totalphysdmg);
            System.out.println(totalphysdmg + " points de dégats physiques infligés a " + cible.getNom());
        }
        return cible.pv;
    }


    public int magdmg(exemplecard cible){
        int totalmagdmg = this.dmgmag-cible.getDefmag();
        if (mana > 2) {
            cible.setPv(cible.getPv() - totalmagdmg);
            System.out.println(totalmagdmg + " points de dégats magiques infligés a " + cible.getNom());
            this.setMana(this.mana - 2);
            return cible.getPv();
        }
        else{
            System.out.println("cette attaque n'est pas possible");
        }

        return this.getMana();
    }



}
