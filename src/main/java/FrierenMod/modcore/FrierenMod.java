package FrierenMod.modcore;


import FrierenMod.cards.optionCards.zeerie.layer2.*;
import FrierenMod.cards.optionCards.zeerie.layer3.*;
import FrierenMod.Characters.Frieren;
import FrierenMod.cards.optionCards.ChantFromDiscardPile;
import FrierenMod.cards.optionCards.ChantFromDrawPile;
import FrierenMod.cards.optionCards.ChantFromHand;
import FrierenMod.cards.optionCards.zeerie.layer1.Cost0;
import FrierenMod.cards.optionCards.zeerie.layer1.Cost1;
import FrierenMod.cards.optionCards.zeerie.layer1.Cost2;
import FrierenMod.cards.optionCards.zeerie.layer1.Cost3;
import FrierenMod.cards.optionCards.zeerie.layer4.*;
import FrierenMod.cards.tempCards.*;
import FrierenMod.cards.white.*;
import FrierenMod.cards.white.chant.*;
import FrierenMod.cards.white.FlightMagic;
import FrierenMod.cards.white.JewelryMagic;
import FrierenMod.cards.white.OilMagic;
import FrierenMod.relics.MyRelic;
import FrierenMod.variables.ChantXVariable;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN;
import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
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
    private static final String ENERGY_ORB = "FrierenModResources/img/char/cost_orb.png";
    public static final Color FRIEREN_WHITE = new Color(237.0F / 255.0F, 234.0F / 255.0F, 245.0F / 255.0F, 1.0F);
    public FrierenMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(FRIEREN_CARD, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE, FRIEREN_WHITE,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512, ENERGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new FrierenMod();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new ChantXVariable());
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new MagicPower());
        BaseMod.addCard(new IceCreamMagic());
        BaseMod.addCard(new HideMagic());
        BaseMod.addCard(new ChantFromDrawPile());
        BaseMod.addCard(new ChantFromHand());
        BaseMod.addCard(new ChantFromDiscardPile());
        BaseMod.addCard(new DefendMagic());
        BaseMod.addCard(new HolyChant());
        BaseMod.addCard(new MarshMagic());
        BaseMod.addCard(new Sustainability());
        BaseMod.addCard(new ReverseFlow());
        BaseMod.addCard(new MoldMagic());
        BaseMod.addCard(new Disperse());
        BaseMod.addCard(new Flow());
        BaseMod.addCard(new NormalAttackMagic());
        BaseMod.addCard(new FlightMagic());
        BaseMod.addCard(new MagicBook());
        BaseMod.addCard(new Outpouring());
        BaseMod.addCard(new ContinualChant());
        BaseMod.addCard(new DarkChant());
        BaseMod.addCard(new NightDressForm());
        BaseMod.addCard(new PreparePosture());
        BaseMod.addCard(new Trick());
        BaseMod.addCard(new FlowerFieldMagic());
        BaseMod.addCard(new Flower());
        BaseMod.addCard(new JewelryMagic());
        BaseMod.addCard(new OilMagic());
        BaseMod.addCard(new VisionMagic());
        BaseMod.addCard(new PancakeMagic());
        BaseMod.addCard(new TwisterMagic());
        BaseMod.addCard(new MultipleAttackMagic());
        BaseMod.addCard(new PrepareRite());
        BaseMod.addCard(new Laziness());
        BaseMod.addCard(new BoomMagic());
        BaseMod.addCard(new Recall());
        BaseMod.addCard(new BabySleeping());
        BaseMod.addCard(new LittleFire());
        BaseMod.addCard(new FullAhead());
        BaseMod.addCard(new StatueMagic());
        BaseMod.addCard(new RedAppleMagic());
        BaseMod.addCard(new LockTarget());
        BaseMod.addCard(new FiveMinutesMore());
        BaseMod.addCard(new ZeerieMagicBook());
        BaseMod.addCard(new CustomLegendMagic());
        BaseMod.addCard(new Cost0((AbstractCard) null));
        BaseMod.addCard(new Cost1((AbstractCard) null));
        BaseMod.addCard(new Cost2((AbstractCard) null));
        BaseMod.addCard(new Cost3((AbstractCard) null));
        BaseMod.addCard(new DrawOption((AbstractCard) null, 0));
        BaseMod.addCard(new GainRandomCardOption((AbstractCard) null, 0));
        BaseMod.addCard(new MagicPowerOption((AbstractCard) null,0));
        BaseMod.addCard(new SelfRetainOption((AbstractCard) null,0));
        BaseMod.addCard(new CostZeroOption((AbstractCard) null,0));
        BaseMod.addCard(new MagicPowerInHandOption((AbstractCard) null, 0));
        BaseMod.addCard(new ScryOption((AbstractCard) null,0));
        BaseMod.addCard(new BlockOption((AbstractCard) null,0));
        BaseMod.addCard(new DamageAllOption((AbstractCard) null,0));
        BaseMod.addCard(new DamageOption((AbstractCard) null,0));
        BaseMod.addCard(new DamageThirdOption((AbstractCard) null, 0));
        BaseMod.addCard(new EnergyOption((AbstractCard) null, 0));
        BaseMod.addCard(new ExtinguishOption((AbstractCard) null,0));
        BaseMod.addCard(new ChantOption((AbstractCard) null,0));
        BaseMod.addCard(new DexterityOption((AbstractCard) null,0));
        BaseMod.addCard(new IntangibleOption((AbstractCard) null,0));
        BaseMod.addCard(new RemoveDebuffOption((AbstractCard) null));
        BaseMod.addCard(new StrengthOption((AbstractCard) null));
        BaseMod.addCard(new UpgradeOption((AbstractCard) null));
        BaseMod.addCard(new UpgradeAllOption((AbstractCard) null));
        BaseMod.addCard(new VulnerableOption((AbstractCard) null,0));
        BaseMod.addCard(new TimeTravel());
        BaseMod.addCard(new ContinuousShooting());
        BaseMod.addCard(new CompleteDefendMagic());
        BaseMod.addCard(new MagicianFinisher());
        BaseMod.addCard(new Kiss());
        BaseMod.addCard(new BindingMagic());
        BaseMod.addCard(new SeeThroughPhantom());
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
        BaseMod.loadCustomStringsFile(UIStrings.class,"FrierenModResources/localization/" + lang + "/UIs.json");
    }
}