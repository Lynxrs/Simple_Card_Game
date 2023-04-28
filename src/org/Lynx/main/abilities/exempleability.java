package org.Lynx.main.abilities;

public class exempleability {
    private String name;
    private Boolean Ismagic;
    private int manacost;
    private int dmgphys;
    private int dmgmag;

    public exempleability(String name, Boolean ismagic, int manacost, int dmgphys, int dmgmag) {
        this.name = name;
        this.Ismagic = ismagic;
        this.manacost = manacost;
        this.dmgphys = dmgphys;
        this.dmgmag = dmgmag;
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

    public int getDmgphys() {
        return dmgphys;
    }

    public int getDmgmag() {
        return dmgmag;
    }


}
