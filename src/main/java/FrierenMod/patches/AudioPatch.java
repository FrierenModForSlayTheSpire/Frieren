package FrierenMod.patches;

import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.audio.TempMusic;

import java.util.HashMap;
import java.util.Objects;

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
    public static class PatchGetSong {
        @SpireInsertPatch(rloc = 0, localvars = {"key"})
        public static SpireReturn<Music> Insert(TempMusic __instance, String key) {
            if (Objects.equals(key, "Frieren_The_Slayer")) {
                return SpireReturn.Return(MainMusic.newMusic(ModInformation.makeAudioPath("sound/Frieren_The_Slayer.mp3")));
            } else
                return SpireReturn.Continue();
        }
    }

    private static void load(SoundMaster instance, HashMap<String, Sfx> ___map, String key) {
        ___map.put(key, new Sfx(ModInformation.makeAudioPath("sound/" + key), false));
    }
}