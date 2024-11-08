package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class MemoryOfChildhood extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(MemoryOfChildhood.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.NONE);

    public MemoryOfChildhood() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            ArrayList<AbstractCard> playCards = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c.cardID.equals(SpecializedOffensiveMagic.ID))
                    playCards.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : playCards) {
                c.freeToPlayOnce = true;
                switch (c.target) {
                    case SELF_AND_ENEMY:
                    case ENEMY:
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c,
                                AbstractDungeon.getRandomMonster()));
                        continue;
                }
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null));
            }
        });
    }
}

