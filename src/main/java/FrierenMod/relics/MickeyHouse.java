package FrierenMod.relics;

import FrierenMod.cards.magicItems.props.UnbelievableTool;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class MickeyHouse extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(MickeyHouse.class.getSimpleName());

    public MickeyHouse() {
        super(ID, RelicTier.SHOP);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof com.megacrit.cardcrawl.rooms.EventRoom) {
            flash();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new UnbelievableTool(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }
}