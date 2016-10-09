package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.managers.HMMGR;
import io.pompeii.utils.Lang;

public class Panic implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("panic")) {
			
			if(p.hasPermission("pompeii.player.panic")) {
				
				if(HMMGR.PANIC.containsKey(p.getUniqueId())) {
					
					HMMGR.PANIC.remove(p.getUniqueId());
					
					p.sendMessage(Lang.PREFIX.toString() + Lang.PLAYER_UNPANIC_MESSAGE.toString());
					
					for(Player staff : Bukkit.getOnlinePlayers()) {
						
						if(staff.hasPermission("pompeii.staff")) {
							
							staff.sendMessage(Lang.STAFF_UNPANIC_MESSAGE.toString().replaceAll("%p%", p.getName()));
							
						}
					}
					
				} else {
					

					// Depricate cooldown method
					HMMGR.PANIC.put(p.getUniqueId(), System.currentTimeMillis() + (1 + 1000));
					
					p.sendMessage(Lang.PREFIX.toString() + Lang.PLAYER_PANIC_MESSAGE.toString());
					
					for(Player staff : Bukkit.getOnlinePlayers()) {
						
						if(staff.hasPermission("pompeii.staff")) {
							
							staff.sendMessage(Lang.STAFF_PANIC_MESSAGE.toString().replaceAll("%p%", p.getName()));
							
						}
						
					}
					
					return true;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
}
