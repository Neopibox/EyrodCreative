package fr.Jivaa.EyrodCreative;

import java.util.Properties;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.ptibiscuit.framework.JavaPluginEnhancer;

import fr.Jivaa.EyrodCreative.listeners.ECBlockListener;
import fr.Jivaa.EyrodCreative.listeners.ECInventoryListener;
import fr.Jivaa.EyrodCreative.listeners.ECPlayerListener;

public class EyrodCreative extends JavaPluginEnhancer
{
	private static EyrodCreative instance;

	@Override
	public void onEnable()
	{
		this.setup(ChatColor.WHITE + "[" + ChatColor.RED + "EyrodCreative" + ChatColor.WHITE + "]", "eyrodcreative", true);
		instance = this;
		
		myLog.startFrame();
		myLog.addInFrame("EyrodCreative by Jivaa for Eyrod : http://eyrod.fr/");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ECBlockListener(this), this);
		pm.registerEvents(new ECPlayerListener(this), this);
		pm.registerEvents(new ECInventoryListener(this), this);
		
		myLog.displayFrame(false);
	}
	
	@Override
	public void onDisable()
	{
		myLog.startFrame();
		myLog.addInFrame("EyrodCreative disabled !");
		myLog.displayFrame(false);
	}
	
	@Override
	public void onConfigurationDefault(FileConfiguration config)
	{
		// Allow some actions
		config.set("config.allowDropItems", true);
		config.set("config.allowPickUpItems", false);
		
		// Display messages
		config.set("config.displayBlockPlaceMessage", true);
		config.set("config.displayBlockBreakMessage", true);
		config.set("config.displayDropItemMessage", true);
		config.set("config.displayPickUpItemMessage", true);
		config.set("config.displayInventoryCreativeMessage", true);
	}
	
	@Override
	public void onLangDefault(Properties p)
	{
		p.setProperty("blockPlaceMessage", "Vous n'avez pas le droit de placer ce bloc !");
		p.setProperty("blockBreakMessage", "Vous n'avez pas le droit de casser ce bloc !");
		p.setProperty("dropItemMessage", "Vous n'avez pas le droit de lâcher des items !");
		p.setProperty("pickUpItemMessage", "Vous n'avez pas le droit de récupérer des items !");
		p.setProperty("inventoryCreativeMessage", "Vous n'avez pas le droit de prendre ce bloc !");
	}
	
	public static EyrodCreative getInstance()
	{
		return instance;
	}
	
	public boolean hasPermission(Player p, int id, int metadata)
	{
		if(getPermissionHandler().has(p, "allow." + id, false))
			return true;
		else if(getPermissionHandler().has(p, "allow." + id + ":" + metadata, false))
			return true;
		else
			return false;
	}
}
