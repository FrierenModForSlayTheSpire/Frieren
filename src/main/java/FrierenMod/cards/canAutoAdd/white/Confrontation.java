package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Confrontation extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Confrontation.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.NONE);

    public Confrontation() {
        super(info);
    }

//    public Confrontation(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.COMMON, CardTarget.NONE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = baseBlock = 5;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed instanceof AbstractMagicianCard && ((AbstractMagicianCard) cardPlayed).isChantCard)
            this.addToBot(new ModifyBlockAction(this.uuid, 3));
    }
}
