package FrierenMod.cards.testCards;

import FrierenMod.ModManager;
import FrierenMod.actions.DestroySpecifiedCardAction;
import FrierenMod.actions.MakeCardsAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CharacterExchanger;
import FrierenMod.gameHelpers.Savables.SerializableCardLite;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.relics.HaitaaWand;
import FrierenMod.relics.HolyEmblem;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.FernRes;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Exchange extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Exchange.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.NONE);
    public AbstractPlayer FrierenSave;

    public Exchange() {
        super(info);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                CharacterExchanger exchanger = new CharacterExchanger(p,CharacterEnums.FERN,this);
                exchanger.load();
            }
        });
    }

    private void saveFrieren() {
    }

    private void loadFrieren() {

    }

    private void loadFern() {
        AbstractPlayer p = AbstractDungeon.player;
        Log.logger.info("Changing character image...");
        p.img = ImageMaster.loadImage(FernRes.CHARACTER_IMG);
        p.chosenClass = CharacterEnums.FERN;
        Log.logger.info("Changing orb style...");
        ReflectionHacks.setPrivate(p, CustomPlayer.class, "energyOrb", getFernOrb());
        p.getEnergyImage();
        Log.logger.info("Replace starter relic...");
        if (p.hasRelic(HolyEmblem.ID)) {
            int slot = p.relics.indexOf(p.getRelic(HolyEmblem.ID));
            p.relics.set(slot, new HaitaaWand());
        }
        p.reorganizeRelics();
        Log.logger.info("Clear current block...");
        p.currentBlock = 0;
        Log.logger.info("Clear current powers...");
        p.powers.clear();
        Log.logger.info("Apply Fern base power...");
        this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(p, 0)));
        Log.logger.info("Clear card played this turn history...");
        AbstractDungeon.actionManager.cardsPlayedThisTurn.clear();
        Log.logger.info("Clear card piles...");
        p.hand.clear();
        p.drawPile.clear();
        p.masterDeck.clear();
        p.discardPile.clear();
        p.exhaustPile.clear();
        ActionHelper.addToBotAbstract(() -> {
            this.addToBot(new DestroySpecifiedCardAction(this, p.hand));
            this.addToBot(new DestroySpecifiedCardAction(this, p.discardPile));
            this.addToBot(new DestroySpecifiedCardAction(this, p.drawPile));
            this.addToBot(new DestroySpecifiedCardAction(this, p.exhaustPile));
        });
        if (!ModManager.saveData.getBool("FernGameSave")) {
            initializeFern();
        } else {
            loadFernFromGameSave();
        }
        ActionHelper.addToBotAbstract(() -> {
            if (!ModManager.saveData.getBool("FernCombatSave")) {
                initializeFernFromCombatSave();
            } else {
                getFernFromCombatSave();
            }
        });
        p.healthBarUpdatedEvent();
        ReflectionHacks.privateMethod(AbstractCreature.class, "refreshHitboxLocation").invoke(p);

    }

    private void initializeFern() {
        Log.logger.info("!!!!!!Start initializing Fern!!!!!!");
        Log.logger.info("___________>Initializing Fern GameSave Structure<___________");
        ModManager.saveData.putValue("FernGameSave", new Object[300][300]);
        Log.logger.info("___________<Initialized Fern GameSave Structure>___________");
        Log.logger.info("___________>Initializing Fern MasterDeck<___________");
        CardGroup sealedGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < 30; i++) {
            AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity());
            if (!sealedGroup.contains(card)) {
                sealedGroup.addToBottom(card.makeCopy());
            } else {
                i--;
            }
        }
        for (AbstractCard c : sealedGroup.group)
            UnlockTracker.markCardAsSeen(c.cardID);
        AbstractDungeon.gridSelectScreen.open(sealedGroup, 10, "test", false);
        ActionHelper.addToBotAbstract(() -> {
            if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                CardGroup cardsToSave = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                    cardsToSave.addToBottom(c.makeCopy());
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                for (int i = 0; i < cardsToSave.group.size(); i++) {
                    AbstractCard c = cardsToSave.group.get(i);
                    AbstractDungeon.player.masterDeck.addToHand(c);
                }
            }
        });
        Log.logger.info("___________<Initialized Fern MasterDeck>___________");
        Log.logger.info("___________>Initializing Fern Attributes<___________");
        AbstractPlayer p = AbstractDungeon.player;
        p.maxHealth = 75;
        Log.logger.info("Set Fern maxHealth To " + p.maxHealth);
        p.currentHealth = 75;
        Log.logger.info("Set Fern currentHealth To " + p.currentHealth);
        Log.logger.info("___________<Initialized Fern Attributes>___________");
        Log.logger.info("!!!!!!Finished initializing Fern!!!!!!");
    }


    private void initializeFernFromCombatSave() {
        ArrayList<AbstractRelic> relics = new ArrayList<>();
        AbstractDungeon.actionManager.cardsPlayedThisCombat.clear();
        ArrayList<AbstractCard> drawPile = new ArrayList<>(AbstractDungeon.player.masterDeck.group);
        shuffle(drawPile, AbstractDungeon.cardRng.random);
        ArrayList<AbstractCard> hand = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            hand.add(drawPile.remove(0));
        }
        this.addToBot(new MakeCardsAction(drawPile, hand, null, null));
    }

    private void loadFernFromGameSave() {
        AbstractPlayer p = AbstractDungeon.player;
        Log.logger.info("___________>Loading Fern MasterDeck<___________");
        p.masterDeck.clear();
        Object[] scMasterDeck = (Object[]) loadFernGameSave(GameSaveType.MASTER_DECK);
        ArrayList<AbstractCard> masterDeck = new ArrayList<>();
        if (scMasterDeck != null) {
            for (Object sc : scMasterDeck) {
                AbstractCard c = SerializableCardLite.toAbstractCard((Object[]) sc);
                masterDeck.add(c);
            }
        }
        p.masterDeck.group.addAll(masterDeck);
        Log.logger.info("___________<Loaded Fern MasterDeck>___________");
        Log.logger.info("___________>Loading Fern Attributes<___________");
        Object[] scAttributes = (Object[]) loadFernGameSave(GameSaveType.ATTRIBUTES);
        p.currentHealth = (int) scAttributes[0];
        p.maxHealth = (int) scAttributes[1];
        Log.logger.info("___________<Loading Fern Attributes>___________");
    }

    private void getFernFromCombatSave() {

    }


    private void saveFern() {
        AbstractPlayer p = AbstractDungeon.player;
        int masterDeckSize = p.masterDeck.size();
        Object[] scMasterDeck = new Object[masterDeckSize];
        Log.logger.info("Saving Fern Master Deck...");
        for (int i = 0; i < masterDeckSize; i++) {
            AbstractCard c = p.masterDeck.group.get(i);
            scMasterDeck[i] = SerializableCardLite.toObjectArray(c);
            Log.logger.info("Saved [ " + c.cardID + " ] to Fern Deck!");
        }
        saveFernGameSave(GameSaveType.MASTER_DECK, scMasterDeck);
    }

    public static CustomEnergyOrb getFernOrb() {
        return new CustomEnergyOrb(FernRes.ORB_TEXTURES, FernRes.ORB_VFX, FernRes.LAYER_SPEED);
    }

    private Object loadFernGameSave(GameSaveType type) {
        Object[][] save = (Object[][]) ModManager.saveData.getValue("FernGameSave");
        if (save == null) {
            Log.logger.info("WHY NO FERN SAVE?");
            return null;
        }
        switch (type) {
            case ALL:
                return save;
            case MASTER_DECK:
                return save[0];
            case ATTRIBUTES:
                return save[1];
            default:
                Log.logger.info("WHY NO FERN SAVE?");
                return null;
        }
    }

    private void saveFernGameSave(GameSaveType type, Object save) {
        Object[][] fernGameSave = (Object[][]) loadFernGameSave(GameSaveType.ALL);
        switch (type) {
            case MASTER_DECK:
                if (fernGameSave[0] != null) {
                    System.arraycopy(((Object[]) save), 0, fernGameSave[0], 0, ((Object[]) save).length);
                }
                break;
            case ATTRIBUTES:
                if (fernGameSave[1] != null) {
                    System.arraycopy(((Object[]) save), 0, fernGameSave[1], 0, ((Object[]) save).length);
                }
                break;
        }
        ModManager.saveData.putValue("FernGameSave", fernGameSave);
    }

    private enum GameSaveType {
        ALL,
        MASTER_DECK,
        ATTRIBUTES
    }
}
