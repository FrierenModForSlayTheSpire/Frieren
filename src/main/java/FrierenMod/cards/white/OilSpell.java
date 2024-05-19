package FrierenMod.cards.white;

import FrierenMod.actions.OilSpellAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class OilSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OilSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ALL_ENEMY);

    public OilSpell() {
        super(info);
    }

//    public OilSpell(CardColor color) {
//        super(ID, 2, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ALL_ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 14;
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new OilSpellAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }
}
