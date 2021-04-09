package me.gdcore.listeners;

import me.gdcore.Main;
import me.gdcore.utils.PlayerPermissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;


public class PlayerEvents implements Listener {

    PlayerPermissions perms = new PlayerPermissions();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Main.getInstance().sqlData.createPlayer(e.getPlayer()); // Cria o usuario no mysql
        Main.getInstance().sqlData.createRpgPlayer(e.getPlayer()); // Cria o usuario rpg no mysql
        if (!PlayerData.datas.containsKey(e.getPlayer().getUniqueId())) {
            PlayerData.datas.put(e.getPlayer().getUniqueId(), new PlayerData(0, 1, 1000, 1, 0));
        }
        if (!PlayerRpgData.datas.containsKey(e.getPlayer().getUniqueId())) {
            PlayerRpgData.datas.put(e.getPlayer().getUniqueId(), new PlayerRpgData(100, 100, 0, 1));
        }
        perms.SetupPermissions(e.getPlayer());
    }

    @EventHandler
    public void disableHunger(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        p.setFoodLevel(20);
        e.setCancelled(true);
    }
}
