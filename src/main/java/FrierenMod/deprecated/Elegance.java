package FrierenMod.deprecated;

import FrierenMod.actions.EleganceAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomSavable;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Type;

@Deprecated
public class Elegance extends AbstractMagicianCard implements CustomSavable<Integer> {
    public static final String ID = ModInformation.makeID(Elegance.class.getSimpleName());
    private int secondMiscVariable;
    public Elegance() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.misc = 8;
        this.secondMisc = 0;
        this.baseDamage = this.misc;
        this.baseBlock = this.misc;
        this.baseMagicNumber = this.secondMisc;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    public void applyPowers() {
        this.baseDamage = this.baseBlock = this.misc;
        this.magicNumber = this.baseMagicNumber = this.secondMisc;
        super.applyPowers();
        this.initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EleganceAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),this));
    }

    @Override
    public Integer onSave() {
        return secondMiscVariable;
    }

    @Override
    public void onLoad(Integer i) {
        if (i != null) {
            secondMiscVariable = i;
        }
    }
    @Override
    public Type savedType() {
        return new TypeToken<Integer>(){}.getType();
    }

    @Override
    public void onLoadedMisc() {
        this.secondMisc = secondMiscVariable;
        this.magicNumber = this.baseMagicNumber = this.secondMisc;
        this.initializeDescription();
    }
}
