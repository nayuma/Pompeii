package io.pompeii.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import io.pompeii.utils.SettingsManager;

public class ConfigurationCommands implements CommandExecutor {

	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(c.getName().equalsIgnoreCase("pompeii")) {
			
			if(s.hasPermission("pompeii.staff")) {
				
				if(args.length == 0) {
					
					s.sendMessage(ChatColor.RED + " --- Invalid Syntax --- ");
					s.sendMessage(ChatColor.RED + "/pompeii cfg save");
					s.sendMessage(ChatColor.RED + "/pompeii cfg load");
					
					return true;
					
				}
				
				if(args.length == 2) {
					
					if(args[0].equalsIgnoreCase("cfg")) {
				
					if(args[1].equalsIgnoreCase("save")) {
						
						SettingsManager.getInstance().saveItem();
						SettingsManager.getInstance().saveDeath();
						SettingsManager.getInstance().saveCooldown();
						
						s.sendMessage(ChatColor.RED + "SAVED ALL CONFIGURATION FILES");
						
						return true;
						
					}
					
					if(args[1].equalsIgnoreCase("load")) {
						
						YamlConfiguration.loadConfiguration(SettingsManager.getInstance().CDFILE);
						YamlConfiguration.loadConfiguration(SettingsManager.getInstance().ITEMFILE);
						YamlConfiguration.loadConfiguration(SettingsManager.getInstance().DEATHFILE);
						
						s.sendMessage(ChatColor.RED + "LOADED ALL CONFIGURATION FILES");
						
						return true;
						
						}
					
					}
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	

}
