package fr.Jivaa.EyrodCreative.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

import fr.Jivaa.EyrodCreative.EyrodCreative;

public class ECInventoryListener implements Listener
{
	private EyrodCreative plugin;
	
	public ECInventoryListener(EyrodCreative plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryCreative(InventoryCreativeEvent event)
	{
		Player p = (Player) event.getWhoClicked();
		ItemStack is = event.getCursor();
		
		if(is.getTypeId() == 0)
			return;
		
		if(!plugin.hasPermission(p, is.getTypeId(), is.getData().getData()))
		{
			event.setCancelled(true);
			
			if(plugin.getConfig().getBoolean("config.displayInventoryCreativeMessage"))
				plugin.sendPreMessage(p, "inventoryCreativeMessage");
		}
	}
}
