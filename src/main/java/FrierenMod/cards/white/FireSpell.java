package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Laziness;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class FireSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FireSpell.class.getSimpleName());

    public FireSpell() {
        super(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public FireSpell(CardColor color) {
        super(ID, 2, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.baseDamage = 18;
        this.isMultiDamage = true;
        this.cardsToPreview = new Laziness();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy(), 1));
    }
}
