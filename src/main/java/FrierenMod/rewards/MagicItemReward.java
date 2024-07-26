package FrierenMod.rewards;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class MagicItemReward extends CustomReward {
    public static RewardItem.RewardType MAGIC_ITEM_REWARD;
    public MagicItemReward(Texture icon, String text, RewardType type) {
        super(icon, text, type);
    }

    @Override
    public boolean claimReward() {
        return false;
    }
}
