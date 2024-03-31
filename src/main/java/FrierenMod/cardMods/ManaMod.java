package FrierenMod.cardMods;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.Mana;
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
    private final int type;

    public ManaMod(int type) {
        this.type = type;
    }

    public AbstractCardModifier makeCopy() {
        return new ManaMod(type);
    }

    public void onInitialApplication(AbstractCard card) {
        switch (type){
            case 1:
                ((AbstractMagicianCard)card).isAccelMana = false;
                ((AbstractMagicianCard)card).isLimitedOverMana = false;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.STATUS;
                if (card instanceof Mana)
                    ((Mana)card).loadCardImage(ModInformation.makeCardImgPath("Mana"));
                break;
            case 2:
                ((AbstractMagicianCard)card).isAccelMana = true;
                ((AbstractMagicianCard)card).isLimitedOverMana = false;
                card.target = AbstractCard.CardTarget.NONE;
                card.type = AbstractCard.CardType.STATUS;
                if (card instanceof Mana)
                    ((Mana)card).loadCardImage(ModInformation.makeCardImgPath("Mana2"));
                break;
            case 3:
                ((AbstractMagicianCard)card).isAccelMana = false;
                ((AbstractMagicianCard)card).isLimitedOverMana = true;
                card.baseDamage = 20;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                if (card instanceof Mana)
                    ((Mana)card).loadCardImage(ModInformation.makeCardImgPath("Mana3"));
                break;
            case 4:
                ((AbstractMagicianCard)card).isAccelMana = true;
                ((AbstractMagicianCard)card).isLimitedOverMana = true;
                card.baseDamage = 20;
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                card.type = AbstractCard.CardType.ATTACK;
                card.exhaust = true;
                if (card instanceof Mana)
                    ((Mana)card).loadCardImage(ModInformation.makeCardImgPath("Mana4"));
                break;
            default:
                break;
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.removeIf(action1 -> action1 instanceof ManaAction);
        switch (type){
            case 1:
                this.addToBot(new ManaAction(1));
                break;
            case 2:
                this.addToBot(new ManaAction(2));
                break;
            case 3:
                this.addToBot(new ManaAction(card,3));
                break;
            case 4:
                this.addToBot(new ManaAction(card,4));
                break;
            default:
                break;
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[type - 1];
    }
}