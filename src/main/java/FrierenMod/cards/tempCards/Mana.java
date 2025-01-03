package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Mana extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Mana.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);

    public Mana() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.SYNCHRO);
        this.tags.add(Enum.MANA);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }


    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return hasEnoughEnergy();
    }

    @SpireOverride
    protected void renderCardBg(SpriteBatch sb, float x, float y) {
        Color renderColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        renderHelper(sb, renderColor, MANA_TEXTURE_IMG, x, y);
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
}
