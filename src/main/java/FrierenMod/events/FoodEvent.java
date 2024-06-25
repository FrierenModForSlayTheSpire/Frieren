package FrierenMod.events;

import FrierenMod.cards.tempCards.EmergencyFood;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class FoodEvent extends AbstractImageEvent {
    public static final String ID = "FoodEvent";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String FREE_RESULT;
    private static final String PACKAGE_RESULT;
    private static final String EAT_RESULT;
    private static final String EAT2_RESULT;
    private int healAmt1 = 15;
    private int healAmt2 =(int) (AbstractDungeon.player.maxHealth * 0.75);;
    private int healAmt3 = AbstractDungeon.player.maxHealth;
    private FoodEvent.CurScreen screen;

    public FoodEvent() {
        super(NAME, DIALOG_1, "FrierenModResources/img/events/FoodEvent.png");
        this.screen = FoodEvent.CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0] + this.healAmt1 + OPTIONS[1]);
        if(AbstractDungeon.player.gold>=75)this.imageEventText.setDialogOption(OPTIONS[2],new EmergencyFood());
        else this.imageEventText.setDialogOption(OPTIONS[7], true);
        if(AbstractDungeon.player.gold >= 150)this.imageEventText.setDialogOption(OPTIONS[3] + this.healAmt2 + OPTIONS[4],new Strawberry());
        else this.imageEventText.setDialogOption(OPTIONS[8], true);
        if(AbstractDungeon.player.gold >= 250)this.imageEventText.setDialogOption(OPTIONS[5],new Mango());
        else this.imageEventText.setDialogOption(OPTIONS[9], true);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.heal(this.healAmt1, true);
                        this.imageEventText.updateBodyText(FREE_RESULT);
                        AbstractEvent.logMetricHeal("FoodEvent", "Banana", this.healAmt1);
                        this.imageEventText.loadImage("FrierenModResources/img/events/FreeFood.png");
                        break;
                    case 1:
                        AbstractDungeon.player.loseGold(75);
                        AbstractCard c = new EmergencyFood();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
                        this.imageEventText.updateBodyText(PACKAGE_RESULT);
                        this.imageEventText.loadImage("FrierenModResources/img/events/Package.png");
                        break;
                    case 2:
                        AbstractDungeon.player.loseGold(150);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new Strawberry());
                        AbstractDungeon.player.heal(this.healAmt2, true);
                        this.imageEventText.updateBodyText(EAT_RESULT);
                        AbstractEvent.logMetricHeal("FoodEvent", "Banana", this.healAmt2);
                        this.imageEventText.loadImage("FrierenModResources/img/events/Meal.png");
                        break;
                    case 3:
                        AbstractDungeon.player.loseGold(250);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new Mango());
                        AbstractDungeon.player.heal(this.healAmt3, true);
                        this.imageEventText.updateBodyText(EAT2_RESULT);
                        AbstractEvent.logMetricHeal("FoodEvent", "Banana", this.healAmt3);
                        this.imageEventText.loadImage("FrierenModResources/img/events/BigMeal.png");
                        break;
                    default:
                        AbstractDungeon.player.heal(this.healAmt1, true);
                        this.imageEventText.updateBodyText(FREE_RESULT);
                        AbstractEvent.logMetricHeal("FoodEvent", "Banana", this.healAmt1);
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[6]);
                this.screen = FoodEvent.CurScreen.RESULT;
                break;
            default:
                this.openMap();
        }

    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric("FoodEvent", actionTaken);
    }
    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("FrierenMod:FoodEvent");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        FREE_RESULT = DESCRIPTIONS[1];
        PACKAGE_RESULT = DESCRIPTIONS[2];
        EAT_RESULT = DESCRIPTIONS[3];
        EAT2_RESULT = DESCRIPTIONS[4];
    }

    private static enum CurScreen {
        INTRO,
        RESULT;

        private CurScreen() {
        }
    }
}
