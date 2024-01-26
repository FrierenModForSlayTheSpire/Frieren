package FrierenMod.modcore;


import FrierenMod.Characters.Frieren;
import FrierenMod.cards.optionCards.ChantFromDiscardPile;
import FrierenMod.cards.optionCards.ChantFromDrawPile;
import FrierenMod.cards.optionCards.ChantFromHand;
import FrierenMod.cards.tempCards.Flower;
import FrierenMod.cards.tempCards.HideMagic;
import FrierenMod.cards.tempCards.LanSan;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.cards.white.*;
import FrierenMod.cards.white.FangYuMoFa;
import FrierenMod.cards.white.HolyChant;
import FrierenMod.relics.MyRelic;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.Color;

import java.nio.charset.StandardCharsets;

import static FrierenMod.Characters.Frieren.Enums.*;
import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class FrierenMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {
    private static final String FRIEREN_BUTTON = "FrierenModResources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String FRIEREN_PORTRAIT = "FrierenModResources/img/char/Character_Portrait_2.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "FrierenModResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "FrierenModResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "FrierenModResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "FrierenModResources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "FrierenModResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "FrierenModResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "FrierenModResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "FrierenModResources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "FrierenModResources/img/char/cost_orb.png";
    public static final Color FRIEREN_WHITE = new Color(237.0F / 255.0F, 234.0F / 255.0F, 245.0F / 255.0F, 1.0F);
    public FrierenMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(FRIEREN_CARD, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new FrierenMod();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new MagicPower());
        BaseMod.addCard(new SaveMagic());
        BaseMod.addCard(new HideMagic());
        BaseMod.addCard(new ChantFromDrawPile());
        BaseMod.addCard(new ChantFromHand());
        BaseMod.addCard(new ChantFromDiscardPile());
        BaseMod.addCard(new FangYuMoFa());
        BaseMod.addCard(new HolyChant());
        BaseMod.addCard(new TransferMagic());
        BaseMod.addCard(new YongXu());
        BaseMod.addCard(new NiLiu());
        BaseMod.addCard(new MeiJunMoFa());
        BaseMod.addCard(new FenSan());
        BaseMod.addCard(new LiuZhuan());
        BaseMod.addCard(new PuGongMoFa());
        BaseMod.addCard(new FeiXingMoFa());
        BaseMod.addCard(new MoDaoShu());
        BaseMod.addCard(new QingXie());
        BaseMod.addCard(new LianHuanYongChang());
        BaseMod.addCard(new HeiAnYongChang());
        BaseMod.addCard(new ShuiYiXingTai());
        BaseMod.addCard(new ZhunBei());
        BaseMod.addCard(new QiPianMoZuDeXiaoBaXi());
        BaseMod.addCard(new HuaTianMoFa());
        BaseMod.addCard(new Flower());
        BaseMod.addCard(new ShiPingMoFa());
        BaseMod.addCard(new YouZiMoFa());
        BaseMod.addCard(new ShiJueMoFa());
        BaseMod.addCard(new BaoBingMoFa());
        BaseMod.addCard(new RaoKouLingMoFa());
        BaseMod.addCard(new DuoChongMoFa());
        BaseMod.addCard(new YiShiZhunBei());
        BaseMod.addCard(new LanSan());
    }
    @Override
    public void receiveEditRelics() {
         BaseMod.addRelic(new MyRelic(), RelicType.SHARED);
    }
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Frieren(CardCrawlGame.playerName), FRIEREN_BUTTON, FRIEREN_PORTRAIT, FRIEREN);
    }
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        String json = Gdx.files.internal("FrierenModResources/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("frierenmod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "FrierenModResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "FrierenModResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "FrierenModResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "FrierenModResources/localization/" + lang + "/powers.json");
    }
}