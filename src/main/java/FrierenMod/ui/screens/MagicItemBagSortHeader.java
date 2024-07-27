package FrierenMod.ui.screens;

import FrierenMod.utils.FrierenRes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButtonListener;

import java.util.Comparator;

public class MagicItemBagSortHeader extends CardLibSortHeader {
    private static final int BAR_W = 1334;

    private static final int BAR_H = 102;

    private static final Comparator<AbstractCard> BY_TYPE;

    private static final Comparator<AbstractCard> ALPHA;

    private static final Comparator<AbstractCard> BY_COST;

    private static final Comparator<AbstractCard> PURE_REVERSE;

    private MagicItemBagScreen magicItemBagScreen;

    private float scrollY;

    static {
        BY_TYPE = (Comparator.comparing(a -> (a.type.name() + a.name)));
        ALPHA = (Comparator.comparing(a -> a.name));
        BY_COST = (Comparator.comparing(a -> ("" + a.cost + a.name)));
        PURE_REVERSE = ((a, b) -> a.cardID.equals(b.cardID) ? 0 : -1);
    }

    public MagicItemBagSortHeader(MagicItemBagScreen magicItemBagScreen) {
        super(null);
        this.magicItemBagScreen = magicItemBagScreen;
        this.buttons[0] = new SortHeaderButton(TEXT[5], START_X, 0.0F, (SortHeaderButtonListener)this);
        this.buttons[0].setActive(true);
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
            if (isAscending) {
                this.magicItemBagScreen.setSortOrder(null);
            } else {
                this.magicItemBagScreen.setSortOrder(PURE_REVERSE);
            }
            return;
        }
        if (button == this.buttons[1]) {
            order = BY_TYPE;
        } else if (button == this.buttons[2]) {
            order = BY_COST;
        } else if (button == this.buttons[3]) {
            order = ALPHA;
        } else {
            return;
        }
        if (!isAscending)
            order = order.reversed();
        this.magicItemBagScreen.setSortOrder(order);
    }

    protected void updateScrollPositions() {}

    public void render(SpriteBatch sb) {
        sb.setColor(FrierenRes.RENDER_COLOR.cpy());
        sb.draw(ImageMaster.COLOR_TAB_BAR, Settings.WIDTH / 2.0F - 667.0F, this.scrollY - 51.0F, 667.0F, 51.0F, 1334.0F, 102.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1334, 102, false, false);
        super.render(sb);
    }

    public void updateScrollPosition(float y) {
        this.scrollY = y + 240.0F * Settings.scale;
        for (SortHeaderButton button : this.buttons)
            button.updateScrollPosition(this.scrollY);
    }
}

