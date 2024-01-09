package org.Lynx.main.abilities;

import org.Lynx.main.Main;
import org.Lynx.main.cards.Player;
import org.Lynx.main.cards.exemplecard;
import org.Lynx.main.effects.effects;

import java.util.ArrayList;
import java.util.List;

import static org.Lynx.main.Main.print;

public class exempleability {
    private String name;
    private Boolean Ismagic;
    private int manacost;
    private int dmgphys;
    private int dmgmag;
    private Boolean HasEffect;
    private String effectname;
    private List<effects> effectlist = new ArrayList<>();
    private Boolean selfhit;

    public exempleability(String name, Boolean ismagic, int manacost, int dmgphys, int dmgmag, Boolean hasEffect, String effectID, Boolean selfhit) {
        this.name = name;
        this.Ismagic = ismagic;
        this.manacost = manacost;
        this.dmgphys = dmgphys;
        this.dmgmag = dmgmag;
        this.HasEffect = hasEffect;
        this.selfhit = selfhit;
        if(this.getHasEffect()){
            this.effectname = effectID;
        }

    }

    public String getName() {
        return name;
    }

    public Boolean getIsmagic() {
        return Ismagic;
    }
    public void use(exemplecard caster, exemplecard target, Player pcaster, Player ptarget){
        if (caster == target && pcaster == ptarget && !pcaster.getSelectedabl().selfhit){
            print("vous ne pouvez vous infliger des dégats");
            use(caster,target,pcaster,ptarget);
        }
        if (pcaster.getSelectedabl().getIsmagic()){
            exempleability abl = pcaster.getSelectedabl();
            caster.magdmg(target, abl.getManacost());
            if(abl.getHasEffect()){
                target.getEffectlist().add(abl.getEffectname());
            }

        } else{
            exempleability abl = pcaster.getSelectedabl();
            caster.physdmg(target);
            if(abl.getHasEffect()){
                target.getEffectlist().add(Main.registeredabilities.get(Main.chooseability).getEffectname());
            }
        }

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


    public Boolean getHasEffect() {
        return HasEffect;
    }

    public void setHasEffect(Boolean hasEffect) {
        HasEffect = hasEffect;
    }

    public String getEffectname() {
        return effectname;
    }
}
