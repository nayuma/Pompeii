package io.pompeii.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import io.pompeii.utils.SettingsManager;

public class DeathLookupListener implements Listener {
	
	private Date date = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent pde) {
		
		Player p = pde.getEntity();
		
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		
		DamageCause dc = p.getLastDamageCause().getCause();

		if(!(SettingsManager.getInstance().getDeath().contains(p.getUniqueId().toString()))) {
			
			SettingsManager.getInstance().getDeath().createSection(p.getUniqueId().toString());
		
		}
		
		ConfigurationSection cfgs = SettingsManager.getInstance().getDeath().getConfigurationSection(p.getUniqueId().toString());
		
		cfgs.set("name", p.getName());
		cfgs.set("time", dateformat.format(date).toString());
		cfgs.set("world", p.getLocation().getWorld().getName());
		cfgs.set("x", x);
		cfgs.set("y", y);
		cfgs.set("z", z);
		
		if(pde.getEntity().getKiller() instanceof Player) {
			
		cfgs.set("killer", p.getKiller().getName());
		
		}
		
		if(dc == DamageCause.DROWNING) {
			
			cfgs.set("killer", "Drowned");
			
		}
		
		if(dc == DamageCause.LAVA) {
			
			cfgs.set("killer", "Lava");
			
		}
		
		if(dc == DamageCause.BLOCK_EXPLOSION) {
			
			cfgs.set("killer", "Block Explosion [TNT]");
			
		}
		
		if(dc == DamageCause.FALL) {
			
			cfgs.set("killer", "Fall Damage");
			
		}
		
		if(dc == DamageCause.SUFFOCATION) {
			
			cfgs.set("killer", "Suffocation");
			
		}
		
		if(dc == DamageCause.STARVATION) {
			
			cfgs.set("killer", "Starvation");
			
		}
		
		if(dc == DamageCause.WITHER) {
			
			cfgs.set("killer", "Withered");
			
		}
		
		if(dc == DamageCause.PROJECTILE)  {
			
			cfgs.set("killer", p.getKiller().getName() + " + Projectile");
			
		}
		
		if(dc == DamageCause.ENTITY_EXPLOSION) {
			
			cfgs.set("killer", "Entity Explosion [Creeper]");
			
		}
		
		if(dc == DamageCause.MAGIC) {
			
			cfgs.set("killer", p.getKiller().getName() + " + Damaging Potions [Magic]");
			
		}
		
		if(dc == DamageCause.LIGHTNING) {
			
			cfgs.set("killer", "Lighting");
			
		}
		
		if(dc == DamageCause.SUICIDE) {
			
			cfgs.set("killer", "Suicide");
			
		}
		
		if(dc == DamageCause.VOID) {
			
			cfgs.set("killer", p.getKiller().getName() + " + Void");
			
		}
		
	}

}
