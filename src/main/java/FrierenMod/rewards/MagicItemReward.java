package FrierenMod.rewards;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardSave;

import java.util.ArrayList;

import static FrierenMod.patches.MagicItemRewardPatch.TypePatch.MAGIC_ITEM_REWARD;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class MagicItemReward extends CustomReward {

    public static String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(MagicItemReward.class.getSimpleName())).TEXT;
    private static final Texture ICON = ImageMaster.loadImage(PublicRes.MAGIC_ITEM_REWARD_ICON);
    public static final int baseRareMagicItemChance = 3;

    public static final int baseUncommonMagicItemChance = 17;
    public static final int basePropMagicItemChance = 50;

    public MagicItemReward(String saveString) {
        super(ICON, TEXT[0], MAGIC_ITEM_REWARD);
        ArrayList<AbstractCard> cards = decodeSavaString(saveString);
        init(cards);
    }

    public MagicItemReward() {
        super(ICON, TEXT[0], MAGIC_ITEM_REWARD);
        init(rollMagicItems());
    }

    public static void register() {
        BaseMod.registerCustomReward(MAGIC_ITEM_REWARD,
                rewardSave -> new MagicItemReward(rewardSave.id),
                customReward -> customReward.cards.isEmpty() ? new RewardSave(customReward.type.toString(), "") : new RewardSave(customReward.type.toString(), encodeSavaString(customReward.cards)));
    }

    @Override
    public boolean claimReward() {
        if (this.cards.isEmpty())
            return true;
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[1]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }

    private void init(ArrayList<AbstractCard> cards) {
        this.cards.clear();
        for (AbstractCard card : cards) {
            if (card == null)
                return;
            for (AbstractRelic relic : player.relics)
                relic.onPreviewObtainCard(card);
            this.cards.add(card);
        }
    }

    public static void addMagicItemRewardToRoom() {
        MagicItemReward magicItemReward = new MagicItemReward();
        if ((magicItemReward).cards.isEmpty())
            return;
        AbstractDungeon.getCurrRoom().addCardReward(magicItemReward);
    }

    public static ArrayList<AbstractCard> rollMagicItems() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        int numCards = 3;
        for (int i = 0; i < numCards; i++) {
            AbstractMagicItem.MagicItemRarity rarity = rollRarity();
            AbstractCard card = null;
            switch (rarity) {
                case COMMON:
                    RandomField.addMagicItemRandomizer(-RandomField.magicItemBlizzGrowth);
                    if (RandomField.getMagicItemRandomizer() <= RandomField.magicItemBlizzStartOffset) {
                        RandomField.magicItemRandomizer.set(RandomField.magicItemBlizzMaxOffset);
                    }
                case UNCOMMON:
                case PROP:
                    break;
                case RARE:
                    RandomField.magicItemRandomizer.set(RandomField.magicItemBlizzStartOffset);
                    break;
                default:
                    Log.logger.info("WTF?");
            }
            boolean containsDupe = true;
            while (containsDupe) {
                containsDupe = false;
                card = CardPoolHelper.getRandomMagicItem(rarity);
                for (AbstractCard c : retVal) {
                    if (c.cardID.equals(card.cardID)) {
                        containsDupe = true;
                        break;
                    }
                }
            }
            if (card != null)
                retVal.add(card);
        }
        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        for (AbstractCard c : retVal)
            retVal2.add(c.makeCopy());
        return retVal2;
    }

    public static AbstractMagicItem.MagicItemRarity rollRarity() {
        int roll = RandomField.getMagicItemRng().random(99);
        roll += RandomField.getMagicItemRandomizer();
        return getMagicItemRarity(roll);
    }

    private static AbstractMagicItem.MagicItemRarity getMagicItemRarity(int roll) {
        if (roll < baseRareMagicItemChance)
            return AbstractMagicItem.MagicItemRarity.RARE;
        if (roll < baseRareMagicItemChance + baseUncommonMagicItemChance)
            return AbstractMagicItem.MagicItemRarity.UNCOMMON;
        if (roll < baseRareMagicItemChance + baseUncommonMagicItemChance + basePropMagicItemChance)
            return AbstractMagicItem.MagicItemRarity.PROP;
        return AbstractMagicItem.MagicItemRarity.COMMON;
    }

    private static String encodeSavaString(ArrayList<AbstractCard> cards) {
        StringBuilder saveString = new StringBuilder();
        for (AbstractCard c : cards) {
            saveString.append(c.cardID).append(",");
        }
        return saveString.substring(0, saveString.length() - 1);
    }

    private static ArrayList<AbstractCard> decodeSavaString(String saveString) {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (String cardID : saveString.split(",")) {
            retVal.add(CardLibrary.getCopy(cardID));
        }
        return retVal;
    }
}
