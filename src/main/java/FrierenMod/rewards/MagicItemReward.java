package FrierenMod.rewards;

import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardSave;

import java.util.ArrayList;

import static FrierenMod.patches.MagicItemRewardPatch.TypePatch.MAGIC_ITEM_REWARD;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class MagicItemReward extends CustomReward {

    public static String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(MagicItemReward.class.getSimpleName())).TEXT;

    public MagicItemReward(String saveString) {
        super(ImageMaster.loadImage(PublicRes.MAGIC_BAG_ICON), TEXT[0], MAGIC_ITEM_REWARD);
        ArrayList<AbstractCard> cards = decodeSavaString(saveString);
        init(cards);
    }

    public MagicItemReward() {
        super(ImageMaster.loadImage(PublicRes.MAGIC_BAG_ICON), TEXT[0], MAGIC_ITEM_REWARD);
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
        int numCards = 1;
        for (int i = 0; i < numCards; i++) {
            int rarity = rollRarity();
            AbstractCard card = null;
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

    public static int rollRarity() {
        int rnd = cardRandomRng.random(99);
//        if (rnd <= 3)
//            return 2;
//        if (rnd <= 20)
//            return 3;
//        if (rnd <= 50)
//            return 1;
        return 0;
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
