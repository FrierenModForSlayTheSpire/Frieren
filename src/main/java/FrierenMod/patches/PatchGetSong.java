package FrierenMod.patches;

import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;

import java.util.Objects;

@SpirePatch(clz = TempMusic.class, method = "getSong")
public class PatchGetSong {
    @SpireInsertPatch(rloc = 0, localvars = {"key"})
    public static SpireReturn<Music> Insert(TempMusic __instance, String key) {
        if (Objects.equals(key, "Frieren_The_Slayer")) {
            return SpireReturn.Return(MainMusic.newMusic(ModInformation.makeAudioPath("sound/Frieren_The_Slayer.mp3")));
        } else
            return SpireReturn.Continue();
    }
}
