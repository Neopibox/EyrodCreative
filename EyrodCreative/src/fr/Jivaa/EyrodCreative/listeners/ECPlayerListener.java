package fr.Jivaa.EyrodCreative.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import fr.Jivaa.EyrodCreative.EyrodCreative;

public class ECPlayerListener implements Listener
{
	private EyrodCreative plugin;
	
	public ECPlayerListener(EyrodCreative plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		Player p = event.getPlayer();
		
		if(!plugin.getConfig().getBoolean("config.allowDropItems"))
		{
			event.setCancelled(true);
			
			if(plugin.getConfig().getBoolean("config.displayDropMessage"))
				plugin.sendPreMessage(p, "dropItemMessage");
		}
	}
	
	@EventHandler
	public void onPlayerPickUpItem(PlayerPickupItemEvent event)
	{
		Player p = event.getPlayer();
		
		if(!plugin.getConfig().getBoolean("config.allowPickUpItems"))
		{
			event.setCancelled(true);
			
			if(plugin.getConfig().getBoolean("config.displayPickUpMessage"))
				plugin.sendPreMessage(p, "pickUpItemMessage");
		}
	}
}
