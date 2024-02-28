package FrierenMod.potions;



import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class DissolveClothPotion extends AbstractPotion {
    public static final String POTION_ID = "DissolveClothPotion";
    private static final PotionStrings potionStrings;

    public DissolveClothPotion() {
        super(potionStrings.NAME, "DissolveClothPotion", PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.WEAK);
     //   ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("FrierenModResources/img/potions/DissolveClothPotion.png"));
        this.isThrown = true;
        this.targetRequired = true;

    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.VULNERABLE.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.VULNERABLE.NAMES[0])));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.FRAIL.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.FRAIL.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new RemoveAllBlockAction(target, AbstractDungeon.player));
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new VulnerablePower(target, this.potency, false), this.potency));
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new FrailPower(target, this.potency, false), this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new DissolveClothPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("DissolveClothPotion");
    }
}
