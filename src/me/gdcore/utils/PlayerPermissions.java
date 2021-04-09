package me.gdcore.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerPermissions {
    int DONO = 100;
    int ADMIN = 80;
    int MOD = 60;
    int HELPER = 40;
    int VIP = 20;
    int MEMBER = 0;

    public void SetupPermissions(Player p) {
        File f = new File("./plugins/GDCore/" + p.getUniqueId() + ".yml");
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.options().header("Use 100 para DONO| 80 para ADMIN| 60 para MOD| 40 para HELPER| 20 para VIP| 0 para MEMBER");
        yml.addDefault("User", p.getName());
        yml.addDefault("UUID", p.getUniqueId().toString());
        yml.addDefault("PERMS", MEMBER);
        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPerms(Player p) {
        File f = new File("./plugins/GDCore/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("PERMS");
    }

    public boolean setPerms(Player p, int permsLevel) {
        File f = new File("./plugins/GDCore/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("PERMS",permsLevel);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getPermsColors(int perms) {
        if(perms == DONO) {
            return " §7DONO §f";
        } else if (perms == ADMIN) {
            return " §cADMIN §f";
        } else if (perms == MOD) {
            return " §dMOD §f";
        } else if (perms == HELPER) {
            return " §bHELPER §f";
        } else if (perms == VIP) {
            return " §eVIP §f";
        } else {
            return "";
        }
    }
}
