package FrierenMod.panels;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

public class ManaPanel extends AbstractPanel {
    public static final String ID = ModInformation.makeID(ManaPanel.class.getSimpleName());

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private static final Color MPTextColor = new Color(0.9F, 1.0F, 1.0F, 1.0F);

    private static final Texture MPImage = new Texture(FrierenRes.MP_LAYER);

    private static final Texture MPDarkImage = new Texture(FrierenRes.MP_DARK_LAYER);

    private static final Texture MPWrapImage = new Texture(FrierenRes.MP_WRAP_LAYER);
    private static final Texture MPWrapGoldenImage = new Texture(FrierenRes.MP_WRAP_GOLDEN_LAYER);

    private static final Texture GainMPImage = new Texture(FrierenRes.GAIN_MP_VFX);

    private static final float FontNormalScale = 1.0F;

    private static final float FontPopScale = 2.0F;

    private static final float OrbRotationSpeed = -30.0F;

    private static final float DarkOrbRotationSpeed = 6.0F;

    private static final float GainMPVFXTime = 1.0F;

    private static final float VFXRotationSpeed = -30.0F;

    private final Hitbox TipHitBox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);

    private final Color VFXColor = Color.WHITE.cpy();

    private float FontScale = 1.0F;

    private float OrbAngle = 0.0F;

    private float VFXAngle = 0.0F;

    private float VFXScale = Settings.scale;

    private float VFXTimer = 0.0F;

    private int drawPileCounter = 0;
    private int handCounter = 0;
    private int discardPileCounter = 0;


    public ManaPanel() {
        super(94.0F * Settings.xScale, 414.0F * Settings.yScale, -720.0F * Settings.xScale, 540.0F * Settings.yScale, 12.0F * Settings.scale, -12.0F * Settings.scale, null, true);
    }

    public static boolean canShowThisPanel() {
        if(AbstractDungeon.player.chosenClass == CharacterEnums.FERN)
            return false;
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN)
                return true;
            for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                if (card instanceof AbstractBaseCard && ((AbstractBaseCard) card).isMana)
                    return true;
            }
            if (CombatHelper.isInCombat()) {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof AbstractBaseCard)
                        return true;
                }
                for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                    if (card instanceof AbstractBaseCard)
                        return true;
                }
                for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                    if (card instanceof AbstractBaseCard)
                        return true;
                }
                for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
                    if (card instanceof AbstractBaseCard)
                        return true;
                }
                for (AbstractCard card : AbstractDungeon.player.limbo.group) {
                    if (card instanceof AbstractBaseCard)
                        return true;
                }
            }
            return false;
        }
        return false;
    }
    public void updateMP(){
        int draw = ChantHelper.getManaNumInDrawPile();
        int hand = ChantHelper.getManaNumInHand();
        int discard = ChantHelper.getManaNumInDiscardPile();
        boolean isIncreased = discard - this.discardPileCounter > 0 || draw - this.drawPileCounter > 0 || hand - this.handCounter > 0;
        if(isIncreased){
            this.FontScale = 2.0F;
            this.VFXTimer = 1.0F;
        }
        this.handCounter = hand;
        this.drawPileCounter = draw;
        this.discardPileCounter = discard;
    }

    public void clearMP() {
        this.handCounter = 0;
        this.drawPileCounter = 0;
        this.discardPileCounter = 0;
    }
    public void update() {
        updateMP();
        //updateOrb();
        updateVFX();
        if (this.FontScale != 1.0F)
            this.FontScale = MathHelper.scaleLerpSnap(this.FontScale, 1.0F);
        this.TipHitBox.update();
        if (this.TipHitBox.hovered && !AbstractDungeon.isScreenUp)
            AbstractDungeon.overlayMenu.hoveredTip = true;
    }

    public void render(SpriteBatch sb) {
        if (!this.isHidden && CardCrawlGame.isInARun() && CombatHelper.isInCombat() && canShowThisPanel()) {
            this.TipHitBox.move(this.current_x, this.current_y);
            renderOrb(sb);
            renderVFX(sb);
            AbstractDungeon.player.getEnergyNumFont().getData().setScale(this.FontScale);
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), String.valueOf(this.drawPileCounter), this.current_x, this.current_y + 100.0F, MPTextColor.cpy());
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), String.valueOf(this.handCounter), this.current_x, this.current_y, MPTextColor.cpy());
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), String.valueOf(this.discardPileCounter), this.current_x, this.current_y - 100.0F, MPTextColor.cpy());
            this.TipHitBox.render(sb);
            if (this.TipHitBox.hovered && !AbstractDungeon.isScreenUp)
                TipHelper.renderGenericTip(this.current_x + (this.isEmpty() ? MPDarkImage.getWidth() : MPImage.getWidth()) / 2.0F * Settings.scale, this.current_y + (this.isEmpty() ? MPDarkImage.getHeight() : MPImage.getHeight()) / 2.0F * Settings.scale, uiStrings.TEXT[0], uiStrings.TEXT[1]);
        }
    }
    private boolean isEmpty(){
        return this.drawPileCounter == 0 && this.handCounter == 0 && this.discardPileCounter == 0;
    }
    private void updateOrb() {
        this.OrbAngle += (this.isEmpty() ? 6.0F : -30.0F) * Gdx.graphics.getDeltaTime();
    }

    private void updateVFX() {
        if (this.VFXTimer > 0.0F) {
            this.VFXColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - this.VFXTimer / 2.0F);
            this.VFXAngle += -30.0F * Gdx.graphics.getDeltaTime();
            this.VFXScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - this.VFXTimer / 2.0F);
            this.VFXTimer -= Gdx.graphics.getDeltaTime();
            if (this.VFXTimer < 0.0F) {
                this.VFXTimer = 0.0F;
                this.VFXColor.a = 0.0F;
            }
        }
    }

    private void renderOrb(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw((this.isEmpty()) ? MPDarkImage : MPImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, this.OrbAngle, 0, 0, 256, 256, false, false);
        sb.draw((LegendarySpellHelper.cannotPlayLegendarySpell()) ? MPWrapImage : MPWrapGoldenImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
    }

    private void renderVFX(SpriteBatch sb) {
        if (this.VFXTimer > 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.VFXColor);
            sb.draw(GainMPImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.VFXScale, this.VFXScale, -this.VFXAngle + 50.0F, 0, 0, 256, 256, true, false);
            sb.draw(GainMPImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.VFXScale, this.VFXScale, this.VFXAngle, 0, 0, 256, 256, false, false);
            sb.setBlendFunction(770, 771);
        }
    }
}