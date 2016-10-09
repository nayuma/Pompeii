package io.pompeii.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class RTP implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("rtp")) {
			
			if(p.hasPermission("pompeii.staff")) {
		
				for(Player players : Bukkit.getOnlinePlayers()) {
					
					if(Bukkit.getOnlinePlayers().length > 1) {
					
					if(!(players.hasPermission("pompeii.staff"))) {
						
						ALMGR.RTP.add(players);
						
						Player pl = ALMGR.RTP.get(new Random().nextInt(ALMGR.RTP.size()));
						
						p.teleport(pl.getLocation());
						
						p.sendMessage(Lang.PLAYER_RANDOM_TELEPORTED_TO.toString().replaceAll("%p%", pl.getName()));
						
						ALMGR.RTP.remove(players);
						
						return true;
						
						}
					
					}
					
					if(Bukkit.getOnlinePlayers().length == 1) {
						
						p.sendMessage(Lang.NOT_ENOUGH_PLAYERS.toString());
						
						break;
						
					}
					
				}
				
			}
			
			if(!(p.hasPermission("pompeii.staff"))) {
				
				p.sendMessage(Lang.NO_PERMISSION.toString());
				
				return true;
				
				}
			
			}
		
		return true;
		
	}
}				
		