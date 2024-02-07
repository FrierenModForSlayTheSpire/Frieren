package FrierenMod.actions;

import FrierenMod.cards.tempCards.Apple;
import FrierenMod.powers.SwitchStrengthAndDexterityPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RedAppleMagicAction extends AbstractGameAction {

    @Override
    public void update() {
        this.addToBot(new SwitchStrengthAndDexterityAction());
        this.addToBot(new MakeTempCardInHandAction(new Apple()));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SwitchStrengthAndDexterityPower(AbstractDungeon.player)));
        this.isDone = true;
    }
}
