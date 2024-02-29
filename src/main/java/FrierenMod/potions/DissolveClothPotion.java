package FrierenMod.potions;

import FrierenMod.utils.ModInformation;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DissolveClothPotion extends AbstractPotion {
    public static final String POTION_ID = ModInformation.makeID(DissolveClothPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;

    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public DissolveClothPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.WEAK);
     //   ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("FrierenModResources/img/potions/DissolveClothPotion.png"));
    }
    public void initializeData() {
        this.isThrown = true;
        this.targetRequired = true;
        this.potency = this.getPotency();
        this.description = String.format(DESCRIPTIONS[0],this.potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.VULNERABLE.NAMES[0]), GameDictionary.keywords.get(GameDictionary.VULNERABLE.NAMES[0])));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT){
            this.addToBot(new RemoveAllBlockAction(target, AbstractDungeon.player));
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new VulnerablePower(target, this.potency, false), this.potency));
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(target, this.potency, false), this.potency));
        }
    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new DissolveClothPotion();
    }
}
