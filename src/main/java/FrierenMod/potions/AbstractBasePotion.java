package FrierenMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractBasePotion extends AbstractPotion {
    public static final Logger logger = LogManager.getLogger(AbstractBasePotion.class.getName());

    public AbstractBasePotion(String name, String id, AbstractPotion.PotionRarity rarity, AbstractPotion.PotionSize size, AbstractPotion.PotionColor color) {
        super(name, id, rarity, size, color);
        logger.info("new base potion:" + getClass().getName() + " id: " + id);
    }

    public AbstractBasePotion(String name, String id, AbstractPotion.PotionRarity rarity, AbstractPotion.PotionSize size, AbstractPotion.PotionEffect effect, Color liquidColor, Color hybridColor, Color spotsColor) {
        super(name, id, rarity, size, effect, liquidColor, hybridColor, spotsColor);
        logger.info("new base potion:" + getClass().getName() + " id: " + id);
    }

    public void triggerOnTurnStart() {}

    public void triggerOnTurnEnd() {}

    public void triggerOnVictory() {}

    public void triggerOnCombatStart() {}

    public AbstractPotion makeCopy() {
        try {
            return (AbstractPotion)getClass().newInstance();
        } catch (InstantiationException|IllegalAccessException e) {
            throw new RuntimeException("failed to auto-generate makeCopy for potion: " + this.ID);
        }
    }

    private String dedupeKeyword(String keyword) {
        String retVal = (String)GameDictionary.parentWord.get(keyword);
        if (retVal != null)
            return retVal;
        return keyword;
    }

    public void initializeDescription() {
        ArrayList<String> keywords = new ArrayList<>();
        for (String word : this.description.split(" ")) {
            String tmp = dedupeKeyword(word.trim().toLowerCase());
            if (GameDictionary.keywords.containsKey(tmp))
                keywords.add(tmp);
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        for (String keyword : keywords)
            this.tips.add(new PowerTip(TipHelper.capitalize(keyword), (String)GameDictionary.keywords.get(keyword)));
    }
}
