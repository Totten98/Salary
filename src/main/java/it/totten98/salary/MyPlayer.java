package it.totten98.salary;


import org.bukkit.entity.Player;

public class MyPlayer {
    
    private Player player = null;
    private int timer = 0;

    public MyPlayer(Player player, int timer) {
        this.player = player;
        this.timer = timer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    
}
