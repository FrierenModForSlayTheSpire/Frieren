package FrierenMod.potions;

import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class BottledMana extends AbstractBasePotion {
    public static final String POTION_ID = ModInformation.makeID(BottledMana.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;

    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BottledMana() {
        super(potionStrings.NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, PotionSize.HEART, PotionColor.SWIFT);
    }
    public void initializeData() {
        this.isThrown = false;
        this.targetRequired = false;
        this.potency = getPotency();
        this.description = String.format(potionStrings.DESCRIPTIONS[0],this.potency);
        initializeDescription();
    }

    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            this.addToBot(new MakeTempCardInHandAction(new Mana(), this.potency));
    }

    public int getPotency(int i) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new BottledMana();
    }
}
