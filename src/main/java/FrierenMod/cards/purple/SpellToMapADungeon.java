package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class SpellToMapADungeon extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SpellToMapADungeon.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.SELF);

    public SpellToMapADungeon() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
        this.isEthereal = true;
        this.magicNumber = this.baseMagicNumber = 30;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (CardCrawlGame.metricData.path_taken.size() > 1) {
            if (CardCrawlGame.metricData.path_taken.get(CardCrawlGame.metricData.path_taken.size() - 2).equals("$")) {
                this.addToBot(new GainGoldAction(this.magicNumber));
            } else if (CardCrawlGame.metricData.path_taken.get(CardCrawlGame.metricData.path_taken.size() - 2).equals("R")) {
                ActionHelper.addToBotAbstract(() -> {
                    AbstractCard theCard = null;
                    ArrayList<AbstractCard> possibleCards = new ArrayList<>();
                    for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                        if (c.canUpgrade())
                            possibleCards.add(c);
                    }
                    if (!possibleCards.isEmpty()) {
                        theCard = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
                        theCard.upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(theCard);
                    }
                    if (theCard != null) {
                        AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(theCard.makeStatEquivalentCopy()));
                        addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    }
                });
            }
        }
    }
}

