package FrierenMod.events;

import FrierenMod.cards.tempCards.BirdCapturingSpell;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

public class AnimalWell extends AbstractImageEvent {
    public static final String ID = ModInformation.makeID(AnimalWell.class.getSimpleName());
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String BOMB_RESULT;
    private static final String UPGRADE_RESULT;
    private static final String SPELL_RESULT;
    private AnimalWell.CurScreen screen;
    private boolean pickCard = false;

    public AnimalWell() {
        super(NAME, DIALOG_1, "FrierenModResources/img/events/AnimalWell.png");
        this.screen = AnimalWell.CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0]);
        if (AbstractDungeon.player.masterDeck.hasUpgradableCards()) {
            this.imageEventText.setDialogOption(OPTIONS[1]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[4], true);
        }
        this.imageEventText.setDialogOption(OPTIONS[2],new BirdCapturingSpell());
    }

    public void update() {
        super.update();
        if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            c.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.pickCard = false;
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(BOMB_RESULT);
                        if (AbstractDungeon.player.hasRelic("Sozu")) {
                            AbstractDungeon.player.getRelic("Sozu").flash();
                        } else {
                            AbstractPotion p = PotionHelper.getPotion("FirePotion");
                            AbstractPotion p1 = PotionHelper.getPotion("ExplosivePotion");//还是给火焰药水
                            AbstractDungeon.player.obtainPotion(p);
                            AbstractDungeon.player.obtainPotion(p1);
                        }
                        this.imageEventText.loadImage("FrierenModResources/img/events/AnimalWell1.png");
                        break;
                    case 1:
                        this.pickCard = true;
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, OPTIONS[5], true, false, false, false);
                        this.imageEventText.updateBodyText(UPGRADE_RESULT);
                        this.imageEventText.loadImage("FrierenModResources/img/events/AnimalWell2.png");
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(SPELL_RESULT);
                        AbstractCard c = new BirdCapturingSpell();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
                        this.imageEventText.loadImage("FrierenModResources/img/events/AnimalWell3.png");
                        break;

                    default:
                        this.imageEventText.updateBodyText(SPELL_RESULT);
                        this.imageEventText.loadImage("FrierenModResources/img/events/AnimalWell3.png");
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[3]);
                this.screen = AnimalWell.CurScreen.RESULT;
                break;
            default:
                this.openMap();
        }

    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric("AnimalWell", actionTaken);
    }
    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("FrierenMod:AnimalWell");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        BOMB_RESULT = DESCRIPTIONS[1];
        UPGRADE_RESULT = DESCRIPTIONS[2];
        SPELL_RESULT = DESCRIPTIONS[3];
    }

    private static enum CurScreen {
        INTRO,
        RESULT;

        private CurScreen() {
        }
    }
}
