package FrierenMod.ui.screens;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;

import java.util.Comparator;

public class MagicDeckSortHeader extends CardLibSortHeader {
    private static final Comparator<AbstractCard> BY_LOAD;

    private static final Comparator<AbstractCard> BY_TYPE;
    private static final Comparator<AbstractCard> BY_RARITY;

    private final MagicDeckScreen magicDeckScreen;

    private float scrollY;
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(MagicDeckSortHeader.class.getSimpleName())).TEXT;

    static {
        BY_LOAD = (Comparator.comparing((a) -> {
            if (a instanceof AbstractMagicItem) {
                if (((AbstractMagicItem) a).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP) {
                    return 4;
                } else {
                    if (((AbstractMagicItem) a).currentSlot == -1)
                        return 3;
                    else
                        return ((AbstractMagicItem) a).currentSlot;
                }
            } else
                return 5;
        }));
        BY_TYPE = (Comparator.comparing(a -> {
            if (a instanceof AbstractMagicItem) {
                if (((AbstractMagicItem) a).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP) {
                    return -1;
                } else {
                    if (((AbstractMagicItem) a).currentSlot == -1)
                        return 3;
                    else
                        return ((AbstractMagicItem) a).currentSlot;
                }
            } else
                return 4;
        }));
        BY_RARITY = (Comparator.comparing(a -> {
            if (a instanceof AbstractMagicItem) {
                switch (((AbstractMagicItem) a).magicItemRarity) {
                    case BASIC:
                        return 1;
                    case COMMON:
                        return 2;
                    case UNCOMMON:
                        return 3;
                    case PROP:
                        return 4;
                    case RARE:
                        return 5;
                }
            }
            return 6;
        }));
    }

    public MagicDeckSortHeader(MagicDeckScreen magicDeckScreen) {
        super(null);
        this.magicDeckScreen = magicDeckScreen;
        float xPosition = START_X;
        SortHeaderButton loadButton = new SortHeaderButton(TEXT[0], xPosition, 0.0F, this);
        xPosition += SPACE_X;
        SortHeaderButton rarityButton = new SortHeaderButton(TEXT[1], xPosition, 0.0F, this);
        xPosition += SPACE_X;
        SortHeaderButton typeButton = new SortHeaderButton(TEXT[2], xPosition, 0.0F, this);
        this.buttons = new SortHeaderButton[]{loadButton, rarityButton, typeButton};
        this.magicDeckScreen.setSortOrder(BY_LOAD);
        float HB_W = (this.buttons[0]).hb.width;
        float leftSideOffset = Settings.WIDTH / 2.0F - HB_W * this.buttons.length / 2.0F;
        for (int i = 0; i < this.buttons.length; i++) {
            SortHeaderButton button = this.buttons[i];
            button.hb.move(leftSideOffset + HB_W * i + HB_W / 2.0F, button.hb.cY);
        }
    }

    public void didChangeOrder(SortHeaderButton button, boolean isAscending) {
        Comparator<AbstractCard> order;
        button.setActive(true);
        if (button == this.buttons[0]) {
            order = BY_LOAD;
        } else if (button == this.buttons[1]) {
            order = BY_RARITY;
        } else if (button == this.buttons[2]) {
            order = BY_TYPE;
        } else {
            return;
        }
        if (!isAscending)
            order = order.reversed();
        this.magicDeckScreen.setSortOrder(order);
    }

    protected void updateScrollPositions() {
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(ImageMaster.COLOR_TAB_BAR, Settings.WIDTH / 2.0F - 667.0F, this.scrollY - 51.0F, 667.0F, 51.0F, 1334.0F, 102.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1334, 102, false, false);
        super.render(sb);
    }

    public void updateScrollPosition(float y) {
        this.scrollY = y + 240.0F * Settings.scale;
        for (SortHeaderButton button : this.buttons)
            button.updateScrollPosition(this.scrollY);
    }
}

