package org.Lynx.main.cards;

import org.Lynx.main.Main;
import org.Lynx.main.abilities.exempleability;

import java.util.ArrayList;
import java.util.List;

public class exemplecard {

    private String nom;
    private int pv, maxpv;
    private int dmgmag, dmgphys;
    private int defphys, defmag;
    private int maxmana, mana;
    private boolean BaIsmagic;
    private boolean haseffect;
    private int lvl = 0;
    private boolean isAlive = true;
    private final exempleability baseAttack = new exempleability("Attaque de base", this.BaIsmagic,2,this.dmgphys, this.dmgmag,false, "waé");
    private final List<exempleability> abilityList =new ArrayList<>();
    private List<String> effectlist = new ArrayList<>();
    public exemplecard(String nom, int pv, int maxpv, int dmgphys, int dmgmag, int defphys, int defmag, int maxmana, int mana, boolean baismagic){
        this.abilityList.add(baseAttack);
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
    public List<exempleability> getAbilityList() {
        return abilityList;
    }
    public List<String> getEffectlist() {
        return effectlist;
    }
    public void setEffectlist(List<String> effectlist) {
        this.effectlist = effectlist;
    }
    public void addEffect(String effectname, List<String> effectlist){
        effectlist.add(effectname);
    }


    public int physdmg(exemplecard cible){
        int totalphysdmg = this.dmgphys- cible.getDefphys();
        if (totalphysdmg<=0){
            Main.print(" Aucun points de dégats ont été infligés a "+cible.getNom());
        }
        else {
            cible.setPv(cible.getPv() - totalphysdmg);
            Main.print(" "+totalphysdmg + " points de dégats physiques infligés a " + cible.getNom());
        }
        return cible.pv;
    }

    public void addability(exempleability ability){
        abilityList.add(ability);
    }
    public int magdmg(exemplecard cible, int manacost){
        int totalmagdmg = this.dmgmag-cible.getDefmag();
        if (mana > 2) {
            if (totalmagdmg <= 0){
                Main.print(" Aucun points de dégats ont été infligés a "+cible.getNom());
            }else {
                cible.setPv(cible.getPv() - totalmagdmg);
                Main.print(" "+totalmagdmg + " points de dégats magiques infligés a " + cible.getNom());
                this.setMana(this.mana - manacost);
                return cible.getPv();
            }
        }
        else{
            Main.print(" "+this.nom+" Manque de mana: cette attaque n'est pas possible");
        }

        return this.getMana();
    }

    public void DislpayStats(){
        Main.print("Pv : "+this.getPv()+"/"+this.getMaxpv());
        Main.print("Mana : "+this.getMana()+"/"+this.getMaxmana());
        Main.print("Capacités: ");

        for (exempleability alname : abilityList){
            Main.print("  "+alname.getName());
        }
        Main.print("Statut : ");

        for (String effects : this.getEffectlist()){
            Main.print(" "+effects);
        }
    }


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }


    public boolean isHaseffect() {
        return haseffect;
    }

    public void setHaseffect(boolean haseffect) {
        this.haseffect = haseffect;
    }

    public void executeffect(String effect) {
        switch(effect){
            case "paralysis" ->{
                Main.print("La carte est paralysé pour ce tour vous ne pouvez rien faire");
                Main.CardSelection(Main.gettouractuel());
                Main.gettouractuel().getSelectedcard().getEffectlist().remove("paralysis");
                Main.gettouractuel().getSelectedcard().setHaseffect(false);
            }
        }
    }
}
