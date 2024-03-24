/*package FrierenMod.events;

import FrierenMod.Characters.Frieren;
import FrierenMod.relics.MimicHead;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.city.Beggar;
import com.megacrit.cardcrawl.events.city.Colosseum;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.SlaverBlue;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import java.util.Iterator;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static FrierenMod.utils.ModInformation.getImgPath;

public class MimicFight extends AbstractImageEvent {
    public static final String ID = "FrierenMod:MimicFight";

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;

    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;

    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private static final String INTRO_MSG = DESCRIPTIONS[0];
    public static final String IMG = getImgPath();
    private CurScreen position;

    public MimicFight() {
        super(NAME, DESCRIPTIONS[0], "images/events/MimicFight.jpg");
        this.position = CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractMonster[] retVal;
        Circlet circlet;
        switch (this.position) {
            case INTRO:
                switch (buttonPressed) {
                    default://哪个选项都一样，有百分之10是真宝箱
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        this.screenNum = 1;
                        (AbstractDungeon.getCurrRoom()).rewards.clear();
                        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                        AbstractDungeon.combatRewardScreen.open();
                        this.position = CurScreen.CHOOSE;
                        return;
                }
                return;
            case REWARD://真宝箱情况
                switch (buttonPressed) {
                    case 0:

                }

            case CHOOSE://选择战斗或者逃跑
                switch (buttonPressed){
                    case 0:
                        this.position = CurScreen.POST_COMBAT;
                        this.imageEventText.updateDialogOption(0, OPTIONS[6]);
                        this.imageEventText.clearRemainingOptions();
                        retVal = new AbstractMonster[1];
                        retVal[0] = (AbstractMonster)new Mimic();
                        (AbstractDungeon.getCurrRoom()).eliteTrigger = true;
                        enterCombatFromImage();
                        return;
                    case 1:
                        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        openMap();
                        break;
                }
            case POST_COMBAT:
                (AbstractDungeon.getCurrRoom()).rewardAllowed = true;
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.position = CurScreen.LEAVE;
                        if (!AbstractDungeon.player.hasRelic(MimicHead.ID)) {
                            MimicHead MimicHead = new MimicHead();
                        } else {
                            circlet = new Circlet();
                        }
                        //给奖励窗口里加宝箱怪的头
                }
                openMap();
                return;
            case LEAVE:
                switch (buttonPressed) {
                    case 0:
                        openMap();
                        break;
                }
                break;
        }
        openMap();


    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric("MimicFight", actionTaken);
    }

    private enum CurScreen {
        INTRO,
        CHOOSE,
        LEAVE,
        REWARD,
        POST_COMBAT;
    }
}
*/