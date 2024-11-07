package FrierenMod.cards.white;

import FrierenMod.actions.DrawPileToHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HighDensityMana extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(HighDensityMana.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.STATUS, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);

    public HighDensityMana() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.MANA);
        this.tags.add(Enum.SYNCHRO);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public boolean canUpgrade() {
        return !this.upgraded;
    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new GainEnergyAction(1));
    }

    @Override
    public void afterSynchroFinished(AbstractCard card) {
        if (AbstractDungeon.player.drawPile.contains(this) && CombatHelper.getContinualSynchroTimes(card) >= 3) {
            this.addToBot(new DrawPileToHandAction(this));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return hasEnoughEnergy();
    }

    @SpireOverride
    protected void renderType(SpriteBatch sb) {
        BitmapFont font = FontHelper.cardTypeFont;
        font.getData().setScale(this.drawScale);
        Color typeColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "typeColor");
        Color renderColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        typeColor.a = renderColor.a;
        FontHelper.renderRotatedText(sb, FontHelper.cardTypeFont, typeTEXT[0], this.current_x, this.current_y - 22.0F * this.drawScale * Settings.scale, 0.0F, -1.0F * this.drawScale * Settings.scale, this.angle, false, typeColor);
    }

    @SpireOverride
    protected void renderCardBg(SpriteBatch sb, float x, float y) {
        Color renderColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        renderHelper(sb, renderColor, MANA_TEXTURE_IMG, x, y);
    }
}
