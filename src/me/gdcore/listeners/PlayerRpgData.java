package me.gdcore.listeners;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRpgData {

    private int playerHP;
    private int playerMAXHP;
    private int playerDEF;
    private int playerHPREGEN;
    private boolean playerChanges = false;
    public static HashMap<UUID, PlayerRpgData> datas = new HashMap<>();

    public PlayerRpgData(int HP, int MAXHP, int DEF, int HPREGEN) {
        this.playerHP = HP;
        this.playerMAXHP = MAXHP;
        this.playerDEF = DEF;
        this.playerHPREGEN = HPREGEN;
    }

    public boolean isPlayerChanges() {
        return playerChanges;
    }

    public void setPlayerChanges(boolean playerChanges) {
        this.playerChanges = playerChanges;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
        setPlayerChanges(true);
    }

    public int getPlayerMAXHP() {
        return playerMAXHP;
    }

    public void setPlayerMAXHP(int playerMAXHP) {
        this.playerMAXHP = playerMAXHP;
        setPlayerChanges(true);
    }

    public int getPlayerDEF() {
        return playerDEF;
    }

    public void setPlayerDEF(int playerDEF) {
        this.playerDEF = playerDEF;
        setPlayerChanges(true);
    }

    public int getPlayerHPREGEN() {
        return playerHPREGEN;
    }

    public void setPlayerHPREGEN(int playerHPREGEN) {
        this.playerHPREGEN = playerHPREGEN;
        setPlayerChanges(true);
    }


}
