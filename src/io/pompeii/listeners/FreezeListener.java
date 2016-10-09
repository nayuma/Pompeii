package io.pompeii.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.pompeii.main.Pompeii;
import io.pompeii.managers.ALMGR;
import io.pompeii.utils.ItemBuilder;
import io.pompeii.utils.Lang;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class FreezeListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onFrozenMove(PlayerMoveEvent pme) {
		
		Player p = pme.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			pme.setTo(pme.getFrom());
			
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588\u2588\u2588\u2588&c\u2588&f\u2588\u2588\u2588\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588\u2588\u2588&c\u2588&6\u2588&c\u2588&f\u2588\u2588\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588\u2588&c\u2588&6\u2588&0\u2588&6\u2588&c\u2588&f\u2588\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588\u2588&c\u2588&6\u2588&0\u2588&6\u2588&c\u2588&f\u2588\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588&c\u2588&6\u2588\u2588&0\u2588&6\u2588\u2588&c\u2588&f\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&f\u2588&c\u2588&6\u2588\u2588\u2588\u2588\u2588&c\u2588&f\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&c\u2588&6\u2588\u2588\u2588&0\u2588&6\u2588\u2588\u2588&c\u2588"));
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&c\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588"));
            p.sendMessage(Lang.PLAYER_FREEZE_MESSAGE.toString());
            p.sendMessage(Lang.TEAMSPEAK_IP.toString());
            
			p.openInventory(gui(p));
            
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent pie) {
		
		Player p = pie.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			pie.setCancelled(true);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInvClose(InventoryCloseEvent ice) {
		
		Player p = (Player) ice.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			new BukkitRunnable() {
				
				public void run() {	
				
					p.openInventory(gui(p));
			
				}
			
			}.runTaskAsynchronously(Pompeii.plugin);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInvClick(InventoryClickEvent ice) {
		
		if(ALMGR.FROZEN.contains(ice.getWhoClicked().getUniqueId())) {
			
			ice.setCancelled(true);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDropItem(PlayerDropItemEvent pdie) {
		
		Player p = pdie.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			pdie.setCancelled(true);
			
			p.sendMessage(Lang.PLAYER_FREEZE_DROP_ITEM.toString());
			
			p.openInventory(gui(p));
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPickup(PlayerPickupItemEvent ppie) {
		
		Player p = ppie.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			ppie.setCancelled(true);
			
			p.openInventory(gui(p));
			
		}
		
	}
	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPlace(BlockPlaceEvent bpe) {
		
		Player p = bpe.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			bpe.setCancelled(true);
			
			p.sendMessage(Lang.PLAYER_FREEZE_BLOCK_PLACE.toString());
			
			p.openInventory(gui(p));
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerBreak(BlockBreakEvent bbe) {
		
		Player p = bbe.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			bbe.setCancelled(true);
			bbe.getBlock().getDrops().clear();
			
			p.sendMessage(Lang.PLAYER_FREEZE_BLOCK_BREAK.toString());
			
			p.openInventory(gui(p));
			
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent pqe) {
		
		Player p = pqe.getPlayer();
		
		if(ALMGR.FROZEN.contains(p.getUniqueId())) {
			
			for(Player staff : Bukkit.getOnlinePlayers()) {
				
				if(staff.hasPermission("pompeii.staff")) {
					
					ComponentBuilder jsonmsgbuilder = new ComponentBuilder(p.getName() + " has QUIT while frozen! ");
					jsonmsgbuilder.color(net.md_5.bungee.api.ChatColor.DARK_RED);
					jsonmsgbuilder.append(ChatColor.DARK_RED + "" + ChatColor.BOLD + "CLICK HERE TO BAN THEM.");
					jsonmsgbuilder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Lang.QUIT_BAN_COMMAND.toString().replaceAll("%p%", p.getName()) ));
					
					staff.spigot().sendMessage(jsonmsgbuilder.create());
					
				}
				
			}
			
		}
		
	}
	
	 public static Inventory gui(Player p) {
		 
	 Inventory inv = Bukkit.createInventory(p, 27, ChatColor.RED + "Please join " + Lang.TEAMSPEAK_IP.toString());
	 
	 ItemStack emptyslot = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 14).toItemStack();
	 ItemStack frozen = new ItemBuilder(Material.ICE, 1).setName(Lang.PLAYER_FREEZE_MESSAGE.toString() + "" + Lang.TEAMSPEAK_IP.toString()).toItemStack();
	 
	 inv.setItem(0, emptyslot);
	 inv.setItem(1, emptyslot);
	 inv.setItem(2, emptyslot);
	 inv.setItem(3, emptyslot);
	 inv.setItem(4, emptyslot);
	 inv.setItem(5, emptyslot);
	 inv.setItem(6, emptyslot);
	 inv.setItem(7, emptyslot);
	 inv.setItem(8, emptyslot);
	 inv.setItem(9, emptyslot);
	 inv.setItem(10, frozen);
	 inv.setItem(11, frozen);
	 inv.setItem(12, frozen);
	 inv.setItem(13, frozen);
	 inv.setItem(14, frozen);
	 inv.setItem(15, frozen);
	 inv.setItem(16, frozen);
	 inv.setItem(17, emptyslot);
	 inv.setItem(18, emptyslot);
	 inv.setItem(19, emptyslot);
	 inv.setItem(20, emptyslot);
	 inv.setItem(21, emptyslot);
	 inv.setItem(22, emptyslot);
	 inv.setItem(23, emptyslot);
	 inv.setItem(24, emptyslot);
	 inv.setItem(25, emptyslot);
	 inv.setItem(26, emptyslot);
	 inv.setItem(26, emptyslot);
	 
	 
	 return inv;
	 
	 }
	
	

}
