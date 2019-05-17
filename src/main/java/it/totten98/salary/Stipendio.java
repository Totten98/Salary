package it.totten98.salary;


import java.util.ArrayList;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

// salary.default  5
// salary.membro 10
// salary.vip 15

public class Stipendio extends JavaPlugin
{
    @Getter private static Stipendio instance;
    
    private ArrayList<MyPlayer> myPlayers = new ArrayList<>();
    
    private Economy economy = null;
    
    private RegisteredServiceProvider<Economy> economyProvider;

    @Override
    public void onEnable()
    {
        instance = this;
        
        economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        
        economy = economyProvider.getProvider();
        
        new OnPlayerEvent(instance);
        
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(MyPlayer myPlayer : myPlayers) {
                    
                    int money = 0;
                    
                    if(myPlayer.getTimer() == 900) {
                        
                        if(myPlayer.getPlayer().hasPermission("salary.vip"))
                            money = 15;
                        
                        else if(myPlayer.getPlayer().hasPermission("salary.membro"))
                            money = 10;
                        
                        else if(myPlayer.getPlayer().hasPermission("salary.default"))
                            money = 5;
      
                        economy.depositPlayer(myPlayer.getPlayer(), money);
                        
                        myPlayer.getPlayer().sendMessage(ChatColor.GOLD + "Hai ricevuto lo stipendio per essere stato online: " + ChatColor.GREEN + money +"$");
                        
                        myPlayer.setTimer(0);
                        
                    }
                    myPlayer.setTimer(myPlayer.getTimer()+1);
                }
            }
        }, 0, 20);// 60 L == 3 sec, 20 ticks == 1 sec
    }
    
    @Override
    public void onDisable()
    {
        
    }
    
    public void savePlayer(Player pl)
    {
        myPlayers.add(new MyPlayer(pl, 0));
    }
    
    public void removePlayer(Player pl)
    {
        for(MyPlayer myPlayer : myPlayers) {
            
            if(myPlayer.getPlayer().equals(pl)) {
                
                myPlayers.remove(myPlayer);
                break;
            }
        }
    }
}
