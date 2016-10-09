
package io.pompeii.utils;

import java.io.File;
import java.io.IOException;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import io.pompeii.main.Pompeii;
 
public class SettingsManager {
 
        private SettingsManager() { }
       
        static SettingsManager instance = new SettingsManager();
       
        public static SettingsManager getInstance() {
                return instance;
        }
       
        Plugin p;
       
       public static FileConfiguration DEATH;
       public File DEATHFILE;
        
       public static FileConfiguration ITEM;
       public File ITEMFILE;
        
       public static FileConfiguration COOLDOWN;
       public File CDFILE;
       
       
       
        public void setup(Plugin p) {
               
                if (!p.getDataFolder().exists()) {
                        p.getDataFolder().mkdir();
                }
                
                ITEMFILE = new File(p.getDataFolder(), "items.yml");
               
                DEATHFILE = new File(p.getDataFolder(), "deaths.yml");
                
                CDFILE = new File(p.getDataFolder(), "cooldowns.yml");
                
                if(!ITEMFILE.exists()) {
                	try {
                				ITEMFILE.createNewFile();
                				Pompeii.plugin.saveResource("items.yml", false);
                	}
                	 catch (IOException e) {
                		 		Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create items.yml");
                	 }
                }
                
                ITEM = YamlConfiguration.loadConfiguration(ITEMFILE);
               
                if (!DEATHFILE.exists()) {
                        try {
                                DEATHFILE.createNewFile();
                        }
                        catch (IOException e) {
                                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create deaths.yml!");
                        }
                }
               
                DEATH = YamlConfiguration.loadConfiguration(DEATHFILE);
                
                if(!CDFILE.exists()) {
                	try {
                		CDFILE.createNewFile();
                		Pompeii.plugin.saveResource("cooldowns.yml", false);
                	} catch (IOException e) {
                				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create cooldowns.yml");
                	}
                }

                COOLDOWN = YamlConfiguration.loadConfiguration(CDFILE);
        }
       
        public FileConfiguration getDeath() {
                return DEATH;
        }
       
        public void saveDeath() {
                try {
                        DEATH.save(DEATHFILE);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save deaths.yml!");
                }
        }
       
        public void reloadDeath() {
                DEATH = YamlConfiguration.loadConfiguration(DEATHFILE);
        }
        
        public FileConfiguration getItem() {
        		return ITEM;
        }
        
        public void saveItem() {
        		try {
        			ITEM.save(ITEMFILE);
        		} catch (IOException e) {
        				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save items.yml");
        		}
        }
        
        public void reloadItem() {
        	ITEM = YamlConfiguration.loadConfiguration(ITEMFILE);
        }
        
        public FileConfiguration getCooldown() {
        	return COOLDOWN;
        }
        
        public void saveCooldown() {
        	try {
        		COOLDOWN.save(CDFILE);
        	} catch (IOException e) {
        				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save cooldowns.yml");
        	}
        }
        
        public void reloadCooldown() {
        	COOLDOWN = YamlConfiguration.loadConfiguration(CDFILE);
        }
        
        public void saveDefaultItem() {
        	if(ITEMFILE == null) {
        		ITEMFILE = new File(Pompeii.plugin.getDataFolder(), "items.yml");
        	}
        	if(!(ITEMFILE.exists())) {
        		Pompeii.plugin.saveResource("items.yml", false);
        	}
        }
       
        
       
        public PluginDescriptionFile getDesc() {
                return p.getDescription();
        }
}