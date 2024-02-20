package FrierenMod.patches;

import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import java.util.HashMap;

public class AudioPatch {
    @SpirePatch(clz = SoundMaster.class, method = "<ctor>")
    public static class SoundPatch {
        public static void Postfix(SoundMaster instance, HashMap<String, Sfx> ___map) {
            AudioPatch.load(instance, ___map, "I_am_frieren.mp3");
            AudioPatch.load(instance, ___map, "kiss.mp3");
            AudioPatch.load(instance, ___map, "normal_attack.mp3");
            AudioPatch.load(instance, ___map, "win.mp3");
        }
    }

    private static void load(SoundMaster instance, HashMap<String, Sfx> ___map, String key) {
        ___map.put(key, new Sfx(ModInformation.makeAudioPath("sound/" + key), false));
    }
}