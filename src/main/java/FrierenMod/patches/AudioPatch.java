package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;
import java.util.Objects;

import static com.megacrit.cardcrawl.audio.MainMusic.newMusic;

public class AudioPatch {
    @SpirePatch(clz = SoundMaster.class, method = "<ctor>")
    public static class SoundPatch {
        public static void Postfix(SoundMaster instance, HashMap<String, Sfx> ___map) {
            AudioPatch.load(instance, ___map, "I_am_frieren.mp3");
            AudioPatch.load(instance, ___map, "kiss.mp3");
            AudioPatch.load(instance, ___map, "normal_attack.mp3");
            AudioPatch.load(instance, ___map, "win.mp3");
            AudioPatch.load(instance, ___map, "Himmel.mp3");
        }
    }
    @SpirePatch(clz = TempMusic.class, method = "getSong")
    public static class PatchGetSongInTempMusic {
        @SpireInsertPatch(rloc = 0, localvars = {"key"})
        public static SpireReturn<Music> Insert(TempMusic __instance, String key) {
            if (Objects.equals(key, "Frieren_The_Slayer")) {
                return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/Frieren_The_Slayer.mp3")));
            }
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN){
                switch (key) {
                    case "SHOP":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Merchant_NewMix_v1.ogg")));
                    case "SHRINE":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Shrine_NewMix_v1.ogg")));
                    case "MINDBLOOM":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Boss1MindBloom_v1.ogg")));
                    case "BOSS_BOTTOM":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Boss1_NewMix_v1.ogg")));
                    case "BOSS_CITY":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Boss2_NewMix_v1.ogg")));
                    case "BOSS_BEYOND":
                        //未替换
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Boss3_NewMix_v1.ogg")));
                    case "BOSS_ENDING":
                        //未替换
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Boss4_v6.ogg")));
                    case "ELITE":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_EliteBoss_NewMix_v1.ogg")));
                    case "CREDITS":
                        // 未替换
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Credits_v5.ogg")));
                }
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(clz = MainMusic.class, method = "getSong")
    public static class PatchGetSongInMainMusic{
        @SpireInsertPatch(rloc = 0, localvars = {"key"})
        public static SpireReturn<Music> Insert(MainMusic __instance, String key) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN){
                switch (key) {
                    case "Exordium":
                        if (AbstractDungeon.miscRng.random(1) == 0) {
                            return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level1_NewMix_v1.ogg")));
                        }
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level1-2_v2.ogg")));
                    case "TheCity":
                        if (AbstractDungeon.miscRng.random(1) == 0) {
                            return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level2_NewMix_v1.ogg")));
                        }
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level2-2_v2.ogg")));
                    case "TheBeyond":
                        if (AbstractDungeon.miscRng.random(1) == 0) {
                            return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level3_v2.ogg")));
                        }
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level3-2_v2.ogg")));
                    case "TheEnding":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Act4_BGM_v2.ogg")));
                    case "MENU":
                        return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_MenuTheme_NewMix_v1.ogg")));
                }
                Log.logger.info("NO SUCH MAIN BGM (playing level_1 instead): " + key);
                return SpireReturn.Return(newMusic(ModInformation.makeAudioPath("sound/STS_Level1_NewMix_v1.ogg")));
            }
            return SpireReturn.Continue();
        }
    }

    private static void load(SoundMaster instance, HashMap<String, Sfx> ___map, String key) {
        ___map.put(key, new Sfx(ModInformation.makeAudioPath("sound/" + key), false));
    }
}