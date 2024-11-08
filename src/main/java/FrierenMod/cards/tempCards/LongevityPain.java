package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.relics.HimmelGravestone;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.vfx.NecronomicurseEffect;

public class LongevityPain extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(LongevityPain.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, -2, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
    private int times;

    public LongevityPain() {
        super(info);
        this.times = 10;
        this.cardsToPreview = new Fatalism();
        SoulboundField.soulbound.set(this, true);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.hasTag(Enum.SYNCHRO)) {
            this.times--;
            this.superFlash();
            if (this.times == 0) {
                this.times = 10;
                this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy()));
            }
        } else
            this.times = 10;
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += String.format(cardStrings.EXTENDED_DESCRIPTION[0], times);
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void onRemoveFromMasterDeck() {
        if (AbstractDungeon.player.hasRelic(HimmelGravestone.ID))
            AbstractDungeon.player.getRelic(HimmelGravestone.ID).flash();
        AbstractDungeon.effectsQueue.add(new NecronomicurseEffect(new LongevityPain(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
    }

    public void triggerOnExhaust() {
        if (AbstractDungeon.player.hasRelic(HimmelGravestone.ID))
            AbstractDungeon.player.getRelic(HimmelGravestone.ID).flash();
        addToBot(new MakeTempCardInHandAction(makeCopy()));
    }
}
