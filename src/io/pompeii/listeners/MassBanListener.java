package io.pompeii.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import io.pompeii.managers.HMMGR;
import io.pompeii.utils.Lang;
import io.pompeii.utils.SettingsManager;

public class MassBanListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMassBan(PlayerCommandPreprocessEvent pcpe) {
		
		Player p = pcpe.getPlayer();
		
		
		if(pcpe.getMessage().split(" ")[0].equalsIgnoreCase("/ban")) {
			
			if(HMMGR.MASS_BAN_PREVENTER.containsKey(p.getUniqueId()) && HMMGR.MASS_BAN_PREVENTER.get(p.getUniqueId()) > System.currentTimeMillis()) {
				
				pcpe.setCancelled(true);
				
				p.sendMessage(Lang.MASS_BAN_STOPPED.toString());
				
			} else {
			
			HMMGR.MASS_BAN_PREVENTER.put(p.getUniqueId(), System.currentTimeMillis() + (SettingsManager.getInstance().getCooldown().getInt("BAN_COOLDOWN") * 1000));
			
			}
			
		}
		
		if(pcpe.getMessage().split(" ")[0].equalsIgnoreCase("/eban")) {
			
			if(HMMGR.MASS_BAN_PREVENTER.containsKey(p.getUniqueId()) && HMMGR.MASS_BAN_PREVENTER.get(p.getUniqueId()) > System.currentTimeMillis()) {
				
				pcpe.setCancelled(true);
				
				p.sendMessage(Lang.MASS_BAN_STOPPED.toString());
				
			} else {
			
			HMMGR.MASS_BAN_PREVENTER.put(p.getUniqueId(), System.currentTimeMillis() + (SettingsManager.getInstance().getCooldown().getInt("BAN_COOLDOWN") * 1000));
			
			}
			
		}
		
		if(pcpe.getMessage().split(" ")[0].equalsIgnoreCase("/punish")) {
			
			if(HMMGR.MASS_BAN_PREVENTER.containsKey(p.getUniqueId()) && HMMGR.MASS_BAN_PREVENTER.get(p.getUniqueId()) > System.currentTimeMillis()) {
				
				pcpe.setCancelled(true);
				
				p.sendMessage(Lang.MASS_BAN_STOPPED.toString());
				
			} else {
			
			HMMGR.MASS_BAN_PREVENTER.put(p.getUniqueId(), System.currentTimeMillis() + (SettingsManager.getInstance().getCooldown().getInt("BAN_COOLDOWN") * 1000));
			
			}
			
		}
		
	}

}
