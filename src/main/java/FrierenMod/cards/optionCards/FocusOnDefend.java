package FrierenMod.cards.optionCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FocusOnDefend extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FocusOnDefend.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);

    public FocusOnDefend() {
        super(info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, ConcentrationPower.POWER_ID, 3));
    }
}
