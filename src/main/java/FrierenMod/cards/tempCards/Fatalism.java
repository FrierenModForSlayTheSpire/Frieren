package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fatalism extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Fatalism.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 10, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL);

    public Fatalism() {
        super(info);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, 999));
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
            if (!mo.halfDead && !mo.isDying && !mo.isEscaping)
                this.addToBot(new LoseHPAction(mo, p, 999));
        this.addToBot(new LoseHPAction(p, p, 999));
    }

    @SpireOverride
    protected void renderBannerImage(SpriteBatch sb, float drawX, float drawY) {
        Color renderColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        renderHelper(sb, renderColor, ImageMaster.CARD_BANNER_RARE, drawX, drawY);
    }
}
