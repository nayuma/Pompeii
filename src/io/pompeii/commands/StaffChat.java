package io.pompeii.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class StaffChat implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("sc")) {
			
			if(p.hasPermission("pompeii.staff")) {
			
				if(ALMGR.STAFF_CHAT.contains(p.getUniqueId())) {
				
					p.sendMessage(Lang.STAFF_CHAT_PREFIX.toString() + Lang.STAFF_CHAT_DISABLED.toString());
					
					ALMGR.STAFF_CHAT.remove(p.getUniqueId());
					
					return true;
				
			
				} else {
					
					p.sendMessage(Lang.STAFF_CHAT_PREFIX.toString() + Lang.STAFF_CHAT_ENABLED.toString());
					
					ALMGR.STAFF_CHAT.add(p.getUniqueId());
					
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

}
