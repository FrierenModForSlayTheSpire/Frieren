package FrierenMod.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.*;

import java.util.Iterator;

public class KraftGift extends AbstractImageEvent {
    public static final String ID = "KraftGift";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String MEAT_RESULT;
    private static final String MEMBERSHIPCARD_RESULT;
    private static final String RING_RESULT;
    private static final String NO_RESULT;
    private KraftGift.CurScreen screen;

    public KraftGift() {
        super(NAME, DIALOG_1, "FrierenModResources/img/events/Kraft1.png");
        this.screen = KraftGift.CurScreen.INTRO;
        if(AbstractDungeon.player.maxHealth >= 80)this.imageEventText.setDialogOption(OPTIONS[0],new MeatOnTheBone());
        else this.imageEventText.setDialogOption(OPTIONS[4], true);
        if(AbstractDungeon.player.gold >= 300)this.imageEventText.setDialogOption(OPTIONS[1], new MembershipCard());
        else this.imageEventText.setDialogOption(OPTIONS[5], true);
        if(AbstractDungeon.player.masterDeck.group.size()>= 30)this.imageEventText.setDialogOption(OPTIONS[2],new RingOfTheSerpent());
        else this.imageEventText.setDialogOption(OPTIONS[6], true);
        this.imageEventText.setDialogOption(OPTIONS[3]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(MEAT_RESULT);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new MeatOnTheBone());
                        logMetricObtainRelic("KraftGift", "Meat", new MeatOnTheBone());
                        this.imageEventText.loadImage("FrierenModResources/img/events/Kraft2.png");
                        break;
                    case 1:
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new MembershipCard());
                        this.imageEventText.updateBodyText(MEMBERSHIPCARD_RESULT);
                        logMetricObtainRelic("KraftGift", "MembershipCard", new MembershipCard());
                        this.imageEventText.loadImage("FrierenModResources/img/events/Kraft2.png");
                        break;
                    case 2:
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new RingOfTheSerpent());
                        this.imageEventText.updateBodyText(RING_RESULT);
                        logMetricObtainRelic("KraftGift", "Ring", new RingOfTheSerpent());
                        this.imageEventText.loadImage("FrierenModResources/img/events/Kraft2.png");
                        break;
                    case 3:
                        this.imageEventText.updateBodyText(NO_RESULT);
                        break;
                    default:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.loadImage("FrierenModResources/img/events/Kraft2.png");
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[7]);
                this.screen = KraftGift.CurScreen.RESULT;
                break;
            default:
                this.openMap();
        }

    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric("KraftGift", actionTaken);
    }
    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("FrierenMod:KraftGift");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        MEAT_RESULT = DESCRIPTIONS[1];
        MEMBERSHIPCARD_RESULT = DESCRIPTIONS[2];
        RING_RESULT = DESCRIPTIONS[3];
        NO_RESULT = DESCRIPTIONS[4];
    }

    private static enum CurScreen {
        INTRO,
        RESULT;

        private CurScreen() {
        }
    }
}
