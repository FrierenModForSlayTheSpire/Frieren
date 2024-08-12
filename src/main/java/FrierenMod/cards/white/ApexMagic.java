package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;

public class ApexMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ApexMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.ALL_ENEMY);

    public ApexMagic() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            if (CombatHelper.getManaNumInDrawPile() == 4 && CombatHelper.getManaNumInHand() == 4 && CombatHelper.getManaNumInDiscardPile() == 4) {
                this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
                for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
                    if (!mo.halfDead && !mo.isDying && !mo.isEscaping)
                        this.addToBot(new InstantKillAction(mo));
            }
        });
    }

    public void triggerOnGlowCheck() {
        if (CombatHelper.getManaNumInDrawPile() == 4 && CombatHelper.getManaNumInHand() == 4 && CombatHelper.getManaNumInDiscardPile() == 4)
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
