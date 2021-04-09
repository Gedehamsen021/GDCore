package me.gdcore;

import me.gdcore.listeners.PlayerData;
import me.gdcore.listeners.PlayerEvents;
import me.gdcore.listeners.PlayerRpgData;
import me.gdcore.mysql.MySQL;
import me.gdcore.mysql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;
    public MySQL mySQL;
    public SQLGetter sqlData;

    String PluginTag = "§9 GDCORE §f";
    public String pluginDeny = "§c ERRO §f";

    @Override
    public void onEnable() {
        instance = this;
        this.mySQL = new MySQL();
        this.sqlData = new SQLGetter(this);
        registerEvents();
        getServer().getConsoleSender().sendMessage(PluginTag + "Ativado \n Carregando Dependencias");
        try {
            mySQL.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException s) {
            Bukkit.getConsoleSender().sendMessage(PluginTag + "Não foi possivel conectar ao database");
        }
        if(mySQL.isConnected()) {
            Bukkit.getConsoleSender().sendMessage(PluginTag + "Mysql inicializado");
            sqlData.createTable();
            sqlData.createTableRpg();
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                for(UUID p : PlayerData.datas.keySet()) {
                    if(PlayerData.datas.get(p).isPlayerUpdate()) {
                        sqlData.savePlayerData(p);
                        PlayerData.datas.get(p).setPlayerUpdate(false);
                    }
                }
                for(UUID p : PlayerRpgData.datas.keySet()) {
                    if(PlayerRpgData.datas.get(p).isPlayerChanges()) {
                        sqlData.savePlayerRpgData(p);
                        PlayerRpgData.datas.get(p).setPlayerChanges(false);
                    }
                }
            }
        }, 0L, (20*60)*5L);
        sqlData.loadPlayerData();
        sqlData.loadPlayerRpgData();
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(PluginTag + "\n\n§cServidor reiniciando\n\n§aTodo o seu progresso foi salvo");
        }
        for(UUID pdata : PlayerData.datas.keySet()) {
            if(PlayerData.datas.get(pdata).isPlayerUpdate()) {
                sqlData.savePlayerData(pdata);
                PlayerData.datas.get(pdata).setPlayerUpdate(false);
            }
        }
        for(UUID rpgData : PlayerRpgData.datas.keySet()) {
            if(PlayerRpgData.datas.get(rpgData).isPlayerChanges()) {
                sqlData.savePlayerRpgData(rpgData);
                PlayerRpgData.datas.get(rpgData).setPlayerChanges(false);
            }
        }
        mySQL.disconnect();
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    public static Main getInstance() {
        return instance;
    }
}
