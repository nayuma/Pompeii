package io.pompeii.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import io.pompeii.commands.Staff;
import io.pompeii.main.Pompeii;
import io.pompeii.managers.ALMGR;
import io.pompeii.utils.Lang;

public class StaffListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDropItemEvent(PlayerDropItemEvent pdie) {
		
		Player p = pdie.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId())) {
			
			pdie.setCancelled(true);
			
			p.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_ITEM_DROP_EVENT.toString());
			
		}
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.bypass"))) {
			
			return;
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDamage(EntityDamageByEntityEvent edbee) {
		
		//Player p = (Player) edbee.getEntity();
		
		if(ALMGR.STAFF.contains(edbee.getEntity().getUniqueId())) {
		
				edbee.setCancelled(true);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent ppie) {
		
		Player p = ppie.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId())) {
			
			ppie.setCancelled(true);
			
			p.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_ITEM_PICKUP_EVENT.toString());
			
		}
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.bypass"))) {
			
			return;
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerBreakObject(BlockBreakEvent bbe) {
		
		Player p = bbe.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId())) {
			
			bbe.setCancelled(true);
			bbe.getBlock().getDrops().clear();
			
			p.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_BLOCK_BREAK_EVENT.toString());
			
			
		}
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.bypass"))) {
			
			return;
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPlaceObject(BlockPlaceEvent bpe) {
		
		Player p = bpe.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId())) {
			
			bpe.setCancelled(true);
			
			p.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_BLOCK_BREAK_EVENT.toString());
			
		}
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.bypass"))) {
			
			return;
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onStaffChatEvent(AsyncPlayerChatEvent aspce) {
		
		Player p = aspce.getPlayer();
		
		if(ALMGR.STAFF_CHAT.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
			
			for(Player staff : Bukkit.getOnlinePlayers()) {
					
					staff.sendMessage(Lang.STAFF_CHAT_PREFIX + p.getName() + " \u226B " + aspce.getMessage());
					
					aspce.setCancelled(true);
					
				}
				
			}
			
		}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent pie) {
		
		Player p = pie.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && ((p.hasPermission("pompeii.staff")))) {
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.SOIL)) {
			
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.DIRT)) {
				
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.STONE_PLATE)) {
				
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.WOOD_PLATE)) {
				
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.GOLD_PLATE)) {
				
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.IRON_PLATE)) {
				
				pie.setCancelled(true);
				
			}
			
			if(pie.getAction() == Action.PHYSICAL && (pie.getClickedBlock().getType() == Material.CACTUS)) {
				
				pie.setCancelled(true);
				
			}
		
		}

	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent pce) {
		
		Player p = (Player) pce.getWhoClicked();
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
			
			pce.setCancelled(true);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onStaffJoin(PlayerJoinEvent pje) {
		
		new BukkitRunnable() {
			
			public void run() {
				
				if(!(pje.getPlayer().hasPermission("pompeii.staff.leave"))  && (!(ALMGR.STAFF.contains(pje.getPlayer().getUniqueId())))) {
					
					Staff.staffInv(pje.getPlayer());
					
					ALMGR.STAFF.add(pje.getPlayer().getUniqueId());
					
				}
				
			}
			
		}.runTask(Pompeii.plugin);
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerSilentChest(PlayerInteractEvent pie) {
		
         Player p = pie.getPlayer();
         Block block = pie.getClickedBlock();
        
        if (ALMGR.STAFF.contains(p.getUniqueId()) && pie.getAction() == Action.RIGHT_CLICK_BLOCK && (block.getType() == Material.CHEST || block.getType() == Material.TRAPPED_CHEST)) {
        	
            pie.setCancelled(true);
            
            Inventory inv = Bukkit.createInventory(p, 54, "Silent Chest");
            
            Chest chest = (Chest)block.getState();
            inv.setContents(chest.getInventory().getContents());
            
            p.openInventory(inv);
            
            p.sendMessage(Lang.SILENT_CHEST_OPEN.toString());
            
        	}
    	}
		
	}
