package FrierenMod.cards.white;

import FrierenMod.actions.ElementsBombingAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.*;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ElementsBombing extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ElementsBombing.class.getSimpleName());
    public ElementsBombing() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
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
    public ArrayList<AbstractCard> getCardsToPreview(){
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