package fr.Jivaa.EyrodCreative.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.Jivaa.EyrodCreative.EyrodCreative;

public class ECBlockListener implements Listener
{
	private EyrodCreative plugin;
	
	public ECBlockListener(EyrodCreative plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		if(!plugin.hasPermission(p, b.getTypeId(), b.getData()))
		{
			event.setCancelled(true);
			
			if(plugin.getConfig().getBoolean("config.displayBlockPlaceMessage"))
				plugin.sendPreMessage(p, "blockPlaceMessage");
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		if(!plugin.hasPermission(p, b.getTypeId(), b.getData()))
		{
			event.setCancelled(true);
			
			if(plugin.getConfig().getBoolean("config.displayBlockBreakMessage"))
				plugin.sendPreMessage(p, "blockBreakMessage");
		}
	}
}
