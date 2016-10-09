package io.pompeii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class MuteChat implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("mc")) {
			
			if(p.hasPermission("pompeii.staff")) {
			
			for(Player players : Bukkit.getOnlinePlayers()) {
				
				if(ALMGR.MUTED_CHAT.contains(players.getUniqueId())) {
					
					ALMGR.MUTED_CHAT.remove(players.getUniqueId());
					
					p.sendMessage(Lang.SENDER_UNMUTE_CHAT_MESSAGE.toString());
					
					Bukkit.broadcastMessage(Lang.BROADCAST_UNMUTE_CHAT_MESSAGE.toString().replaceAll("%p%", p.getName()));
					
					return true;
					
				} else {
					
					ALMGR.MUTED_CHAT.add(players.getUniqueId());
					
					p.sendMessage(Lang.SENDER_MUTE_CHAT_MESSAGE.toString());
					
					Bukkit.broadcastMessage(Lang.BROADCAST_MUTE_CHAT_MESSAGE.toString().replaceAll("%p%", p.getName()));
					
					return true;
					
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
