package me.gdcore.mysql;

import me.gdcore.Main;
import me.gdcore.listeners.PlayerData;
import me.gdcore.listeners.PlayerRpgData;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Main plugin;

    public SQLGetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement pst;
        try {
            pst = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players " +
                    "(NAME VARCHAR(100),UUID VARCHAR(100),XP INT(100), LEVEL INT(20), BOOSTERS INT(100), MONEY INT(100), TIMER INT(30), PRIMARY KEY (UUID))");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableRpg() {
        PreparedStatement pst;
        try {
            pst = plugin.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS rpgplayers" +
                    " (NAME VARCHAR(100),UUID VARCHAR(100),HP INT(100), MAXHP INT(100), DEF INT(100), HPREGEN INT(100), FOREIGN KEY(UUID) REFERENCES players(UUID))");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player p) {
        try {
            UUID uuid = p.getUniqueId();
            if (!existPlayer(uuid)) {
                PreparedStatement pst2 = plugin.mySQL.getConnection().prepareStatement("INSERT INTO players" +
                        " (NAME,UUID,XP,LEVEL,BOOSTERS,MONEY,TIMER) VALUES (?,?,0,1,1,1000,0)");
                pst2.setString(1, p.getName());
                pst2.setString(2, uuid.toString());
                pst2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRpgPlayer(Player p) {
        try {
            UUID uuid = p.getUniqueId();
            if (!existPlayerRpg(uuid)) {
                PreparedStatement pst2 = plugin.mySQL.getConnection().prepareStatement("INSERT INTO rpgplayers" +
                        " (NAME,UUID,HP,MAXHP,DEF,HPREGEN) VALUES (?,?,100,100,1,0)");
                pst2.setString(1, p.getName());
                pst2.setString(2, uuid.toString());
                pst2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existPlayer(UUID uuid) {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            pst.setString(1, uuid.toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean existPlayerRpg(UUID uuid) {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("SELECT * FROM rpgplayers WHERE UUID=?");
            pst.setString(1, uuid.toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadPlayerData() {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("SELECT * FROM players");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("UUID");
                int xp = rs.getInt("XP");
                int level = rs.getInt("LEVEL");
                int money = rs.getInt("MONEY");
                int boosters = rs.getInt("BOOSTERS");
                int timer = rs.getInt("TIMER");
                PlayerData.datas.put(UUID.fromString(uuid), new PlayerData(xp, level, money, boosters, timer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerData(UUID uuid) {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("UPDATE players SET XP=? ,LEVEL=? ,MONEY=? ,BOOSTERS=? ,TIMER=? WHERE UUID=?");
            pst.setInt(1, PlayerData.datas.get(uuid).getPlayerXp());
            pst.setInt(2, PlayerData.datas.get(uuid).getPlayerLevel());
            pst.setInt(3, PlayerData.datas.get(uuid).getPlayerMoney());
            pst.setInt(4, PlayerData.datas.get(uuid).getPlayerBoosters());
            pst.setInt(5, PlayerData.datas.get(uuid).getPlayerTimer());
            pst.setString(6, uuid.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerRpgData(UUID uuid) {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("UPDATE rpgplayers SET HP=? ,MAXHP=? ,DEF=? ,HPREGEN=? WHERE UUID=?");
            pst.setInt(1, PlayerRpgData.datas.get(uuid).getPlayerHP());
            pst.setInt(2, PlayerRpgData.datas.get(uuid).getPlayerMAXHP());
            pst.setInt(3, PlayerRpgData.datas.get(uuid).getPlayerDEF());
            pst.setInt(4, PlayerRpgData.datas.get(uuid).getPlayerHPREGEN());
            pst.setString(5, uuid.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadPlayerRpgData() {
        try {
            PreparedStatement pst = plugin.mySQL.getConnection().prepareStatement("SELECT * FROM rpgplayers");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                String uuid = rs.getString("UUID");
                int hp = rs.getInt("HP");
                int maxHP = rs.getInt("MAXHP");
                int def = rs.getInt("DEF");
                int hpRegen = rs.getInt("HPREGEN");
                PlayerRpgData.datas.put(UUID.fromString(uuid),new PlayerRpgData(hp,maxHP,def,hpRegen));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
