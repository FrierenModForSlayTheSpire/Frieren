package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.optionCards.Snuggle1;
import FrierenMod.cards.optionCards.Snuggle2;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Snuggle extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Snuggle.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public Snuggle() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            Snuggle1 option1 = new Snuggle1();
            Snuggle2 option2 = new Snuggle2();
            option1.magicNumber = option1.baseMagicNumber = option2.baseMagicNumber = option2.magicNumber = this.magicNumber;
            ArrayList<AbstractCard> options = new ArrayList<>();
            options.add(option1);
            options.add(option2);
            this.addToBot(new ChooseOneAction(options));
        });
    }
}

