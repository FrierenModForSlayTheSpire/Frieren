package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.LegendMagicHelper;
import FrierenMod.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.LEGEND_MAGIC;

public class CustomLegendMagic extends AbstractFrierenCard {
    public static final String ID = ModHelper.makePath(CustomLegendMagic.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = -1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    public CustomLegendMagic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(LEGEND_MAGIC);
    }
    @Override
    public void upgrade() {
        return;
    }
    public boolean canUpgrade(){
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(this.magicNumber != -1){
            if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                return false;
            } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                return false;
            } else if (new ChantHelper().cannotChant(this.magicNumber) || new LegendMagicHelper().cannotPlayLegendMagic()){
                return false;
            }
            else {
                return this.cardPlayable(m) && this.hasEnoughEnergy();
            }
        }
        else {
            return new LegendMagicHelper().canLegendMagicUse(this,m);
        }
    }
}
