package FrierenMod.gameHelpers;

import FrierenMod.powers.BanMagicGainPower;
import FrierenMod.powers.ChantWithoutMagicPower;
import FrierenMod.powers.MagicInsteadOfCostPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HardCodedPowerHelper {
    public static final String CHANT_WITHOUT_MAGIC = ChantWithoutMagicPower.POWER_ID;
    public static final String BAN_MAGIC_GAIN = BanMagicGainPower.POWER_ID;
    public static final String MAGIC_INSTEAD_OF_COST = MagicInsteadOfCostPower.POWER_ID;
    private static final AbstractPlayer p = AbstractDungeon.player;
    public static boolean hasChantWithoutMagicPower(){
        return p.hasPower(CHANT_WITHOUT_MAGIC);
    }
    public static boolean dontHaveBanMagicGainPower(){
        return !p.hasPower(BAN_MAGIC_GAIN);
    }
    public static boolean hasMagicInsteadOfCostPower(){
        return p.hasPower(MAGIC_INSTEAD_OF_COST);
    }
}
