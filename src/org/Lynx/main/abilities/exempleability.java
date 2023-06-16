package org.Lynx.main.abilities;

public class exempleability {
    private String name;
    private Boolean Ismagic;
    private int manacost;
    private int dmgphys;
    private int dmgmag;
    private Boolean HasEffect;

    public exempleability(String name, Boolean ismagic, int manacost, int dmgphys, int dmgmag, Boolean hasEffect) {
        this.name = name;
        this.Ismagic = ismagic;
        this.manacost = manacost;
        this.dmgphys = dmgphys;
        this.dmgmag = dmgmag;
        HasEffect = hasEffect;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsmagic() {
        return Ismagic;
    }

    public int getManacost() {
        return manacost;
    }
    public void setManacost(int manacost){this.manacost = manacost;}
    public int getDmgphys() {
        return dmgphys;
    }

    public int getDmgmag() {
        return dmgmag;
    }


}
