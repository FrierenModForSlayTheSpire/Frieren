package FrierenMod.utils;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;

public class FrierenRes {
    public static final String CHARACTER_NAME = "Frieren";
    public static final String BG_ATTACK_512 = ModInformation.makeCardBgPath(CHARACTER_NAME,"attack",512);
    public static final String BG_SKILL_512 = ModInformation.makeCardBgPath(CHARACTER_NAME,"skill",512);
    public static final String BG_POWER_512 = ModInformation.makeCardBgPath(CHARACTER_NAME,"power",512);
    public static final String BG_MAGIC_512 = ModInformation.makeCardBgPath(CHARACTER_NAME,"magic",512);
    public static final String BG_ATTACK_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME,"attack",1024);
    public static final String BG_SKILL_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME,"skill",1024);
    public static final String BG_POWER_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME,"power",1024);
    public static final String BG_MAGIC_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME,"magic",1024);
    public static final String ENERGY_ORB = ModInformation.makeCardOrbPath(CHARACTER_NAME,"cost");
    public static final String BIG_ORB = ModInformation.makeCardOrbPath(CHARACTER_NAME,"card");
    public static final String SMALL_ORB = ModInformation.makeCardOrbPath(CHARACTER_NAME,"small");
    public static final Color RENDER_COLOR = CardHelper.getColor(237.0F, 234.0F, 245.0F);
    public static final String CHARACTER_BUTTON = ModInformation.makeCharResPath(CHARACTER_NAME,"Character_Button");
    public static final String CHARACTER_PORTRAIT = ModInformation.makeCharResPath(CHARACTER_NAME,"Character_Portrait");
    public static final String SHOULDER_1 = ModInformation.makeCharResPath(CHARACTER_NAME,"shoulder");
    public static final String SHOULDER_2 = ModInformation.makeCharResPath(CHARACTER_NAME,"shoulder2");
    public static final String CORPSE_IMAGE = ModInformation.makeCharResPath(CHARACTER_NAME,"corpse_character");
    public static final String CHARACTER_IMG = ModInformation.makeCharResPath(CHARACTER_NAME,"character");
    public static final String VICTORY_1 = ModInformation.makeCharResPath(CHARACTER_NAME,"Victory1");
    public static final String VICTORY_2 = ModInformation.makeCharResPath(CHARACTER_NAME,"Victory2");
    public static final String VICTORY_3 = ModInformation.makeCharResPath(CHARACTER_NAME,"Victory3");
    public static final String[] ORB_TEXTURES = ModInformation.makeOrbTexturePath(CHARACTER_NAME);
    public static final String ORB_VFX = ModInformation.makeOrbVfxPath(CHARACTER_NAME);
    public static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
}
