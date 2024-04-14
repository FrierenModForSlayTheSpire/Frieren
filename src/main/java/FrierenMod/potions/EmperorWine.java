package FrierenMod.potions;


import FrierenMod.actions.DiscoverSpecifiedRarityCardAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class EmperorWine extends AbstractBasePotion {
    public static final String POTION_ID = ModInformation.makeID(EmperorWine.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;

    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public EmperorWine() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, AbstractPotion.PotionSize.SPHERE, AbstractPotion.PotionColor.ENERGY);
    }

    public void initializeData() {
        this.isThrown = false;
        this.potency = getPotency();
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[0];
        } else {
            this.description = potionStrings.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }
    @Override
    public int getPotency(int i) {
        return 1;
    }
    public void use(AbstractCreature target) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            this.addToBot(new DiscoverSpecifiedRarityCardAction(AbstractCard.CardRarity.RARE,this.potency));
    }
    public AbstractPotion makeCopy() {
        return new EmperorWine();
    }
}
