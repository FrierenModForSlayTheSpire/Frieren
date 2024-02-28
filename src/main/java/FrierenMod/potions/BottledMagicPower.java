package FrierenMod.potions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class BottledMagicPower extends AbstractPotion {
    public static final String POTION_ID = "BottledMagicPower";

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack
            .getPotionString("BottledMagicPower");

    public static final String NAME = potionStrings.NAME;

    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BottledMagicPower() {
        super(potionStrings.NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, PotionSize.FAIRY, AbstractPotion.PotionColor.BLUE);
        this.isThrown = false;
    }

    public void use(AbstractCreature abstractCreature) {
        MagicPower MagicPower = new MagicPower();
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(MagicPower.makeStatEquivalentCopy(), this.potency));
    }

    public int getPotency(int i) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new BottledMagicPower();
    }
}
