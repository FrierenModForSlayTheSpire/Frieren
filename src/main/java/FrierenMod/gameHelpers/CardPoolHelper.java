package FrierenMod.gameHelpers;

import FrierenMod.cards.optionCards.magicItems.*;
import FrierenMod.cards.white.*;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;
import FrierenMod.cards.whitePurple.RapidChant;
import FrierenMod.cards.whitePurple.ShavedIceSpell;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

;

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
        retVal.add(new DefensiveMagic());
        retVal.add(new RapidChant());
        retVal.add(new ContinualChant());
        retVal.add(new FlowerFieldSpell());
        retVal.add(new RustCleanMagic());
        retVal.add(new PerfectDefensiveMagic());
        retVal.add(new FinalChant());
        retVal.add(new PreciseChant());
        retVal.add(new TrueColours());
        retVal.add(new LureTheEnemyInDeep());
        return retVal;
    }

    public static ArrayList<AbstractCard> getLegendarySpellCardPool() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        retVal.add(new FlyingMagic());
        retVal.add(new ContinualChant());
        retVal.add(new AccessoriesSpell());
        retVal.add(new OilSpell());
        retVal.add(new LightningMagic());
        retVal.add(new Famehameha());
        retVal.add(new FairyTaleEnd());
        return retVal;
    }

    public static ArrayList<AbstractCard> getMagicItemCardPool(AbstractMagicItem.MagicItemRarity rarity) {
        ArrayList<AbstractCard> srcCardPool = new ArrayList<>();
        srcCardPool.add(new BetaFactor1());
        srcCardPool.add(new BetaFactor2());
        srcCardPool.add(new BetaFactor3());
        srcCardPool.add(new BetaFactor4());
        srcCardPool.add(new BetaFactor5());
        srcCardPool.add(new BetaFactor6());
        srcCardPool.add(new BetaFactor7());
        srcCardPool.add(new BetaFactor8());
        srcCardPool.add(new BetaFactor9());
        srcCardPool.add(new BetaFactor10());
        srcCardPool.add(new BetaFactor11());
        srcCardPool.add(new BetaProp1());
        srcCardPool.add(new BetaProp2());
        srcCardPool.add(new BetaProp3());
        srcCardPool.add(new BetaProp4());
        srcCardPool.add(new BetaProp5());
        srcCardPool.add(new BetaProp6());
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
                    if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).magicItemRarity != AbstractMagicItem.MagicItemRarity.PROP)
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
        return list.get(RandomField.getMagicItemRng().random(list.size() - 1));
    }

    public enum PoolType {
        CHANT,
        LEGENDARY_SPELL,
        MAGIC_FACTOR
    }
}
