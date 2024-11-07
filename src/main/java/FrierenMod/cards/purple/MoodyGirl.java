package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.*;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class MoodyGirl extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(MoodyGirl.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public MoodyGirl() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
    }

    public ArrayList<AbstractCard> getCardsToPreview() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Pouting());
        list.add(new Cheering());
        return list;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Pouting(), 1, true, true));
        this.addToBot(new MakeTempCardInDiscardAction(new Cheering(), 1));
    }
}

