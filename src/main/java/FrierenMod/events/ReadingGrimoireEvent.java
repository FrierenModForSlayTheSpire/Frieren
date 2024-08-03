package FrierenMod.events;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.cards.optionCards.magicItems.BetaProp1;
import FrierenMod.effects.FastMagicItemObtainEffect;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;

public class ReadingGrimoireEvent extends AbstractImageEvent {
    public static String ID = ModInformation.makeID(ReadingGrimoireEvent.class.getSimpleName());
    public static EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString(ID);
    public int state = 0;

    MapRoomNode currNode;

    MapRoomNode node;

    public ReadingGrimoireEvent() {
        super(EVENT_STRINGS.NAME, EVENT_STRINGS.DESCRIPTIONS[1], "FrierenModResources/img/events/Beta.png");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[0]);
    }

    public void onEnterRoom() {
        RoomEventDialog.waitForInput = true;
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.state) {
            case 0:
                if (buttonPressed == 0) {
                    this.state = 1;
                    this.imageEventText.loadImage("FrierenModResources/img/events/Beta.png");
                    this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[1]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[1]);
                    AbstractDungeon.cardRewardScreen.chooseOneOpen(CardPoolHelper.getBasicMagicItems(0));
                    ReflectionHacks.setPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class,"chooseOne",false);
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.dynamicBanner.appear(EVENT_STRINGS.DESCRIPTIONS[4]);
                    return;
                }
                return;
            case 1:
                if (buttonPressed == 0) {
                    this.state = 2;
                    this.imageEventText.loadImage("FrierenModResources/img/events/Beta.png");
                    this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[2]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[2]);
                    AbstractDungeon.cardRewardScreen.chooseOneOpen(CardPoolHelper.getBasicMagicItems(1));
                    ReflectionHacks.setPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class,"chooseOne",false);
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.dynamicBanner.appear(EVENT_STRINGS.DESCRIPTIONS[5]);
                    return;
                }
                return;
            case 2:
                if (buttonPressed == 0) {
                    this.state = 3;
                    this.imageEventText.loadImage("FrierenModResources/img/events/Beta.png");
                    this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[2]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[3]);
                    AbstractDungeon.cardRewardScreen.chooseOneOpen(CardPoolHelper.getBasicMagicItems(2));
                    ReflectionHacks.setPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class,"chooseOne",false);
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.dynamicBanner.appear(EVENT_STRINGS.DESCRIPTIONS[6]);
                    return;
                }
                return;
            case 3:
                if (buttonPressed == 0) {
                    this.state = 4;
                    this.imageEventText.loadImage("FrierenModResources/img/events/Beta.png");
                    this.imageEventText.updateBodyText(EVENT_STRINGS.DESCRIPTIONS[3]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(EVENT_STRINGS.OPTIONS[4]);
                    AbstractDungeon.effectList.add(new FastMagicItemObtainEffect(new BetaProp1()));
                    AbstractDungeon.effectList.add(new FastMagicItemObtainEffect(CardPoolHelper.getRandomMagicItem(AbstractMagicItem.MagicItemRarity.PROP)));
                    return;
                }
                return;
            case 4:
                if (buttonPressed == 0) {
                    for (AbstractGameEffect f : AbstractDungeon.effectList) {
                        if (f instanceof InfiniteSpeechBubble)
                            ((InfiniteSpeechBubble) f).dismiss();
                    }
                    this.imageEventText.clearAllDialogs();
                    GenericEventDialog.hide();
                    this.currNode = AbstractDungeon.getCurrMapNode();
                    this.node = new MapRoomNode(this.currNode.x, this.currNode.y);
                    this.node.setRoom(new NeowRoom(false) {
                        public void onPlayerEntry() {
                            AbstractDungeon.overlayMenu.proceedButton.hide();
                            this.event = new NeowEvent();
                            for (AbstractGameEffect f : AbstractDungeon.effectList) {
                                if (f instanceof InfiniteSpeechBubble)
                                    ((InfiniteSpeechBubble) f).dismiss();
                            }
                            this.event.onEnterRoom();
                        }
                    });
                    AbstractDungeon.dungeonMapScreen.dismissable = true;
                    AbstractDungeon.nextRoom = this.node;
                    AbstractDungeon.setCurrMapNode(this.node);
                    AbstractDungeon.getCurrRoom().onPlayerEntry();
                    AbstractDungeon.scene.nextRoom(this.node.room);
                    AbstractDungeon.currMapNode.setRoom(new NeowRoom(false));
                }
                return;
        }
        openMap();
    }
}