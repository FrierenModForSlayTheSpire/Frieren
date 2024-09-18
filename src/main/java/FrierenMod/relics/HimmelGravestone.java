package FrierenMod.relics;

import FrierenMod.cards.tempCards.LongevityPain;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class HimmelGravestone extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(HimmelGravestone.class.getSimpleName());
    private boolean cardsReceived = true;

    public HimmelGravestone() {
        super(ID, RelicTier.BOSS);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HimmelGravestone();
    }

    public boolean isNormal() {
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS)
                return true;
        }
        return false;
    }

    public void onEquip() {
        this.cardsReceived = false;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        LongevityPain longevityPain = new LongevityPain();
        UnlockTracker.markCardAsSeen(longevityPain.cardID);
        group.addToBottom(longevityPain.makeCopy());
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
        AbstractDungeon.player.energy.energyMaster++;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }
}