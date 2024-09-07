package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;
import FrierenMod.cards.whitePurple.RapidChant;
import FrierenMod.cards.whitePurple.ShavedIceSpell;
import FrierenMod.enums.CardEnums;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class CardPoolHelper {
    public static ArrayList<AbstractCard> getBaseFrierenFernCardPool() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        if (Config.FERN_ENABLE) {
            retVal.add(new OrdinaryOffensiveMagic(OrdinaryOffensiveMagic.info2));
            retVal.add(new RapidChant(RapidChant.info2));
            retVal.add(new ShavedIceSpell(ShavedIceSpell.info2));
        }
        retVal.add(new OrdinaryOffensiveMagic(OrdinaryOffensiveMagic.info));
        retVal.add(new RapidChant(RapidChant.info));
        retVal.add(new ShavedIceSpell(ShavedIceSpell.info));
        return retVal;
    }

    public static ArrayList<AbstractCard> getChantCardPool() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(CardEnums.FRIEREN_LIBRARY)) {
            if (c.hasTag(AbstractBaseCard.Enum.CHANT) && !c.hasTag(AbstractBaseCard.Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT)) {
                retVal.add(c.makeCopy());
            }
        }
        return retVal;
    }

    public static ArrayList<AbstractCard> getLegendarySpellCardPool() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(CardEnums.FRIEREN_LIBRARY)) {
            if (c.hasTag(AbstractBaseCard.Enum.LEGENDARY_SPELL) && !c.hasTag(AbstractBaseCard.Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT)) {
                retVal.add(c.makeCopy());
            }
        }
        return retVal;
    }

    public static ArrayList<AbstractCard> getMagicItemCardPool(AbstractMagicItem.MagicItemRarity rarity) {
        ArrayList<AbstractCard> srcCardPool = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(CardEnums.MAGIC_ITEM_LIBRARY)) {
            srcCardPool.add(c.makeCopy());
        }
        ArrayList<AbstractCard> common = new ArrayList<>();
        ArrayList<AbstractCard> uncommon = new ArrayList<>();
        ArrayList<AbstractCard> rare = new ArrayList<>();
        ArrayList<AbstractCard> prop = new ArrayList<>();
        for (AbstractCard c : srcCardPool) {
            if (c instanceof AbstractMagicItem) {
                switch (((AbstractMagicItem) c).magicItemRarity) {
                    case RARE:
                        rare.add(c);
                        break;
                    case UNCOMMON:
                        uncommon.add(c);
                        break;
                    case COMMON:
                        common.add(c);
                        break;
                    case PROP:
                        prop.add(c);
                        break;
                }
            }
        }
        if (rarity != null)
            switch (rarity) {
                case COMMON:
                case BASIC:
                    return common;
                case UNCOMMON:
                    return uncommon;
                case RARE:
                    return rare;
                case PROP:
                    return prop;
            }
        return srcCardPool;
    }

    public static CardGroup getMagicItemCardGroup(AbstractMagicItem.MagicItemRarity rarity) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : getMagicItemCardPool(rarity))
            retVal.addToTop(c);
        return retVal;
    }

    public static AbstractCard getRandomCard(PoolType type) {
        ArrayList<AbstractCard> list;
        switch (type) {
            case CHANT:
                list = getChantCardPool();
                break;
            case LEGENDARY_SPELL:
                list = getLegendarySpellCardPool();
                break;
            case MAGIC_FACTOR:
                list = getMagicItemCardPool(null);
                ArrayList<AbstractCard> retVal = new ArrayList<>();
                for (AbstractCard c : list) {
                    if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).magicItemRarity != AbstractMagicItem.MagicItemRarity.PROP && !c.hasTag(AbstractBaseCard.Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT))
                        retVal.add(c);
                }
                return retVal.get(RandomField.getMagicItemRandomRng().random(retVal.size() - 1));
            default:
                list = null;
                Log.logger.info("WTF?");
                break;
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard getRandomMagicItem(AbstractMagicItem.MagicItemRarity rarity) {
        ArrayList<AbstractCard> list = getMagicItemCardPool(rarity);
        if(RandomField.getMagicItemRng().random(100) > 20)
            list.removeIf(card -> card.hasTag(AbstractBaseCard.Enum.LESS_CHANCE_TO_MEET));
        list.removeIf(card -> card.hasTag(AbstractBaseCard.Enum.NEVER_DROP));
        return list.get(RandomField.getMagicItemRng().random(list.size() - 1));
    }

    public static AbstractCard getRandomMagicItem(AbstractMagicItem.MagicItemRarity rarity, boolean judgeByTag) {
        ArrayList<AbstractCard> list = getMagicItemCardPool(rarity);
        if (judgeByTag)
            list.removeIf(card -> card.hasTag(AbstractBaseCard.Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT));
        return list.get(RandomField.getMagicItemRng().random(list.size() - 1));
    }

    public static ArrayList<AbstractCard> getBasicMagicItems(int slot) {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        int numCards = 3;
        for (int i = 0; i < numCards; i++) {
            AbstractCard card = null;
            boolean containsDupe = true;
            while (containsDupe) {
                containsDupe = false;
                card = getRandomMagicItem(AbstractMagicItem.MagicItemRarity.COMMON, true);
                for (AbstractCard c : retVal) {
                    if (c.cardID.equals(card.cardID)) {
                        containsDupe = true;
                        break;
                    }
                }
            }
            if (card != null)
                retVal.add(card);
            if (card instanceof AbstractMagicItem)
                ((AbstractMagicItem) card).currentSlot = slot;
        }
        return retVal;
    }

    public enum PoolType {
        CHANT,
        LEGENDARY_SPELL,
        MAGIC_FACTOR
    }
}
