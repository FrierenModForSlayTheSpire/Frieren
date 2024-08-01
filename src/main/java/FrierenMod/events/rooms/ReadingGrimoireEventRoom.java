package FrierenMod.events.rooms;

import FrierenMod.events.ReadingGrimoireEvent;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.EventRoom;

public class ReadingGrimoireEventRoom extends EventRoom {
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        this.event = new ReadingGrimoireEvent();
        this.event.onEnterRoom();
    }
}
