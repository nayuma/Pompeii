package io.pompeii.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class MuteChatListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent aspce) { 
		
		Player p = aspce.getPlayer();
		
		if(ALMGR.MUTED_CHAT.contains(p.getUniqueId()) && (!(p.hasPermission("pompeii.staff")))) {
			
			aspce.setCancelled(true);
			
			p.sendMessage(Lang.PLAYER_CHAT_IS_MUTED.toString());
			
		}
		
	}

}
