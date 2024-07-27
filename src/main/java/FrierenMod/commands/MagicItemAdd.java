package FrierenMod.commands;

import FrierenMod.actions.AddMagicItemToDeckAction;
import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import basemod.helpers.ConvertHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;


public class MagicItemAdd extends ConsoleCommand {
    public MagicItemAdd() {
        this.requiresPlayer = true;
        this.minExtraTokens = 1;
        this.maxExtraTokens = 3;
    }

    public void execute(String[] tokens, int depth) {
        int countIndex = MagicItemCommand.countIndex(tokens);
        String cardName = MagicItemCommand.cardName(tokens, countIndex);
        AbstractCard c = CardLibrary.getCard(cardName);
        if (c != null) {
            int count = 1;
            if (tokens.length > countIndex + 1 && ConvertHelper.tryParseInt(tokens[countIndex + 1]) != null) {
                count = ConvertHelper.tryParseInt(tokens[countIndex + 1], 0);
            }

            DevConsole.log("adding " + count + (count == 1 ? " copy of " : " copies of " + cardName));

            for(int i = 0; i < count; ++i) {
                AbstractCard copy = c.makeCopy();
                if(c instanceof AbstractMagicItem)
                    AbstractDungeon.actionManager.addToTop(new AddMagicItemToDeckAction(copy));
                else
                    DevConsole.log(cardName + "is not a magic item");
            }
        } else {
            DevConsole.log("could not find card " + cardName);
        }

    }

    public ArrayList<String> extraOptions(String[] tokens, int depth) {
        ArrayList<String> options = ConsoleCommand.getCardOptions();
        if (options.contains(tokens[depth])) {
            if (tokens.length > depth + 1 && tokens[depth + 1].matches("\\d*")) {
                if (tokens.length > depth + 2) {
                    if (tokens[depth + 2].matches("\\d+")) {
                        ConsoleCommand.complete = true;
                    } else if (!tokens[depth + 2].isEmpty()) {
                        tooManyTokensError();
                    }
                }

                return ConsoleCommand.smallNumbers();
            }

        }
        if (tokens.length > depth + 1) {
            tooManyTokensError();
        }

        return options;
    }

    public void errorMsg() {
        MagicItemCommand.cmdMagicItemHelp();
    }
}

