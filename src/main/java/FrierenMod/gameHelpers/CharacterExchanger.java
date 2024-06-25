//package FrierenMod.gameHelpers;
//
//import FrierenMod.ModManager;
//import FrierenMod.actions.DestroySpecifiedCardAction;
//import FrierenMod.cards.testCards.ExchangeBack;
//import FrierenMod.relics.HaitaaWand;
//import FrierenMod.utils.FernRes;
//import FrierenMod.utils.Log;
//import FrierenMod.utils.ObjectCloner;
//import basemod.ReflectionHacks;
//import basemod.abstracts.CustomEnergyOrb;
//import basemod.abstracts.CustomPlayer;
//import com.badlogic.gdx.graphics.Texture;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.actions.common.GainEnergyAndEnableControlsAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.CardGroup;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
//import com.megacrit.cardcrawl.unlock.UnlockTracker;
//
//import java.util.Objects;
//
//public class CharacterExchanger {
//    public final AbstractPlayer self;
//    public final AbstractPlayer.PlayerClass target;
//    private final AbstractCard useCard;
//    private AbstractPlayer p = AbstractDungeon.player;
//    private final DataObject data = ModManager.saveData;
//    private final String targetSaveDataName;
//    private static final int MAX_STORAGE_SIZE = 300;
//
//    public CharacterExchanger(AbstractPlayer self, AbstractPlayer.PlayerClass target, AbstractCard useCard) {
//        this.self = ObjectCloner.copyObject(self);
//        this.target = target;
//        this.targetSaveDataName = target.name() + "_SAVE";
//        this.useCard = useCard;
//    }
//
//    public void initializeGameSaves() {
//        //Object[0]存储一局游戏的存档，Object[1]存储战斗中的存档
//        Object[] top = new Object[2];
//        top[0] = new Object[MAX_STORAGE_SIZE];
//        top[1] = new Object[MAX_STORAGE_SIZE];
//        data.putValue(targetSaveDataName, top);
//    }
//
//    public void load() {
//        basicLoadLogic();
//        if (!data.containsKey(targetSaveDataName)) {
//            initializeGameSaves();
//            initializeMasterDeck();
//            initializeCharacter();
//        }
//        ActionHelper.addToBotAbstract(() -> {
//            startFirstTurn();
////            if (((Object[]) Objects.requireNonNull(getSave(SaveType.COMBAT_SAVE))).length == 0) {
////
////            } else {
////
////            }
//        });
//
//        p.healthBarUpdatedEvent();
//        ReflectionHacks.privateMethod(AbstractCreature.class, "refreshHitboxLocation").invoke(p);
//    }
//
//    private void basicLoadLogic() {
//        p.img = getCharacterImage(target, "img");
//        p.corpseImg = getCharacterImage(target, "corpse");
//        p.chosenClass = target;
//        ReflectionHacks.setPrivate(p, CustomPlayer.class, "energyOrb", getCharacterOrb());
//        p.getEnergyImage();
//        p.relics.clear();
//        p.potions.clear();
//        p.currentBlock = 0;
//        EnergyPanel.totalCount = 0;
//        p.powers.clear();
//        AbstractDungeon.actionManager.cardsPlayedThisTurn.clear();
//        p.hand.clear();
//        p.drawPile.clear();
//        p.masterDeck.clear();
//        p.discardPile.clear();
//        p.exhaustPile.clear();
//        ActionHelper.addToBotAbstract(() -> {
//            AbstractDungeon.actionManager.addToBottom(new DestroySpecifiedCardAction(useCard, p.hand));
//            AbstractDungeon.actionManager.addToBottom(new DestroySpecifiedCardAction(useCard, p.discardPile));
//            AbstractDungeon.actionManager.addToBottom(new DestroySpecifiedCardAction(useCard, p.drawPile));
//            AbstractDungeon.actionManager.addToBottom(new DestroySpecifiedCardAction(useCard, p.exhaustPile));
//        });
//    }
//
//    private void initializeCharacter() {
//        p.currentHealth = p.maxHealth = 75;
//        p.relics.add(getCharacterRelic());
//        p.reorganizeRelics();
//        ActionHelper.addToBotAbstract(() -> p.drawPile.group.addAll(p.masterDeck.group));
//    }
//
//    private void initializeMasterDeck() {
//        CardGroup sealedGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//        for (int i = 0; i < 30; i++) {
//            AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity());
//            if (!sealedGroup.contains(card)) {
//                sealedGroup.addToBottom(card.makeCopy());
//            } else {
//                i--;
//            }
//        }
//        for (AbstractCard c : sealedGroup.group)
//            UnlockTracker.markCardAsSeen(c.cardID);
//        AbstractDungeon.gridSelectScreen.open(sealedGroup, 1, "test", false);
//        ActionHelper.addToBotAbstract(() -> {
//            if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
//                CardGroup cardsToSave = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
//                    cardsToSave.addToBottom(c.makeCopy());
//                AbstractDungeon.gridSelectScreen.selectedCards.clear();
//                for (int i = 0; i < cardsToSave.group.size(); i++) {
//                    AbstractCard c = cardsToSave.group.get(i);
//                    p.masterDeck.addToHand(c);
//                }
//            }
//        });
//    }
//    private void startFirstTurn() {
//        ActionHelper.addToBotAbstract(()->{
//            ExchangeBack c = new ExchangeBack();
//            c.setExchanger(this);
//            p.hand.addToHand(c);
//            AbstractDungeon.actionManager.addToBottom(new GainEnergyAndEnableControlsAction(AbstractDungeon.player.energy.energyMaster));
//            AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
//            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.gameHandSize));
//            AbstractDungeon.player.applyStartOfCombatLogic();
//            AbstractDungeon.getCurrRoom().skipMonsterTurn = false;
//            AbstractDungeon.player.applyStartOfTurnRelics();
//            AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
//            AbstractDungeon.player.applyStartOfTurnCards();
//            AbstractDungeon.player.applyStartOfTurnPowers();
//            AbstractDungeon.player.applyStartOfTurnOrbs();
//            AbstractDungeon.actionManager.useNextCombatActions();
//        });
//    }
//
//    private Object getSave(SaveType type) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(data.getValue(targetSaveDataName));
//        System.out.println(data.containsKey(targetSaveDataName));
//        System.out.println("!!!!!!!!!!!!!!!!!!!!");
//        Object[] save = (Object[]) data.getValue(targetSaveDataName);
//        switch (type) {
//            case ALL:
//                return save;
//            case GAME_SAVE:
//                return save[0];
//            case COMBAT_SAVE:
//                return save[1];
//        }
//        return null;
//    }
//
//    public void exchangeBack(){
//        p = self;
//    }
//    private Object loadGameSave(GameSaveType type) {
//        Object[] save = (Object[]) getSave(SaveType.GAME_SAVE);
//        if (save.length == 0) {
//            Log.logger.info("WHY NO FERN SAVE?");
//            return null;
//        }
//        switch (type) {
//            case ALL:
//                return save;
//            case MASTER_DECK:
//                return save[0];
//            case ATTRIBUTES:
//                return save[1];
//            default:
//                Log.logger.info("WHY NO FERN SAVE?");
//                return null;
//        }
//    }
//
//    private void saveGameSave(GameSaveType type, Object save) {
//        Object[] fernGameSave = (Object[]) loadGameSave(GameSaveType.ALL);
//        Object[][] masterDeckSave = (Object[][]) loadGameSave(GameSaveType.MASTER_DECK);
//        switch (type) {
//            case MASTER_DECK:
//                if (fernGameSave[0] != null) {
//                    System.arraycopy(((Object[]) save), 0, fernGameSave[0], 0, ((Object[]) save).length);
//                }
//                break;
//            case ATTRIBUTES:
//                if (fernGameSave[1] != null) {
//                    System.arraycopy(((Object[]) save), 0, fernGameSave[1], 0, ((Object[]) save).length);
//                }
//                break;
//        }
//        ModManager.saveData.putValue("FernGameSave", fernGameSave);
//    }
//
//    public static Texture getCharacterImage(AbstractPlayer.PlayerClass target, String type) {
//        if (Objects.equals(type, "img"))
//            return ImageMaster.loadImage(FernRes.CHARACTER_IMG);
//        else
//            return ImageMaster.loadImage(FernRes.CORPSE_IMAGE);
//    }
//
//    public static CustomEnergyOrb getCharacterOrb() {
//        return new CustomEnergyOrb(FernRes.ORB_TEXTURES, FernRes.ORB_VFX, FernRes.LAYER_SPEED);
//    }
//
//    public static AbstractRelic getCharacterRelic() {
//        return new HaitaaWand();
//    }
//
//    private enum SaveType {
//        ALL,
//        GAME_SAVE,
//        COMBAT_SAVE,
//
//    }
//
//    private enum GameSaveType {
//        ALL,
//        MASTER_DECK,
//        ATTRIBUTES
//    }
//    public static void copyPlayer(AbstractPlayer p){
//
//    }
//}
