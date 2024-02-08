package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VaultPower;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class TimeDoor extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(TimeDoor.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public TimeDoor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 7;
        this.secondMagicNumber = this.baseSecondMagicNumber = 0;
        this.selfRetain =true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isMagicPower){
            this.flash(FLASH_COLOR);
            this.secondMagicNumber = this.baseSecondMagicNumber++;
        }
        if(secondMagicNumber >= magicNumber){
            AbstractPlayer p = AbstractDungeon.player;
            this.superFlash();
            this.secondMagicNumber = this.baseSecondMagicNumber = 0;
            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new ExhaustSpecificCardAction(this, p.hand));
        }
    }
}
