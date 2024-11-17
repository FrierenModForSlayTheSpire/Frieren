package FrierenMod.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CardInfo {
    public final String baseId;

    public final String name;

    public final String img;
    public final int baseCost;
    public final String rawDescription;
    public final AbstractCard.CardType cardType;
    public final AbstractCard.CardColor cardColor;

    public final AbstractCard.CardTarget cardTarget;

    public final AbstractCard.CardRarity cardRarity;

    public CardInfo(String baseId, String name, String img, int baseCost, String rawDescription, AbstractCard.CardType cardType, AbstractCard.CardColor cardColor, AbstractCard.CardRarity cardRarity, AbstractCard.CardTarget cardTarget) {
        this.baseId = baseId;
        this.name = name;
        this.img = img;
        this.baseCost = baseCost;
        this.rawDescription = rawDescription;
        this.cardType = cardType;
        this.cardColor = cardColor;
        this.cardRarity = cardRarity;
        this.cardTarget = cardTarget;
    }

    public CardInfo(String baseId, String rawDescription, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = -2;
        this.rawDescription = rawDescription;
        this.cardType = type;
        this.cardColor = AbstractCard.CardColor.COLORLESS;
        this.cardRarity = AbstractCard.CardRarity.SPECIAL;
        this.cardTarget = target;
    }

    public CardInfo(String baseId, String img, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = img;
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = type;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = target;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = type;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = target;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, boolean isSecondInfo) {
        if (isSecondInfo)
            this.baseId = dualCardIdMaker(baseId);
        else
            this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = type;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = target;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, boolean isSecondInfo) {
        if (isSecondInfo) {
            this.baseId = dualCardIdMaker(baseId);
        } else {
            this.baseId = baseId;
        }
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = type;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = AbstractCard.CardTarget.NONE;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity) {
        this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = type;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = AbstractCard.CardTarget.NONE;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardColor color, AbstractCard.CardRarity rarity) {
        this.baseId = baseId;
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = AbstractCard.CardType.SKILL;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = AbstractCard.CardTarget.NONE;
    }

    public CardInfo(String baseId, int cost, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, boolean isSecondInfo) {
        if (isSecondInfo) {
            this.baseId = dualCardIdMaker(baseId);
        } else {
            this.baseId = baseId;
        }
        this.name = CardCrawlGame.languagePack.getCardStrings(baseId).NAME;
        this.img = getImgPath(baseId);
        this.baseCost = cost;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(baseId).DESCRIPTION;
        this.cardType = AbstractCard.CardType.SKILL;
        this.cardColor = color;
        this.cardRarity = rarity;
        this.cardTarget = AbstractCard.CardTarget.NONE;
    }

    private static String dualCardIdMaker(String id) {
        return id + "$";
    }

    private static String getImgPath(String baseId) {
        if (ResourceChecker.exist(ModInformation.makeCardImgPath(baseId.split(":")[1])))
            return ModInformation.makeCardImgPath(baseId.split(":")[1]);
        else
            return null;
    }
}