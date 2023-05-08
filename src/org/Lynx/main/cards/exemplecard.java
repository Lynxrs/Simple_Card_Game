package org.Lynx.main.cards;

import org.Lynx.main.abilities.exempleability;

public class exemplecard {

    private String nom;
    private int pv, maxpv;
    private int dmgmag, dmgphys;
    private int defphys, defmag;
    private int maxmana, mana;
    private boolean BaIsmagic;
    private int lvl = 0;
    private final exempleability baseAttack = new exempleability("Attaque de base", this.BaIsmagic,2,this.dmgphys, this.dmgmag);
    private exempleability[] abilityList ={baseAttack};
    public exemplecard(String nom, int pv, int maxpv, int dmgphys, int dmgmag, int defphys, int defmag, int maxmana, int mana, boolean baismagic){
        this.nom = nom;
        this.pv = pv;
        this.maxpv = maxpv;
        this.dmgphys = dmgphys;
        this.dmgmag = dmgmag;
        this.defphys = defphys;
        this.defmag = defmag;
        this.maxmana = maxmana;
        this.mana = mana;
        this.BaIsmagic = baismagic;
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
    public boolean getBaIsmagic(){
        return BaIsmagic;
    }
    public exempleability getBaseAttack() {
        return baseAttack;
    }
    public exempleability[] getAbilityList() {
        return abilityList;
    }


    public int physdmg(exemplecard cible){
        int totalphysdmg = this.dmgphys- cible.getDefphys();
        if (totalphysdmg<=0){
            System.out.println(" Aucun points de dégats ont été infligés a "+cible.getNom());
        }
        else {
            cible.setPv(cible.getPv() - totalphysdmg);
            System.out.println(" "+totalphysdmg + " points de dégats physiques infligés a " + cible.getNom());
        }
        return cible.pv;
    }


    public int magdmg(exemplecard cible, int manacost){
        int totalmagdmg = this.dmgmag-cible.getDefmag();
        if (mana > 2) {
            if (totalmagdmg <= 0){
                System.out.println(" Aucun points de dégats ont été infligés a "+cible.getNom());
            }else {
                cible.setPv(cible.getPv() - totalmagdmg);
                System.out.println(" "+totalmagdmg + " points de dégats magiques infligés a " + cible.getNom());
                this.setMana(this.mana - manacost);
                return cible.getPv();
            }
        }
        else{
            System.out.println(" "+this.nom+" Manque de mana: cette attaque n'est pas possible");
        }

        return this.getMana();
    }

    public void DislpayStats(){
        System.out.println("Pv : "+this.getPv()+"/"+this.getMaxpv());
        System.out.println("Mana : "+this.getMana()+"/"+this.getMaxmana());
        System.out.println("Capacités: ");

        for (exempleability alname : this.getAbilityList()){
            System.out.println("  "+alname.getName());
        }

    }


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }


}
