package me.gdcore.utils;

import me.gdcore.Main;
import org.bukkit.command.CommandSender;

public class Utils {
    public static boolean isInt(String s, CommandSender sender) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            sender.sendMessage(Main.getInstance().pluginDeny + "Use numeros no Level de permissão /perms <usuario> <Level de permissão>");
            return false;
        }
        return true;
    }
}
