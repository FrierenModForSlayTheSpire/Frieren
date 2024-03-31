package FrierenMod.cards.white;

import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class Famehameha extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Famehameha.class.getSimpleName());

    public Famehameha() {
        super(ID, 4, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public Famehameha(CardColor color) {
        super(ID, 4, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 25;
        this.isLegendarySpell = true;
        this.isCostResetCard = true;
    }

    public void afterChant() {
        this.addToBot(new ModifyCostAction(this.uuid, -1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
}
