package FrierenMod.cards.white;

import FrierenMod.actions.ElementsBombingAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.*;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ElementsBombing extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ElementsBombing.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.NONE);

    public ElementsBombing() {
        super(info);
    }

//    public ElementsBombing(CardColor color) {
//        super(ID, 3, CardType.SKILL, color, CardRarity.RARE, CardTarget.NONE);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public ArrayList<AbstractCard> getCardsToPreview() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Ice());
        list.add(new Fire());
        list.add(new Thunder());
        list.add(new Dark());
        list.add(new Light());
        return list;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ElementsBombingAction(this.upgraded));
    }
}