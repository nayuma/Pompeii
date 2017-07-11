package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.managers.HMMGR;
import io.pompeii.utils.Lang;

public class Request implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("request")) {
			
			if(p.hasPermission("pompeii.report")) {
				
				if(args.length == 0) {
					
					p.sendMessage(ChatColor.RED + "Invalid Syntax: /request <message>");
					
				}
				
				if(args.length == 1) {
					
					if(HMMGR.REQUEST_COOLDOWN.containsKey(p.getUniqueId()) && HMMGR.REQUEST_COOLDOWN.get(p.getUniqueId()) > System.currentTimeMillis()) {
					
		                long remaining = HMMGR.REQUEST_COOLDOWN.get(p.getUniqueId()) - System.currentTimeMillis();
		                int seconds = (int)(remaining / 1000) % 60;
		                int minutes = (int)(remaining / 60000 % 60);
		                
		                p.sendMessage(Lang.REQUEST_COOLDOWN.toString().replaceAll("%time%", minutes + " minutes " + seconds + " seconds"));
						
		                
		                return true;
					
					} else {
						
						HMMGR.REQUEST_COOLDOWN.put(p.getUniqueId(), System.currentTimeMillis() + (SettingsManager.getInstance().getCooldown().getInt("REQUEST_COOLDOWN") * 1000));
			
						String message = "";
						
						for(int i = 0; i < args.length; ++i) {
							
							message = String.valueOf(message) + args[i] + " ";
							
							for(Player staff : Bukkit.getOnlinePlayers()) {
								
								if(staff.hasPermission("pompeii.staff")) {
									
									staff.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "R" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD  + "] " + ChatColor.GRAY + s.getName() + "\u226B " + message);
									
									p.sendMessage(Lang.REQUEST_SUCCESSFUL.toString());
									
									return true;
									
								}
								
							}
							
						}
						
					}
				
				}
					
			}
			
		}
		
		return true;
		
	}

}
