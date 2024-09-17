package FrierenMod.utils;

import javax.swing.*;

import static com.codedisaster.steamworks.SteamAPI.isSteamRunning;

public class AntiPirateHelper {
    public static void antiPirate() throws InterruptedException {
        if(!isSteamRunning() && !Config.IN_DEV){
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(false);
            JOptionPane.showConfirmDialog(frame,"请支持正版杀戮尖塔","不要再玩这byd整合包了", JOptionPane.YES_NO_OPTION);
            frame.dispose();
        }
    }
}
