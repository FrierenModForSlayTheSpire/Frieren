package FrierenMod.gameHelpers.Savables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class SerializableCardLite {
    public static Object[] toObjectArray(AbstractCard ac) {
        Object[] cardArray = new Object[18];
        cardArray[0] = ac.cardID;
        AbstractCard unmoddedCopy = getUnmoddedCopy(ac);
        if (unmoddedCopy.upgraded != ac.upgraded)
            cardArray[1] = ac.upgraded;
        if (unmoddedCopy.timesUpgraded != ac.timesUpgraded)
            cardArray[2] = ac.timesUpgraded;
        for (int i = 0; i < ac.timesUpgraded; i++)
            unmoddedCopy.upgrade();
        if (unmoddedCopy.cost != ac.cost)
            cardArray[3] = ac.cost;
        if (unmoddedCopy.baseDamage != ac.baseDamage)
            cardArray[4] = ac.baseDamage;
        if (unmoddedCopy.baseBlock != ac.baseBlock)
            cardArray[5] = ac.baseBlock;
        if (unmoddedCopy.baseMagicNumber != ac.baseMagicNumber)
            cardArray[6] = ac.baseMagicNumber;
        if (unmoddedCopy.baseHeal != ac.baseHeal)
            cardArray[7] = ac.baseHeal;
        if (unmoddedCopy.baseDraw != ac.baseDraw)
            cardArray[8] = ac.baseDraw;
        if (unmoddedCopy.baseDiscard != ac.baseDiscard)
            cardArray[9] = ac.baseDiscard;
        if (unmoddedCopy.misc != ac.misc)
            cardArray[10] = ac.misc;
        if (unmoddedCopy.color != ac.color)
            cardArray[11] = ac.color.toString();
        if (unmoddedCopy.type != ac.type)
            cardArray[12] = ac.type.toString();
        if (unmoddedCopy.rarity != ac.rarity)
            cardArray[13] = ac.rarity.toString();
        return cardArray;
    }

    public static AbstractCard toAbstractCard(Object[] sc) {
        if (sc == null || sc[0] == null || !CardLibrary.isACard((String)sc[0]))
            return new Madness();
        AbstractCard card = getUnmoddedCopy((String)sc[0]);
        if (sc[2] != null && (int)((Double)sc[2]).doubleValue() > 0) {
            card.timesUpgraded = (int)((Double)sc[2]).doubleValue() - 1;
            card.upgrade();
        }
        if (sc[3] != null)
            card.cost = (int)((Double)sc[4]).doubleValue();
        card.costForTurn = card.cost;
        if (sc[4] != null)
            card.baseDamage = (int)((Double)sc[5]).doubleValue();
        if (sc[5] != null)
            card.baseBlock = (int)((Double)sc[6]).doubleValue();
        if (sc[6] != null)
            card.baseMagicNumber = (int)((Double)sc[7]).doubleValue();
        card.magicNumber = card.baseMagicNumber;
        if (sc[7] != null)
            card.baseHeal = (int)((Double)sc[8]).doubleValue();
        if (sc[8] != null)
            card.baseDraw = (int)((Double)sc[9]).doubleValue();
        if (sc[9] != null)
            card.baseDiscard = (int)((Double)sc[10]).doubleValue();
        if (sc[10] != null)
            card.misc = (int)((Double)sc[11]).doubleValue();
        if (sc[11] != null)
            card.color = AbstractCard.CardColor.valueOf((String)sc[12]);
        if (sc[12] != null)
            card.type = AbstractCard.CardType.valueOf((String)sc[13]);
        if (sc[13] != null)
            card.rarity = AbstractCard.CardRarity.valueOf((String)sc[14]);
        return card;
    }
    public static AbstractCard getUnmoddedCopy(AbstractCard card) {
        return card.makeCopy();
    }
    public static AbstractCard getUnmoddedCopy(String cardId) {
        if (!CardLibrary.isACard(cardId))
            return new Madness();
        return CardLibrary.getCard(cardId).makeCopy();
    }
}
