package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.main.Pompeii;
import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class Vanish implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("v")) {
			
			if(p.hasPermission("pompeii.staff")) {
				
				if(ALMGR.VANISHED.contains(p.getUniqueId())) {
					
					ALMGR.VANISHED.remove(p.getUniqueId());
					
					p.sendMessage(Lang.VANISH_DISABLED.toString());
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						
						players.showPlayer(p);
						
					}
					
					Pompeii.ghostFactory.setGhost(p, false);
					
				} else {
				
				ALMGR.VANISHED.add(p.getUniqueId());
				
				p.sendMessage(Lang.VANISH_ENABLED.toString());
					
				for(Player notstaff : Bukkit.getOnlinePlayers()) {
					
					if(!(notstaff.hasPermission("pompeii.staff"))) {
					
					notstaff.hidePlayer(p);
					
					}
					
					if(notstaff.hasPermission("pompeii.staff")) {
						
						notstaff.showPlayer(p);
						
						Pompeii.ghostFactory.setGhost(p, true);
						
						}
					
					}
				}
				
			}
			
		}
		
		return true;
		
	}
	
}
