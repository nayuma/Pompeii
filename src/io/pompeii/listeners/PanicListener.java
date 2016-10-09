package io.pompeii.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import io.pompeii.managers.HMMGR;

public class PanicListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPanicDamage(EntityDamageByEntityEvent edbee) {
		
		if(HMMGR.PANIC.containsKey(edbee.getEntity().getUniqueId())) {
			
			edbee.setCancelled(true);
			edbee.getEntity().setFireTicks(0);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPanicMove(PlayerMoveEvent pme) {
		
		if(HMMGR.PANIC.containsKey(pme.getPlayer().getUniqueId())) {
			
			pme.setTo(pme.getFrom());
			
		}
		
	}
}
