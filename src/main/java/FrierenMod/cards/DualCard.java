package FrierenMod.cards;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.Config;
import FrierenMod.utils.FernRes;
import FrierenMod.utils.FrierenRes;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;

import static FrierenMod.utils.PublicRes.*;
import static FrierenMod.utils.PublicRes.BG_SKILL_FRIEREN_FERN_1024;

public abstract class DualCard extends AbstractBaseCard {
    public boolean isFrierenFernCard;
    public DualCard(CardInfo info) {
        super(info);
    }
    public void loadSpecifiedCardStyle(){
        if(this.isFrierenFernCard && Config.FERN_ENABLE){
            String img512, img1024;
            switch(this.type){
                case ATTACK:
                    img512 = BG_ATTACK_FRIEREN_FERN_512;
                    img1024 = BG_ATTACK_FRIEREN_FERN_1024;
                    break;
                case SKILL:
                    img512 = BG_SKILL_FRIEREN_FERN_512;
                    img1024 = BG_SKILL_FRIEREN_FERN_1024;
                    break;
                case POWER:
                    img512 = BG_POWER_FRIEREN_FERN_512;
                    img1024 = BG_POWER_FRIEREN_FERN_1024;
                    break;
                default:
                    img512 = BG_SKILL_FRIEREN_FERN_512;
                    img1024 = BG_SKILL_FRIEREN_FERN_1024;
                    System.out.println("?");
                    break;
            }
            this.setBackgroundTexture(img512, img1024);
            if(CombatHelper.isInCombat()){
                String energyOrb,bigOrb;
                if(AbstractDungeon.player.chosenClass == CharacterEnums.FERN){
                    energyOrb = FernRes.ENERGY_ORB;
                    bigOrb = FernRes.BIG_ORB;
                }else {
                    energyOrb = FrierenRes.ENERGY_ORB;
                    bigOrb = FrierenRes.BIG_ORB;
                }
                this.setOrbTexture(energyOrb,bigOrb);
            }
        }
    }
    public List<TooltipInfo> getCustomTooltips() {
        if(this.isFrierenFernCard && Config.FERN_ENABLE){
            this.tips.clear();
            this.tips.add(new TooltipInfo(CardCrawlGame.languagePack.getUIString("FrierenMod:DualCardTip").TEXT[0], CardCrawlGame.languagePack.getUIString("FrierenMod:DualCardTip").TEXT[1]));
        }
        return this.tips;
    }
}
