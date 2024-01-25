package FrierenMod.cards.white;

import FrierenMod.helpers.LegendMagicHelper;
import FrierenMod.helpers.ModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OmegaFlashEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.LEGEND_MAGIC;

public class YouZiMoFa extends CustomCard{
    public static final String ID = ModHelper.makePath(YouZiMoFa.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    public YouZiMoFa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 12;
        this.tags.add(LEGEND_MAGIC);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractMonster> preMonsters = new ArrayList<>();
        ArrayList<AbstractMonster> curMonsters = new ArrayList<>();
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (mo != null && !mo.isDeadOrEscaped()) {
                preMonsters.add(mo);
            }
        }
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (mo != null && !mo.isDeadOrEscaped()) {
                curMonsters.add(mo);
            }
        }
        if(curMonsters.size() == preMonsters.size()){
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new LegendMagicHelper().canLegendMagicUse(this,p,m);
    }
}
