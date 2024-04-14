package FrierenMod.cardMods;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ManaMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ManaMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final Mana.Type type;

    public ManaMod(Mana.Type type) {
        this.type = type;
    }

    public AbstractCardModifier makeCopy() {
        return new ManaMod(type);
    }

    public void onInitialApplication(AbstractCard card) {
        switch (type) {
            case NORMAL:
                ((AbstractBaseCard) card).isAccelMana = false;
                ((AbstractBaseCard) card).isLimitedOverMana = false;
                card.baseDamage = -1;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.STATUS;
                if (card instanceof Mana)
                    ((Mana) card).loadCardImage(ModInformation.makeCardImgPath("Mana"));
                break;
            case ACCEL:
                ((AbstractBaseCard) card).isAccelMana = true;
                ((AbstractBaseCard) card).isLimitedOverMana = false;
                card.baseDamage = -1;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.STATUS;
                if (card instanceof Mana)
                    ((Mana) card).loadCardImage(ModInformation.makeCardImgPath("Mana2"));
                break;
            case LIMITED_OVER:
                ((AbstractBaseCard) card).isAccelMana = false;
                ((AbstractBaseCard) card).isLimitedOverMana = true;
                card.baseDamage = 15;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                if (card instanceof Mana)
                    ((Mana) card).loadCardImage(ModInformation.makeCardImgPath("Mana3"));
                break;
            case LIMITED_OVER_ACCEL:
                ((AbstractBaseCard) card).isAccelMana = true;
                ((AbstractBaseCard) card).isLimitedOverMana = true;
                card.baseDamage = 15;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                if (card instanceof Mana)
                    ((Mana) card).loadCardImage(ModInformation.makeCardImgPath("Mana4"));
                break;
            default:
                break;
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.removeIf(action1 -> action1 instanceof ManaAction);
        this.addToBot(new ManaAction(card, this.type));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        switch (type) {
            case ACCEL:
                return TEXT[1];
            case LIMITED_OVER:
                return TEXT[2];
            case LIMITED_OVER_ACCEL:
                return TEXT[3];
            default:
                return TEXT[0];
        }
    }
}