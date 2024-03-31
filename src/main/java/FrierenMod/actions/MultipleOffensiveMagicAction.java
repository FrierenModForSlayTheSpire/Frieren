package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class MultipleOffensiveMagicAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard card;
    private final int energyOnUse;
    private final boolean isUpgraded;

    public MultipleOffensiveMagicAction(AbstractPlayer p, AbstractCard c, int energyOnUse, boolean isUpgraded) {
        this.p = p;
        this.card = c;
        this.energyOnUse = energyOnUse;
        this.isUpgraded = isUpgraded;
    }

    public void update() {
        int baseDamage = this.energyOnUse + 4;
        int counts = this.isUpgraded?this.energyOnUse+1:this.energyOnUse;
        int magicNum = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            baseDamage += 2;
            counts += 2;
            magicNum += 2;
            this.p.getRelic("Chemical X").flash();
        }
        this.card.baseDamage = baseDamage;
        for(int i = 0; i < counts; ++i) {
            this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            this.card.calculateCardDamage((AbstractMonster)this.target);
            if (this.target != null) {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.NONE));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.card.hb.cY)));
            }
        }
        this.addToBot(new MakeManaInHandAction(magicNum));
        this.p.energy.use(EnergyPanel.totalCount);
        this.isDone = true;
    }
}
