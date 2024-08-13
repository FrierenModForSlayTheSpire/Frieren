package FrierenMod.cards.tempCards;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.SynchroWithoutManaPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Mana extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Mana.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    private static final TextureAtlas.AtlasRegion MANA_TEXTURE_IMG = getImg(ImageMaster.loadImage(PublicRes.BG_MANA_512));

    public Mana() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.exhaust = !AbstractDungeon.player.hasPower(SynchroWithoutManaPower.POWER_ID);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ManaAction(Type.NORMAL));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return true;
    }

    public enum Type {
        NORMAL,
        ACCEL,
        LIMITED_OVER,
        LIMITED_OVER_ACCEL
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
