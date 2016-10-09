package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import io.pompeii.utils.Lang;
import io.pompeii.utils.SettingsManager;
import net.md_5.bungee.api.ChatColor;

public class DeathLookup implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("dl")) {
			
			if(p.hasPermission("pompeii.staff")) {
				
				if(args.length == 0) {
					
					p.sendMessage(ChatColor.RED + "Invalid Syntax: /dl <player>.");
					
					return true;
					
				}
				
				if(args.length == 1) {
					
					if(!(SettingsManager.getInstance().getDeath().contains(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId().toString()))) {
						
						p.sendMessage(ChatColor.RED + args[0] + " doesn't exist or hasn't been logged.");
						
						return true;
						
					}
					
					ConfigurationSection cfgs = SettingsManager.getInstance().getDeath().getConfigurationSection(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId().toString());
					
					p.sendMessage(colorize("&6Name: &7" + cfgs.getString("name") + "\n" + "&6Location: &6x: &7 " + cfgs.getString("x") + " &6y: &7 " + cfgs.getString("y") + " &6z: &7" + cfgs.getString("z") + "\n" + "&6World: &7 " + cfgs.getString("world") + "\n" + "&6Time: &7 " + cfgs.getString("time") + "\n" + "&6Killer: &7 " + cfgs.getString("killer")));
					
					return true;
					
				}
				
			}
			
			if(!(p.hasPermission("pompeii.staff"))) {
				
				p.sendMessage(Lang.NO_PERMISSION.toString());
				
				return true;
				
			}
			
		}
		
		return true;
		
	}
	
	private String colorize(String str) {
		
		return ChatColor.translateAlternateColorCodes('&', str);
		
	}

}
