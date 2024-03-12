package FrierenMod.gameHelpers;

import FrierenMod.actions.SealCardsAction;
import basemod.interfaces.OnStartBattleSubscriber;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class OnStartBattleHelper implements OnStartBattleSubscriber {
    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AbstractDungeon.actionManager.addToBottom(new SealCardsAction());
    }
}
