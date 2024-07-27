//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package FrierenMod.commands;

import basemod.BaseMod;
import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import basemod.helpers.ConvertHelper;

import java.util.Arrays;

public class MagicItemCommand extends ConsoleCommand {
    public MagicItemCommand() {
        this.followup.put("add", MagicItemAdd.class);
        this.requiresPlayer = true;
        this.simpleCheck = true;
    }

    public void execute(String[] tokens, int depth) {
        cmdMagicItemHelp();
    }

    public void errorMsg() {
        cmdMagicItemHelp();
    }

    public static int countIndex(String[] tokens) {
        int countIndex;
        for (countIndex = tokens.length - 1; ConvertHelper.tryParseInt(tokens[countIndex]) != null; --countIndex) {
        }

        return countIndex;
    }

    public static String cardName(String[] tokens) {
        return cardName(tokens, countIndex(tokens));
    }

    public static String cardName(String[] tokens, int countIndex) {
        String[] cardNameArray = Arrays.copyOfRange(tokens, 2, countIndex + 1);
        String cardName = String.join(" ", cardNameArray);
        if (BaseMod.underScoreCardIDs.containsKey(cardName)) {
            cardName = BaseMod.underScoreCardIDs.get(cardName);
        }

        return cardName;
    }

    public static void cmdMagicItemHelp() {
        DevConsole.couldNotParse();
        DevConsole.log("options are:");
        DevConsole.log("* add [id] {count} ");
    }

    public static void register() {
        ConsoleCommand.addCommand("magicitem", MagicItemCommand.class);
    }
}
