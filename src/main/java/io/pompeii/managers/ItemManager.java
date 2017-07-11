package io.pompeii.managers;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.pompeii.listeners.FreezeListener;
import io.pompeii.utils.ItemBuilder;
import io.pompeii.utils.Lang;
import io.pompeii.utils.Ping;
import io.pompeii.utils.SettingsManager;

public class ItemManager implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerFreeze(PlayerInteractEntityEvent piee) {
		
		Player p = piee.getPlayer();
		//Player cp = (Player) piee.getRightClicked();
		
		if(!(piee.getRightClicked() instanceof Player)) {
			
			return;
			
		}
		
		if(piee.getRightClicked() instanceof Player) {
			
			if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
				
				if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("FREEZE_ITEM"))) {
					
					if(ALMGR.FROZEN.contains(piee.getRightClicked().getUniqueId())) {
						
						ALMGR.FROZEN.remove(piee.getRightClicked().getUniqueId());
						
						p.sendMessage(Lang.STAFF_UNFREEZE_MESSAGE.toString().replaceAll("%p%", ((HumanEntity) piee.getRightClicked()).getName()));
						
						((CommandSender) piee.getRightClicked()).sendMessage(Lang.PLAYER_UNFREEZE_MESSAGE.toString());
						
					} else {
						
						ALMGR.FROZEN.add(piee.getRightClicked().getUniqueId());
						
						p.sendMessage(Lang.STAFF_FREEZE_MESSAGE.toString().replaceAll("%p%", ((HumanEntity) piee.getRightClicked()).getName()));
						
						 ((CommandSender) piee.getRightClicked()).sendMessage(Lang.PLAYER_FREEZE_MESSAGE.toString());
						
						FreezeListener.gui((Player) piee.getRightClicked());
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInvInspect(PlayerInteractEntityEvent piee) {
		
		Player p = piee.getPlayer();
		//Player cp = (Player) piee.getRightClicked();
		
		if(!(piee.getRightClicked() instanceof Player)) {
			
			return;
			
		}
		
		if(piee.getRightClicked() instanceof Player) {
		
			if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
				
				if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("INV_ITEM"))) {
						
						p.openInventory(gui((Player) piee.getRightClicked()));
					
				}
			}
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onInteractRTP(PlayerInteractEvent pie) {
		
		Player p = pie.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
		
			if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("RTP_ITEM"))) {
				
					if(pie.getAction() == Action.RIGHT_CLICK_AIR || pie.getAction() == Action.RIGHT_CLICK_BLOCK) {
						
						for(Player players : Bukkit.getOnlinePlayers()) {
							
							if(Bukkit.getOnlinePlayers().length > 1) {
							
							if(!(players.hasPermission("pompeii.staff"))) {
								
								ALMGR.RTP.add(players);
								
								Player pl = ALMGR.RTP.get(new Random().nextInt(ALMGR.RTP.size()));
								
								p.teleport(pl.getLocation());
								
								p.sendMessage(Lang.PLAYER_RANDOM_TELEPORTED_TO.toString().replaceAll("%p%", pl.getName()));
								
								ALMGR.RTP.remove(players);
								
								}
							
							}
							
							if(Bukkit.getOnlinePlayers().length == 1) {
								
								p.sendMessage(Lang.NOT_ENOUGH_PLAYERS.toString());
								
								break;
								
							}
							
						}
						
					}
				
				}
			
			}
		}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onInteractRTPXray(PlayerInteractEvent pie) {
		
		Player p = pie.getPlayer();
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (p.hasPermission("pompeii.staff"))) {
			
			if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("RTP_XRAY_ITEM"))) {
				
				if(pie.getAction() == Action.RIGHT_CLICK_AIR || pie.getAction() == Action.RIGHT_CLICK_BLOCK) {
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						
						if(Bukkit.getOnlinePlayers().length > 1) {
						
						if(!(players.hasPermission("pompeii.staff"))) {
							
							ALMGR.RTP.add(players);
							
							Player pl = ALMGR.RTP.get(new Random().nextInt(ALMGR.RTP.size()));
							
							if(pl.getLocation().getBlockY() > 25) {
							
							p.teleport(pl.getLocation());
							
							p.sendMessage(Lang.PLAYER_RANDOM_TELEPORTED_TO.toString().replaceAll("%p%", pl.getName()));
							
							ALMGR.RTP.remove(players);
							
								}
							
						} else {
							
							break;
							
							}
						
						}
						
						if(Bukkit.getOnlinePlayers().length == 1) {
							
							p.sendMessage(Lang.NOT_ENOUGH_PLAYERS.toString());
							
							break;
							
						}
					
					}
				
				}
			
			}
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerVanish(PlayerInteractEvent pie) {
		
		Player p = pie.getPlayer();
		
		ItemStack vanished = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("VANISHED_ITEM")), 1, (byte) SettingsManager.getInstance().getItem().getInt("VANISHED_ITEM_COLOR")).setName(SettingsManager.getInstance().getItem().getString("VANISHED_NAME").replace('&', '§')).toItemStack();
		ItemStack unvanished = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("UNVANISHED_ITEM")), 1, (byte) SettingsManager.getInstance().getItem().getInt("UNVANISHED_ITEM_COLOR")).setName(SettingsManager.getInstance().getItem().getString("UNVANISHED_NAME").replace('&', '§')).toItemStack();
		
		if(pie.getAction() == Action.RIGHT_CLICK_AIR || (pie.getAction() == Action.RIGHT_CLICK_BLOCK)) {
		
		if(ALMGR.STAFF.contains(p.getUniqueId()) && (ALMGR.VANISHED.contains(p.getUniqueId()))) {
			
				if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("VANISHED_ITEM"))) {
			
					p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("VANISH_SLOT"), unvanished);
			
					p.performCommand("v");
			
				}
			
		} else {
			
				if(p.getItemInHand().getType() == Material.getMaterial(SettingsManager.getInstance().getItem().getString("UNVANISHED_ITEM"))) {
			
					p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("VANISH_SLOT"), vanished);
			
					p.performCommand("v");
			
				}
			
			}
			
		}
		
		
	}
	
		
	
	 public static Inventory gui(Player p) {
		 
		  Damageable p2 = p;
		  double health = p2.getHealth();
		 
		  Inventory inv = Bukkit.createInventory(p, 54,ChatColor.GRAY + "" + ChatColor.BOLD + p.getName() + ChatColor.GOLD + " inventory");
		  inv.setContents(p.getInventory().getContents());
		  
		  ItemStack emptyslot = new ItemBuilder(Material.STAINED_GLASS_PANE).setDyeColor(DyeColor.RED).toItemStack();
		  ItemStack armorglass = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(ChatColor.GREEN + "Armor -->").setDyeColor(DyeColor.RED).toItemStack();
		  ItemStack iteminhandglass = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(ChatColor.GREEN + "<-- Item In Hand").setDyeColor(DyeColor.RED).toItemStack();
		  
		  ItemStack vitals = new ItemBuilder(Material.BONE).setName(ChatColor.GOLD + "Vitals").setLore("§6Health \u226B " +  "§7" + health, "§6Food \u226B " +  "§7" + p.getFoodLevel(), "§6Experience \u226B " + "§7" + p.getExpToLevel()).toItemStack();
		  ItemStack ipaddress = new ItemBuilder(Material.BEACON).setName(ChatColor.GOLD + "Connection Information").setLore("§6IP Address \u226B " + "§7" + p.getAddress().getAddress().getHostAddress(), "§6Ping \u226B " + "§7" + Ping.pingPlayer(p) + "§6 ms").toItemStack();
		  ItemStack name = new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GOLD + "Indentifiers").setLore("§6" + "Name \u226B "  + "§7" + p.getName(), "§6UUID \u226B "  + "§7" + p.getUniqueId()).toItemStack();
		  
		  inv.setItem(39, vitals);
		  inv.setItem(40, ipaddress);
		  inv.setItem(41, name);
		  
		  inv.setItem(45, emptyslot);
		  inv.setItem(46, armorglass);
		  inv.setItem(47, p.getInventory().getHelmet());
		  inv.setItem(48, p.getInventory().getChestplate());
		  inv.setItem(49, p.getInventory().getLeggings());
		  inv.setItem(50, p.getInventory().getBoots());
		  inv.setItem(51, p.getItemInHand());
		  inv.setItem(52, iteminhandglass);
		  inv.setItem(53, emptyslot);
		  
		  return inv;
		  
		   }
}
