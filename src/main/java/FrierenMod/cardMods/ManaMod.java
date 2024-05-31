package FrierenMod.cardMods;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.ArrayList;

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
        ArrayList<AbstractGameAction> actionsToStore = new ArrayList<>();
        for (AbstractGameAction action2 : AbstractDungeon.actionManager.actions) {
            if (action2 instanceof RemoveSpecificPowerAction) {
                String poId = ReflectionHacks.getPrivate(action2, RemoveSpecificPowerAction.class, "powerToRemove");
                if (poId.equals(PenNibPower.POWER_ID) || poId.equals(VigorPower.POWER_ID)) {
                    actionsToStore.add(action2);
                }
            }
        }
        for (AbstractGameAction action3 : actionsToStore) {
            AbstractDungeon.actionManager.actions.remove(action3);
        }
        this.addToBot(new ManaAction(card, this.type, actionsToStore));
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