package FrierenMod.events;

import FrierenMod.cards.white.RingletForm;
import FrierenMod.relics.FakeFlammeGrimoire;
import FrierenMod.relics.FlammeGrimoire;
import FrierenMod.relics.MimicHead;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class MimicFight extends AbstractImageEvent {
    public static String ID = ModInformation.makeID(MimicFight.class.getSimpleName());
    public static EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString(ID);
    public int state = 0;

    public MimicFight() {
        super(EVENT_STRINGS.NAME, EVENT_STRINGS.DESCRIPTIONS[1], ModInformation.makeEventImgPath("MimicEvent1"));
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[0]);
        this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[1]);
    }

    public void onEnterRoom() {
        RoomEventDialog.waitForInput = true;
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.state) {
            case 0:
                if (buttonPressed == 0 || buttonPressed == 1) {
                    this.state = 1;
                    this.imageEventText.loadImage(ModInformation.makeEventImgPath("MimicEvent2"));
                    this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[1]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[2]);
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[3]);
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[4]);
                    return;
                }
                return;
            case 1:
                switch (buttonPressed) {
                    //战斗
                    case 0:
                        (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Slavers");
                        (AbstractDungeon.getCurrRoom()).eliteTrigger = true;
                        (AbstractDungeon.getCurrRoom()).rewards.clear();
                        if (AbstractDungeon.player.hasRelic(FakeFlammeGrimoire.ID))
                            AbstractDungeon.getCurrRoom().addRelicToRewards(new FlammeGrimoire());
                        else
                            AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(new MimicHead());
                        AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        enterCombatFromImage();
                        return;
                    //卷发
                    case 1:
                        this.imageEventText.loadImage(ModInformation.makeEventImgPath("MimicEvent3"));
                        this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[2]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[5]);
                        AbstractDungeon.player.damage(new DamageInfo(null, 15));
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new RingletForm(), (float) Settings.WIDTH / 2, (float) (Settings.HEIGHT / 2)));
                        this.state = 3;
                        return;
                    //肥伦
                    case 2:
                        this.imageEventText.loadImage(ModInformation.makeEventImgPath("MimicEvent4"));
                        this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[3]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[5]);
                        this.state = 3;
                        return;
                }
                return;
            case 3:
                if (buttonPressed == 0) {
                    openMap();
                    return;
                }
                return;
        }
        openMap();
    }
}
