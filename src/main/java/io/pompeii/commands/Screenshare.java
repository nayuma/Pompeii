package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.listeners.FreezeListener;
import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class Screenshare implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("ss")) {
			
			if(p.hasPermission("pompeii.staff")) {
				
				if(args.length == 0) {
					
					p.sendMessage(ChatColor.RED + "Invalid Syntax: /ss <player | /freeze <player>");
					
					return true;
					
				}
				
				if(args.length == 1) {
					
					Player t = Bukkit.getServer().getPlayer(args[0]);
					
					if(t == null) {
					
						p.sendMessage(ChatColor.RED + args[0] + " doesn't exist!");
					
						return true;
					
					}
				
				if(ALMGR.FROZEN.contains(t.getUniqueId())) {
					
					ALMGR.FROZEN.remove(t.getUniqueId());
					
					p.sendMessage(Lang.STAFF_UNFREEZE_MESSAGE.toString().replaceAll("%p%", t.getName()));
					
					t.sendMessage(Lang.PLAYER_UNFREEZE_MESSAGE.toString());
					
					for(Player staff : Bukkit.getOnlinePlayers()) {
						
						if(staff.hasPermission("pompeii.staff")) {
							
							staff.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_PUBLIC_UNFREEZE_MESSAGE.toString().replaceAll("%fp%", t.getName()).replaceAll("%p%", p.getName()));
							
						}
						
					}
					
					return true;
					
					} else {
					
					ALMGR.FROZEN.add(t.getUniqueId());
					
					p.sendMessage(Lang.STAFF_FREEZE_MESSAGE.toString().replaceAll("%p%", t.getName()));
					
					t.sendMessage(Lang.PLAYER_FREEZE_MESSAGE.toString());
					
					FreezeListener.gui(t);
					
					}
				}
				
				if(!(p.hasPermission("pompeii.staff"))) {
					
					p.sendMessage(Lang.NO_PERMISSION.toString());

					return true;
				}
			}
			
		}
		
		return true;
		
	}

}
