package FrierenMod.cardMods;

import FrierenMod.actions.AbstractFrierenAction;
import FrierenMod.actions.MagicPowerAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MagicPowerMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(MagicPowerMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final int type;

    public MagicPowerMod(int type) {
        this.type = type;
    }

    public AbstractCardModifier makeCopy() {
        return new MagicPowerMod(type);
    }

    public void onInitialApplication(AbstractCard card) {
        switch (type){
            case 1:
                ((AbstractFrierenCard)card).isFastMagicPower = false;
                ((AbstractFrierenCard)card).isFinalMagicPower = false;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.SKILL;
                if (card instanceof MagicPower)
                    ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/MagicPower_skill.png");
                break;
            case 2:
                ((AbstractFrierenCard)card).isFastMagicPower = true;
                ((AbstractFrierenCard)card).isFinalMagicPower = false;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.SKILL;
                if (card instanceof MagicPower)
                    ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/MagicPower2.png");
                break;
            case 3:
                ((AbstractFrierenCard)card).isFastMagicPower = false;
                ((AbstractFrierenCard)card).isFinalMagicPower = true;
                card.baseDamage = 20;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                if (card instanceof MagicPower)
                    ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/MagicPower3.png");
                break;
            case 4:
                ((AbstractFrierenCard)card).isFastMagicPower = true;
                ((AbstractFrierenCard)card).isFinalMagicPower = true;
                card.baseDamage = 20;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                card.exhaust = true;
                if (card instanceof MagicPower)
                    ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/MagicPower4.png");
                break;
            default:
                break;
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.removeIf(action1 -> action1 instanceof AbstractFrierenAction && ((AbstractFrierenAction) action1).isMagicPowerAction);
        switch (type){
            case 1:
                this.addToBot(new MagicPowerAction(1));
                break;
            case 2:
                this.addToBot(new MagicPowerAction(2));
                break;
            case 3:
                this.addToBot(new MagicPowerAction(card,3));
                break;
            case 4:
                this.addToBot(new MagicPowerAction(card,4));
                break;
            default:
                break;
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        switch (type){
            case 1:
                return TEXT[0];
            case 2:
                return TEXT[1];
            case 3:
                return TEXT[2];
            case 4:
                return TEXT[3];
            default:
                return "ERROR";
        }
    }
}