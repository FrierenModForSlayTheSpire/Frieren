package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlammeGrimoire extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(FlammeGrimoire.class.getSimpleName());

    public FlammeGrimoire() {
        super(ID, RelicTier.SPECIAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void obtain() {
        AbstractPlayer player = AbstractDungeon.player;
        player.relics.stream()
                .filter(r -> r instanceof FakeFlammeGrimoire).findFirst()
                .map(r -> player.relics.indexOf(r))
                .ifPresent(index -> instantObtain(player, index, true));

        (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
    }

    @Override
    public void instantObtain() {
        this.obtain();
    }
}
