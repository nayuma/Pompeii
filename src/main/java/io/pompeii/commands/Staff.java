package io.pompeii.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.pompeii.main.Pompeii;
import io.pompeii.managers.ALMGR;
import io.pompeii.utils.ItemBuilder;
import io.pompeii.utils.Lang;
import io.pompeii.utils.SettingsManager;

public class Staff implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		Player p = (Player) s;
		
		if(c.getName().equalsIgnoreCase("staff")) {
			
			if(p.hasPermission("pompeii.staff")) {
				
				if(ALMGR.STAFF.contains(p.getUniqueId())) {
					
					if(p.hasPermission("pompeii.staff.leave")) {
					
					ALMGR.STAFF.remove(p.getUniqueId());
					
					p.sendMessage(Lang.PREFIX.toString() + Lang.STAFF_MODE_DISABLED.toString());
					
					p.getInventory().clear();
					p.getActivePotionEffects().clear();
					p.setExp(0);
					p.setFoodLevel(20);
					p.setHealth(20);
					
					return true;
					
					}
					
					if(!(p.hasPermission("pompeii.staff.leave"))) {
						
						p.sendMessage(Lang.PREFIX.toString() + Lang.NO_PERMISSION.toString());
						
						return true;
						
					}
					
				} else {
					
					ALMGR.STAFF.add(p.getUniqueId());
					
					p.sendMessage(Lang.STAFF_MODE_ENABLED.toString());
					
					p.getInventory().clear();
					p.setGameMode(GameMode.CREATIVE);
					p.setAllowFlight(true);
					p.setFlying(true);
					
					staffInv(p);
					
					return true;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	public static void staffInv(Player p) {
		
		ItemStack invinspect = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("INV_ITEM")), 1).setName(SettingsManager.getInstance().getItem().getString("INV_NAME").replace('&', '§')).setLore("Inspect players inventories and more!").setInfinityDurability().toItemStack();
		ItemStack frozen = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("FREEZE_ITEM")), 1).setName(SettingsManager.getInstance().getItem().getString("FREEZE_NAME").replace('&', '§')).setLore("Freeze suspected cheaters with this!").setInfinityDurability().toItemStack();
		ItemStack rtpxray = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("RTP_XRAY_ITEM")), 1).setName(SettingsManager.getInstance().getItem().getString("RTP_XRAY_NAME").replace('&', '§')).setLore("Randomly teleport to players that are mining!").toItemStack();
		ItemStack rtp = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("RTP_ITEM"))).setName(SettingsManager.getInstance().getItem().getString("RTP_NAME").replace('&', '§')).setLore("Randomly teleport to players!").toItemStack();
		ItemStack vanished = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("VANISHED_ITEM")), 1, (byte) SettingsManager.getInstance().getItem().getInt("VANISHED_ITEM_COLOR")).setName(SettingsManager.getInstance().getItem().getString("VANISHED_NAME").replace('&', '§')).toItemStack();
		ItemStack unvanished = new ItemBuilder(Material.getMaterial(SettingsManager.getInstance().getItem().getString("UNVANISHED_ITEM")), 1, (byte) SettingsManager.getInstance().getItem().getInt("UNVANISHED_ITEM_COLOR")).setName(SettingsManager.getInstance().getItem().getString("UNVANISHED_NAME").replace('&', '§')).toItemStack();
		
		p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("INV_INSPECT_SLOT"), invinspect);
		p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("FREEZE_SLOT"), frozen);
		p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("RTP_XRAY_SLOT"), rtpxray);
		p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("RTP_SLOT"), rtp);
		
		new BukkitRunnable() {
			
			public void run() {
				
				if(ALMGR.STAFF.contains(p.getUniqueId()) && (ALMGR.VANISHED.contains(p.getUniqueId()))) {
					
					p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("VANISH_SLOT"), vanished);
					
				}
				
				if(ALMGR.STAFF.contains(p.getUniqueId()) && (!(ALMGR.VANISHED.contains(p.getUniqueId())))) {
					
					p.getInventory().setItem(SettingsManager.getInstance().getItem().getInt("VANISH_SLOT"), unvanished);
					
				}
				
			}
			
		}.runTask(Pompeii.plugin);
		
	}

}
