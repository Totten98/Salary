package it.totten98.salary;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnPlayerEvent implements Listener
{
    //private Stipendio m = null;
    
    Stipendio s = Stipendio.getInstance();
    
    public OnPlayerEvent(Stipendio s)
    {
        this.s = s;
        s.getServer().getPluginManager().registerEvents(this, s);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        
        this.s.savePlayer(p);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
       Player p = event.getPlayer(); 
        
       this.s.removePlayer(p);
    }    
}
