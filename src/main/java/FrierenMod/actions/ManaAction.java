package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.powers.AbstractBasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ManaAction extends AbstractGameAction {
    private final Mana.Type type;
    private AbstractCard c;

    public ManaAction(Mana.Type type) {
        this.type = type;
    }

    public ManaAction(AbstractCard c, Mana.Type type) {
        this(type);
        this.c = c;
    }

    @Override
    public void update() {
        switch (type) {
            case NORMAL:
                this.addToBot(new DrawCardAction(1));
                break;
            case ACCEL:
                this.addToBot(new DrawCardAction(2));
                break;
            case LIMITED_OVER:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                break;
            case LIMITED_OVER_ACCEL:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                this.addToBot(new DrawCardAction(2));
                break;
            default:
                break;
        }
        this.triggerPowers();
        this.triggerCards();
        this.isDone = true;
    }

    private void triggerCardsInGroup(CardGroup group) {
        for (AbstractCard c : group.group)
            if (c instanceof AbstractBaseCard) {
                this.addToBot(new AfterSynchroFinishedAction((AbstractBaseCard) c));
            }
    }

    private void triggerCards() {
        AbstractPlayer p = AbstractDungeon.player;
        triggerCardsInGroup(p.drawPile);
        triggerCardsInGroup(p.discardPile);
        triggerCardsInGroup(p.hand);
    }

    private void triggerPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractPower po : p.powers)
            if (po instanceof AbstractBasePower) {
                this.addToBot(new AfterSynchroFinishedAction((AbstractBasePower) po));
            }
    }
}
