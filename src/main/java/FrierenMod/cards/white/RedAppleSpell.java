package FrierenMod.cards.white;

import FrierenMod.actions.SwitchStrengthAndDexterityAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.GreenApple;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.SwitchStrengthAndDexterityPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedAppleSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RedAppleSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public RedAppleSpell() {
        super(info);
    }

//    public RedAppleSpell(CardColor color) {
//        super(ID, 1, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.cardsToPreview = new GreenApple();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SwitchStrengthAndDexterityAction());
        this.addToBot(new MakeTempCardInHandAction(new GreenApple()));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SwitchStrengthAndDexterityPower(AbstractDungeon.player)));
    }
}
