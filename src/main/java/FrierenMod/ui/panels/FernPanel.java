package FrierenMod.ui.panels;

import FrierenMod.actions.MakeSpecializedOffensiveMagicAction;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.patches.PanelPatch;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.FernRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class FernPanel extends AbstractPanel {
    public static final String ID = ModInformation.makeID(FernPanel.class.getSimpleName());

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private static final Color MPTextColor = new Color(0.9F, 1.0F, 1.0F, 1.0F);

    private static final Texture MPImage = new Texture(FernRes.MP_LAYER);

    private static final Texture MPWrapImage = new Texture(FernRes.MP_WRAP_LAYER);

    private static final Texture MPWrapPinkImage = new Texture(FernRes.MP_WRAP_PINK_LAYER);

    private static final Texture GainMPImage = new Texture(FernRes.GAIN_MP_VFX);

    private final Hitbox TipHitBox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);

    private final Color VFXColor = Color.WHITE.cpy();

    private float fontScale = 1.0F;

    private float OrbAngle = 0.0F;

    private float VFXAngle = 0.0F;

    private float VFXScale = Settings.scale;

    private float VFXTimer = 0.0F;

    private AbstractCard previewCard;

    private int rate = 2;

    private int totalCount = 1;

    public FernPanel() {
        super(124.0F * Settings.xScale, 414.0F * Settings.yScale, -720.0F * Settings.xScale, 540.0F * Settings.yScale, 42.0F * Settings.scale, -12.0F * Settings.scale, null, true);
    }

    public static boolean canShowThisPanel() {
        if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN)
            return false;
        if (CardCrawlGame.isInARun()) {
            return AbstractDungeon.player.chosenClass == CharacterEnums.FERN;
        }
        return false;
    }

    public void clearMP() {
        this.rate = 2;
        this.totalCount = 1;
    }

    public void update() {
        updateOrb();
        updateVFX();
        updateAction();
//        if (this.FontScale != 1.0F)
//            this.FontScale = MathHelper.scaleLerpSnap(this.FontScale, 1.0F);
        this.TipHitBox.update();
        if (this.TipHitBox.hovered && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            this.previewCard = CombatHelper.getPreviewSpecializedOffensiveMagic(getBaseDamage());
            if (this.previewCard != null) {
                previewCard.current_x = this.current_x;
                previewCard.current_y = this.current_y + 200.0F * Settings.scale;
            }
        }
    }

    public void updateAction() {
        if (this.TipHitBox.hovered) {
            if (InputHelper.justClickedRight) {
                InputHelper.justClickedRight = false;
                if (AbstractDungeon.actionManager.actions.stream().anyMatch(action -> action instanceof MakeSpecializedOffensiveMagicAction))
                    return;
                if (AbstractDungeon.actionManager.turnHasEnded)
                    return;
                if (!CombatHelper.isInCombat())
                    return;
                if (getCurrentEnergy() == 0) {
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, uiStrings.TEXT[2], true));
                    return;
                }
                int damage = getBaseDamage();
                AbstractDungeon.actionManager.addToBottom(new MakeSpecializedOffensiveMagicAction(damage));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ConcentrationPower.POWER_ID));
                useEnergy(1);
            }
        }
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBaseDamage() {
        return CombatHelper.getConcentrationPowerAmt() * this.rate;
    }

    public void render(SpriteBatch sb) {
        if (!this.isHidden && CardCrawlGame.isInARun() && CombatHelper.isInCombat() && canShowThisPanel()) {
            this.TipHitBox.move(this.current_x, this.current_y);
            renderOrb(sb);
            renderVFX(sb);
            AbstractDungeon.player.getEnergyNumFont().getData().setScale(this.fontScale);
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), this.totalCount + "/" + getMaxEnergy(), this.current_x, this.current_y, MPTextColor.cpy());
            this.TipHitBox.render(sb);
            if (this.TipHitBox.hovered && !AbstractDungeon.isScreenUp) {
                TipHelper.renderGenericTip(this.current_x + (MPImage.getWidth()) / 2.0F * Settings.scale, this.current_y + (MPImage.getHeight()) / 2.0F * Settings.scale, uiStrings.TEXT[0], String.format(uiStrings.TEXT[1], rate, totalCount));
                if (previewCard != null) {
                    previewCard.render(sb);
                }
            }
        }
    }

    private boolean isEmpty() {
        return this.totalCount == 0;
    }

    private void updateOrb() {
        this.fontScale = this.isEmpty() ? 1.0F : 1.2F;
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
        sb.draw(MPImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, this.OrbAngle, 0, 0, 256, 256, false, false);
        sb.draw((this.totalCount == 0) ? MPWrapImage : MPWrapPinkImage, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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

    public static void addEnergy(int e) {
        FernPanel instance = PanelPatch.PanelField.FernPanel.get(AbstractDungeon.player);
        instance.totalCount += e;
        if (instance.totalCount > 999) {
            instance.totalCount = 999;
        }
        instance.VFXTimer = 2.0F;
    }

    public static void setEnergy(int energy) {
        FernPanel instance = PanelPatch.PanelField.FernPanel.get(AbstractDungeon.player);
        instance.totalCount = energy;
        AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
        instance.VFXTimer = 2.0F;
    }

    public static void useEnergy(int e) {
        FernPanel instance = PanelPatch.PanelField.FernPanel.get(AbstractDungeon.player);
        instance.totalCount -= e;
        if (instance.totalCount < 0)
            instance.totalCount = 0;
//        if (e != 0)
//            instance.fontScale = 2.0F;
    }

    public static int getCurrentEnergy() {
        return AbstractDungeon.player == null ? 0 : PanelPatch.PanelField.FernPanel.get(AbstractDungeon.player).totalCount;
    }

    public static int getMaxEnergy() {
        return AbstractDungeon.player == null ? 1 : PanelPatch.PanelField.fernPanelMaxEnergy.get(AbstractDungeon.player);
    }

    public static void setMaxEnergy(int newMaxEnergy) {
        if(AbstractDungeon.player != null)
            PanelPatch.PanelField.fernPanelMaxEnergy.set(AbstractDungeon.player, newMaxEnergy);
    }

    public static void recharge() {
        setEnergy(getMaxEnergy());
    }
}
