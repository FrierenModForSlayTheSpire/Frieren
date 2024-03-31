package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaParcel extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ManaParcel.class.getSimpleName());

    public ManaParcel() {
        super(ID, -2, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public ManaParcel(CardColor color) {
        super(ID, -2, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    public void triggerWhenDrawn() {
        this.addToTop(new MakeTempCardInHandAction(new Mana(), this.magicNumber));
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
}
