package FrierenMod.cards.optionCards.ChantOptions;

import FrierenMod.actions.AfterChantFinishedAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.powers.AbstractBasePower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMagicFactor extends AbstractBaseCard {
    public int currentSlot; //-1表示未装载，0表示抽，1手，2弃
    public static final String[] LOAD_MESSAGES = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicFactorLoadMessages")).TEXT;
    public static final String[] BAN_TIPS = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicFactorBanTips")).TEXT;
    public byte[] banSlot;
    public ArrayList<AbstractGameAction> extraActions;

    public AbstractMagicFactor(String ID) {
        super(new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE));
        this.currentSlot = -1;
    }

    public abstract void takeEffect();

    public List<TooltipInfo> getCustomTooltips() {
        String banTipTitle = BAN_TIPS[3];
        String banTipDescription = getBanTipDescription();
        if (getBanTipDescription() != null) {
            this.tips.clear();
            this.tips.add(new TooltipInfo(banTipTitle, banTipDescription));
        }
        return this.tips;
    }

    public String getBanTipDescription() {
        if (banSlot == null || banSlot.length == 0 || Arrays.equals(banSlot, new byte[]{0, 0, 0})) {
            return null;
        }
        String description = "";
        ArrayList<String> banTypeStrings = new ArrayList<>();
        if (banSlot[0] == 1)
            banTypeStrings.add(BAN_TIPS[0]);
        if (banSlot[1] == 1)
            banTypeStrings.add(BAN_TIPS[1]);
        if (banSlot[2] == 1)
            banTypeStrings.add(BAN_TIPS[2]);
        if (banTypeStrings.isEmpty())
            return null;
        if (banTypeStrings.size() == 1)
            description = BAN_TIPS[4] + banTypeStrings.get(0) + BAN_TIPS[5];
        if (banTypeStrings.size() == 2)
            description = BAN_TIPS[4] + banTypeStrings.get(0) + BAN_TIPS[6] + banTypeStrings.get(1) + BAN_TIPS[5];
        return description;
    }

    public String getCombatDescription(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION;
    }

    public String getDeckDescription(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id).EXTENDED_DESCRIPTION[0] + LOAD_MESSAGES[currentSlot + 1];
    }

    public void setCurrentSlot(int currentSlot) {
        this.currentSlot = currentSlot;
    }

    public void setDescriptionByShowPlaceType(ShowPlaceType type) {
        switch (type) {
            case COMBAT:
                this.rawDescription = getCombatDescription(this.cardID);
                break;
            case DECK:
                this.rawDescription = getDeckDescription(this.cardID);
                break;
            default:
                this.rawDescription = "ERROR!";
                break;
        }
        this.initializeDescription();
    }

    public enum ShowPlaceType {
        COMBAT,
        DECK
    }

    @Override
    public void onChoseThisOption() {
        ActionHelper.addToBotAbstract(()->{
            takeEffect();
            triggerPowers();
            triggerCards();
            if (extraActions != null && !extraActions.isEmpty()) {
                for (AbstractGameAction action : extraActions) {
                    this.addToBot(action);
                }
            }
        });
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void loadMagicFactor(AbstractGameAction[] nextAction, int manaNeed, int chantX) {
        this.extraActions = new ArrayList<>();
        if (nextAction != null && nextAction.length > 0)
            this.extraActions.addAll(Arrays.asList(nextAction));
        this.magicNumber = this.baseMagicNumber = manaNeed;
        this.secondMagicNumber = this.baseSecondMagicNumber = chantX;
        this.isSecondMagicNumberModified = (manaNeed < chantX);
        this.setDescriptionByShowPlaceType(ShowPlaceType.COMBAT);
    }

    public void loadMagicFactor(int manaNeed, int chantX) {
        this.extraActions = new ArrayList<>();
        this.magicNumber = this.baseMagicNumber = manaNeed;
        this.secondMagicNumber = this.baseSecondMagicNumber = chantX;
        this.isSecondMagicNumberModified = (manaNeed < chantX);
        this.setDescriptionByShowPlaceType(ShowPlaceType.COMBAT);
    }

    public void triggerPowers() {
        for (AbstractPower po : AbstractDungeon.player.powers)
            if (po instanceof AbstractBasePower) {
                ((AbstractBasePower) po).afterChant();
                this.addToBot(new AfterChantFinishedAction((AbstractBasePower) po));
            }

    }

    public void triggerCards() {
        AbstractPlayer p = AbstractDungeon.player;
        triggerCardsInCardGroup(p.drawPile);
        triggerCardsInCardGroup(p.hand);
        triggerCardsInCardGroup(p.discardPile);
    }

    protected void triggerCardsInCardGroup(CardGroup group) {
        for (AbstractCard c : group.group)
            if (c instanceof AbstractBaseCard) {
                ((AbstractBaseCard) c).afterChant();
                this.addToBot(new AfterChantFinishedAction((AbstractBaseCard) c));
            }
    }
}
