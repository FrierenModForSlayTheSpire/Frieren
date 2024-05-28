package FrierenMod.cards.optionCards.ChantOptions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractChantOption extends AbstractBaseCard {
    public int slotNumber; //-1表示未装载，0表示抽，1手，2弃
    public static final String[] LOAD_MESSAGES = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("ChantOptionCardLoadMessages")).TEXT;
    public static final String[] BAN_TIPS = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("ChantOptionCardBanTips")).TEXT;
    public byte[] banSlot;

    public AbstractChantOption(String ID) {
        super(new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE));
    }

    public AbstractChantOption(String ID, ShowPlaceType type) {
        super(new CardInfo(ID, "", CardType.SKILL, CardTarget.NONE));
        switch (type) {
            case COMBAT:
                this.rawDescription = getCombatDescription(ID);
                break;
            case BAG:
                this.rawDescription = getBagDescription(ID);
                break;
            default:
                this.rawDescription = "";
                break;
        }
        this.initializeDescription();
    }

    public List<TooltipInfo> getCustomTooltips() {
        String banTipTitle = BAN_TIPS[3];
        String banTipDescription = getBanTipDescription();
        if (getBanTipDescription() != null) {
            this.tips.clear();
            this.tips.add(new TooltipInfo(banTipTitle, banTipDescription));
        }
        return this.tips;
    }

    public String getBanTipDescription() {
        if (banSlot == null || banSlot.length == 0 || Arrays.equals(banSlot, new byte[]{0, 0, 0})) {
            return null;
        }
        String description = "";
        ArrayList<String> banTypeStrings = new ArrayList<>();
        if (banSlot[0] == 1)
            banTypeStrings.add(BAN_TIPS[0]);
        if (banSlot[1] == 1)
            banTypeStrings.add(BAN_TIPS[1]);
        if (banSlot[2] == 1)
            banTypeStrings.add(BAN_TIPS[2]);
        if (banTypeStrings.isEmpty())
            return null;
        if (banTypeStrings.size() == 1)
            description = BAN_TIPS[4] + banTypeStrings.get(0) + BAN_TIPS[5];
        if (banTypeStrings.size() == 2)
            description = BAN_TIPS[4] + banTypeStrings.get(0) + BAN_TIPS[6] + banTypeStrings.get(1) + BAN_TIPS[5];
        return description;
    }

    public String getCombatDescription(String id) {
        refreshCurrentSlot();
        return CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION;
    }

    //TODO: 从ModManager.save中获取装载槽位
    public void refreshCurrentSlot() {
        this.slotNumber = 1;
    }

    public String getBagDescription(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id).EXTENDED_DESCRIPTION[0] + LOAD_MESSAGES[slotNumber + 1];
    }

    public enum ShowPlaceType {
        COMBAT,
        BAG
    }
}
