package FrierenMod.patches;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.patches.fields.Magic_DeckField;
import FrierenMod.patches.fields.Magic_ItemsField;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.runHistory.TinyCard;
import com.megacrit.cardcrawl.screens.stats.RunData;
import javassist.CtBehavior;

import java.util.*;

public class MagicDeckHistoryPatch {
    private static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicDeckHistory"))).TEXT;
    private static final String MAGIC_DECK_NAME = "magic_deck";
    private static final String LOADED_FACTORS_NAME = "loaded_factors";

    @SpirePatch(clz = RunHistoryScreen.class, method = "reloadWithRunData")
    public static class PatchReloadWithRunData {
        @SpireInsertPatch(locator = ReloadCardsLocator.class, localvars = {"runData"})
        public static void Insert(RunHistoryScreen __instance, RunData runData) {
            reloadMagicItems(__instance, runData);
        }

        private static class ReloadCardsLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(RunHistoryScreen.class, "reloadCards");
                int line = LineFinder.findAllInOrder(ctBehavior, matcher)[0];
                return new int[]{line};
            }
        }

        private static void reloadMagicItems(RunHistoryScreen runHistoryScreen, RunData runData) {
            Hashtable<String, AbstractCard> rawNameToCards = new Hashtable<>();
            Hashtable<AbstractCard, Integer> cardCounts = new Hashtable<>();
            Hashtable<AbstractCard.CardRarity, Integer> cardRarityCounts = new Hashtable<>();
            CardGroup sortedMagicDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            List<String> magic_deck = Magic_DeckField.magic_deck.get(runData);
            List<String> loaded_factors = Magic_DeckField.loaded_factors.get(runData);
            Magic_ItemsField.magic_items.get(runHistoryScreen).clear();
            Magic_ItemsField.loaded_factors.get(runHistoryScreen).clear();
            if (magic_deck == null || magic_deck.isEmpty())
                return;
            if (loaded_factors != null && !loaded_factors.isEmpty()) {
                ArrayList<AbstractCard> new_loaded_factors = new ArrayList<>();
                for (String loaded_factor_id : loaded_factors) {
                    AbstractCard loaded_factor = CardLibrary.getCard(loaded_factor_id);
                    new_loaded_factors.add(loaded_factor);
                }
                Magic_ItemsField.loaded_factors.set(runHistoryScreen, new_loaded_factors);
            }
            for (String cardID : magic_deck) {
                AbstractCard card;
                if (rawNameToCards.containsKey(cardID)) {
                    card = rawNameToCards.get(cardID);
                } else {
                    card = cardForName(runData, cardID);
                }
                if (card != null) {
                    int value = cardCounts.containsKey(card) ? (cardCounts.get(card) + 1) : 1;
                    cardCounts.put(card, value);
                    rawNameToCards.put(cardID, card);
                    int rarityCount = cardRarityCounts.containsKey(card.rarity) ? (cardRarityCounts.get(card.rarity).intValue() + 1) : 1;
                    cardRarityCounts.put(card.rarity, rarityCount);
                }
            }
            sortedMagicDeck.clear();
            for (AbstractCard card : rawNameToCards.values())
                sortedMagicDeck.addToTop(card);
            sortedMagicDeck.sortAlphabetically(true);
            sortedMagicDeck.sortByRarityPlusStatusCardType(false);
            sortedMagicDeck = sortedMagicDeck.getGroupedByColor();
            for (AbstractCard card : sortedMagicDeck.group)
                Magic_ItemsField.magic_items.get(runHistoryScreen).add(new TinyCard(card, cardCounts.get(card)));
        }

        private static AbstractCard cardForName(RunData runData, String cardID) {
            String libraryLookupName = cardID;
            if (cardID.endsWith("+")) {
                libraryLookupName = cardID.substring(0, cardID.length() - 1);
            }


            AbstractCard card = CardLibrary.getCard(libraryLookupName);
            int upgrades = 0;
            if (card != null) {
                if (cardID.endsWith("+")) {
                    upgrades = 1;
                }
            } else if (libraryLookupName.contains("+")) {
                String[] split = libraryLookupName.split("\\+", -1);
                libraryLookupName = split[0];
                upgrades = Integer.parseInt(split[1]);
                card = CardLibrary.getCard(libraryLookupName);
            }

            if (card == null) {
                Log.logger.info("Could not find card named: {}", cardID);
                return null;
            } else {
                card = card.makeCopy();

                for (int i = 0; i < upgrades; ++i) {
                    card.upgrade();
                }

                return card;
            }
        }

    }

    @SpirePatch(clz = RunHistoryScreen.class, method = "renderRunHistoryScreen")
    public static class PatchRenderRunHistoryScreen {
        @SpireInsertPatch(locator = RunsDropDownLocator.class, localvars = {"sb", "yOffset", "relicBottom", "header2x"})
        public static void Insert(RunHistoryScreen __instance, SpriteBatch sb, float yOffset, float relicBottom, float header2x) {
            float deckBottom = getDeckBottom(__instance, yOffset);
            renderMagicDeck(__instance, sb, header2x, deckBottom - screenPosY(50.0F));
        }

        private static class RunsDropDownLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(RunHistoryScreen.class, "runsDropdown");
                int line = LineFinder.findAllInOrder(ctBehavior, matcher)[0];
                return new int[]{line};
            }
        }

        private static void renderMagicDeck(RunHistoryScreen runHistoryScreen, SpriteBatch sb, float x, float y) {
            ArrayList<TinyCard> magic_items = Magic_ItemsField.magic_items.get(runHistoryScreen);
            if (magic_items == null || magic_items.isEmpty())
                return;
            float screenX = ReflectionHacks.getPrivate(runHistoryScreen, RunHistoryScreen.class, "screenX");
            layoutTinyMagicItems(runHistoryScreen, magic_items, screenX + screenPosX(90.0F), y);
            int cardCount = 0;
            for (TinyCard card : magic_items) {
                card.render(sb);
                cardCount += card.count;
            }
            String LABEL_WITH_COUNT_IN_PARENS = ReflectionHacks.getPrivateStatic(RunHistoryScreen.class, "LABEL_WITH_COUNT_IN_PARENS");
            String mainText = String.format(LABEL_WITH_COUNT_IN_PARENS, TEXT[0], cardCount);
            renderSubHeadingWithMessage(sb, mainText, getLoadedFactorsMetricsString(Magic_ItemsField.loaded_factors.get(runHistoryScreen)), x, y);
        }

        private static String getLoadedFactorsMetricsString(ArrayList<AbstractCard> factors) {
            if (factors == null || factors.isEmpty())
                return "";
            if (factors.size() != 3)
                return "";
            String[] cardNames = new String[factors.size()];
            cardNames[0] = factors.get(0).name;
            cardNames[1] = factors.get(1).name;
            cardNames[2] = factors.get(2).name;
            return String.format(TEXT[1], cardNames[0], cardNames[1], cardNames[2]);
        }

        private static void layoutTinyMagicItems(RunHistoryScreen runHistoryScreen, ArrayList<TinyCard> cards, float x, float y) {
            float originX = x + screenPosX(60.0F);
            float originY = y - screenPosY(64.0F);
            float rowHeight = screenPosY(48.0F);
            float columnWidth = screenPosX(340.0F);
            int row = 0, column = 0;
            int desiredColumns = (cards.size() <= 36) ? 3 : 4;
            int cardsPerColumn = cards.size() / desiredColumns;
            int remainderCards = cards.size() - cardsPerColumn * desiredColumns;
            int[] columnSizes = new int[desiredColumns];
            Arrays.fill(columnSizes, cardsPerColumn);
            for (int i = 0; i < remainderCards; i++)
                columnSizes[i % desiredColumns] = columnSizes[i % desiredColumns] + 1;
            for (TinyCard card : cards) {
                if (row >= columnSizes[column]) {
                    row = 0;
                    column++;
                }
                float cardY = originY - row * rowHeight;
                card.hb.move(originX + column * columnWidth + card.hb.width / 2.0F, cardY);
                if (card.col == -1) {
                    card.col = column;
                    card.row = row;
                }
                row++;
                refreshScrollUpperBound(runHistoryScreen, cardY);
            }
        }

        private static void refreshScrollUpperBound(RunHistoryScreen runHistoryScreen, float cardY) {
            float scrollY = ReflectionHacks.getPrivate(runHistoryScreen, RunHistoryScreen.class, "scrollY");
            float originScrollUpperBound = ReflectionHacks.getPrivate(runHistoryScreen, RunHistoryScreen.class, "scrollUpperBound");
            float newScrollUpperBound = Math.max(originScrollUpperBound, scrollY - cardY * screenPosY(50.0F));
            ReflectionHacks.setPrivate(runHistoryScreen, RunHistoryScreen.class, "scrollUpperBound", newScrollUpperBound);
        }

        private float screenPos(float val) {
            return val * Settings.scale;
        }

        private static float screenPosX(float val) {
            return val * Settings.xScale;
        }

        private static float screenPosY(float val) {
            return val * Settings.yScale;
        }

        private static void renderSubHeadingWithMessage(SpriteBatch sb, String main, String description, float x, float y) {
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.buttonLabelFont, main, x, y, Settings.GOLD_COLOR);
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.cardDescFont_N, description, x + FontHelper.getSmartWidth(FontHelper.buttonLabelFont, main, 99999.0F, 0.0F), y - 4.0F * Settings.scale, Settings.CREAM_COLOR);
        }

    }

    @SpirePatch(clz = Metrics.class, method = "gatherAllData")
    public static class PatchGatherAllData {
        @SpirePrefixPatch
        public static void Prefix(Metrics __instance) {
            HashMap<Object, Object> params = ReflectionHacks.getPrivate(__instance, Metrics.class, "params");
            params.put(MAGIC_DECK_NAME, MagicDeckField.getDeck().getCardIdsForMetrics());
            if (getLoadedFactorsCardGroup(MagicDeckField.getDeck()) != null) {
                params.put(LOADED_FACTORS_NAME, Objects.requireNonNull(getLoadedFactorsCardGroup(MagicDeckField.getDeck())).getCardIdsForMetrics());
            }
        }
    }

    private static float getDeckBottom(RunHistoryScreen runHistoryScreen, float deckY) {
        int row = 0, column = 0;
        ArrayList<TinyCard> cards = ReflectionHacks.getPrivate(runHistoryScreen, RunHistoryScreen.class, "cards");
        float originY = deckY - PatchRenderRunHistoryScreen.screenPosY(64.0F);
        float rowHeight = PatchRenderRunHistoryScreen.screenPosY(48.0F);
        int cardsPerColumn = cards.size() / TinyCard.desiredColumns;
        int remainderCards = cards.size() - cardsPerColumn * TinyCard.desiredColumns;
        int[] columnSizes = new int[TinyCard.desiredColumns];
        Arrays.fill(columnSizes, cardsPerColumn);
        for (int i = 0; i < remainderCards; i++)
            columnSizes[i % TinyCard.desiredColumns] = columnSizes[i % TinyCard.desiredColumns] + 1;
        for (TinyCard ignored : cards) {
            if (row >= columnSizes[column]) {
                row = 0;
                column++;
            }
            row++;
        }
        return originY - row * rowHeight;
    }

    private static CardGroup getLoadedFactorsCardGroup(CardGroup magicDeck) {
        ArrayList<AbstractMagicItem> magicItems = new ArrayList<>();
        for (AbstractCard card : magicDeck.group) {
            if (card instanceof AbstractMagicItem && ((AbstractMagicItem) card).currentSlot > -1) {
                magicItems.add((AbstractMagicItem) card);
            }
        }
        if (magicItems.size() == 3) {
            magicItems.sort(Comparator.comparingInt(o -> o.currentSlot));
            CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractMagicItem item : magicItems) {
                retVal.addToTop(item);
            }
            return retVal;
        }
        Log.logger.info("Wrong number of magic items: {}", magicItems.size());
        return null;
    }

    @SpirePatch(clz = RunHistoryScreen.class, method = "update")
    public static class PatchUpdate {
        @SpireInsertPatch(rloc = 85)
        public static void Insert(RunHistoryScreen __instance) {
            ArrayList<TinyCard> magicItems = Magic_ItemsField.magic_items.get(__instance);
            if (magicItems != null && !magicItems.isEmpty()) {
                for (TinyCard card : magicItems) {
                    boolean didClick = card.updateDidClick();
                    if (didClick) {
                        CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        for (TinyCard addMe : magicItems)
                            cardGroup.addToTop(addMe.card);
                        CardCrawlGame.cardPopup.open(card.card, cardGroup);
                    }
                }
            }
        }
    }
}
