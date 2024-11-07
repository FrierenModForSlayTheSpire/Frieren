package FrierenMod.utils;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamApps;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;

import javax.swing.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AntiPirateHelper {
    private static final String SHA256 = "9800bab98901a29d8d04d0c853f2d4fdc9d3881ff5d67781949c90fe97ea7d27";

    public static void antiPirate() throws InterruptedException {
        SteamApps App = new SteamApps();
        if (SteamAPI.isSteamRunning(true) && App.isSubscribedApp(646570))
            return;
        SpireConfig Notion;
        try {
            Notion = new SpireConfig(ModInformation.MOD_NAME, "notional");
        } catch (IOException exception) {
            Log.logger.error("Exception caught: " + exception.getMessage() + ".");
            throw new RuntimeException(exception);
        }
        if (Notion.has("LocalDebugVerification")) {
            if (verifySHA256(Notion.getString("LocalDebugVerification"), SHA256))
                return;
        }
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        JOptionPane.showConfirmDialog(frame, "请支持正版杀戮尖塔", "不要再玩这byd整合包了", JOptionPane.YES_NO_OPTION);
        frame.dispose();
    }

    private static String generateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean verifySHA256(String input, String expectedHash) {
        String inputHash = generateSHA256(input);
        return inputHash.equalsIgnoreCase(expectedHash);
    }
}
