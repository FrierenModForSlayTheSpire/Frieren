package FrierenMod.cards.purple;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.whitePurple.ClearMind;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PureMind extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PureMind.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public PureMind() {
        super(info);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
            if(!this.hasTag(Enum.SPEED))
                this.tags.add(Enum.SPEED);
            this.cardsToPreview = new ClearMind();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(card -> card.hasTag(Enum.SPEED)).count() >= 2) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        } else
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeManaInDrawPileAction(2));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(card -> card.hasTag(Enum.SPEED)).count() >= 2) {
            this.addToBot(new MakeTempCardInHandAction(new ClearMind()));
        }
    }
}

