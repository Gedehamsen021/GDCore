package me.gdcore.listeners;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData {

    private int playerXp;
    private int playerLevel;
    private int playerMoney;
    private int playerBoosters;
    private int playerTimer;
    private boolean playerUpdate;
    public static HashMap<UUID, PlayerData> datas = new HashMap<>();

    public boolean isPlayerUpdate() {
        return playerUpdate;
    }

    public void setPlayerUpdate(boolean playerUpdate) {
        this.playerUpdate = playerUpdate;
    }

    public PlayerData(int xp, int level, int money, int boosters, int timer) {
        this.playerXp = xp;
        this.playerLevel = level;
        this.playerMoney = money;
        this.playerBoosters = boosters;
        this.playerTimer = timer;
        this.playerUpdate = false;
    }

    public int getPlayerXp() {
        return playerXp;
    }

    public void setPlayerXp( int playerxp) {
        setPlayerUpdate(true);
        this.playerXp = playerxp;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        setPlayerUpdate(true);
        this.playerLevel = playerLevel;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        setPlayerUpdate(true);
        this.playerMoney = playerMoney;
    }

    public int getPlayerBoosters() {
        return playerBoosters;
    }

    public void setPlayerBoosters(int playerBoosters) {
        setPlayerUpdate(true);
        this.playerBoosters = playerBoosters;
    }

    public int getPlayerTimer() {
        return playerTimer;
    }

    public void setPlayerTimer(int playerTimer) {
        setPlayerUpdate(true);
        this.playerTimer = playerTimer;
    }
}
