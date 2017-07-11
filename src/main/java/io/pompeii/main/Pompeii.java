package io.pompeii.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.pompeii.commands.ConfigurationCommands;
import io.pompeii.commands.DeathLookup;
import io.pompeii.commands.MuteChat;
import io.pompeii.commands.Panic;
import io.pompeii.commands.RTP;
import io.pompeii.commands.RTPXray;
import io.pompeii.commands.Request;
import io.pompeii.commands.Screenshare;
import io.pompeii.commands.Staff;
import io.pompeii.commands.StaffChat;
import io.pompeii.commands.Vanish;
import io.pompeii.listeners.DeathLookupListener;
import io.pompeii.listeners.FreezeListener;
import io.pompeii.listeners.MassBanListener;
import io.pompeii.listeners.MuteChatListener;
import io.pompeii.listeners.PanicListener;
import io.pompeii.listeners.StaffListener;
import io.pompeii.managers.ItemManager;
import io.pompeii.utils.GhostFactory;
import io.pompeii.utils.Lang;
import io.pompeii.utils.SettingsManager;

public class Pompeii extends JavaPlugin {
	
	public static Pompeii plugin;
	public static Pompeii instance;
    public static YamlConfiguration LANG;
    public static File LANG_FILE;
    public static GhostFactory ghostFactory;
	
	@SuppressWarnings("static-access")
	public void onEnable() {

		plugin = this;
		instance = this;
		
		SettingsManager.getInstance().setup(this);
		this.ghostFactory = new GhostFactory(plugin);
		
		System.out.println(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
		System.out.println(ChatColor.RED + "Pompeii has been enabled");
		System.out.println(ChatColor.GREEN + "Version: 1.0 / Unlicensed Copy - Developer Copy - K77E-58VR-B7CS-QDQ9");
		System.out.println(ChatColor.YELLOW + "Product ID: K77E-58VR-B7CS-QDQ9");
		System.out.println(ChatColor.AQUA + "Author: TewLit (defcn)");
		System.out.println(ChatColor.GOLD + "Pompeii is a lightweight staff plugin.");
		System.out.println(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
		
		registerCommands();
		registerListeners();
		loadLang();
		
	}
	
	public void onDisable() {
		
		plugin = null;
		instance = null;
		
		System.out.println(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
		System.out.println(ChatColor.RED + "Pompeii has been disabled");
		System.out.println(ChatColor.GREEN + "Version: 1.0 / Unlicensed Copy - Developer Copy - K77E-58VR-B7CS-QDQ9");
		System.out.println(ChatColor.YELLOW + "Product ID: K77E-58VR-B7CS-QDQ9");
		System.out.println(ChatColor.AQUA + "Author: TewLit (defcn)");
		System.out.println(ChatColor.GOLD + "Pompeii is a lightweight staff plugin");
		System.out.println(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
		
		SettingsManager.getInstance().saveDeath();
		
	}
	
	public void registerCommands() {
		
		getCommand("ss").setExecutor(new Screenshare());
		getCommand("sc").setExecutor(new StaffChat());
		getCommand("rtp").setExecutor(new RTP());
		getCommand("rtpxray").setExecutor(new RTPXray());
		getCommand("mc").setExecutor(new MuteChat());
		getCommand("staff").setExecutor(new Staff());
		getCommand("panic").setExecutor(new Panic());
		getCommand("dl").setExecutor(new DeathLookup());
		getCommand("v").setExecutor(new Vanish());
		getCommand("pompeii").setExecutor(new ConfigurationCommands());
		getCommand("request").setExecutor(new Request());
		
	}
	
	
	public void registerListeners() {
		
		Bukkit.getPluginManager().registerEvents(new StaffListener(), this);
		Bukkit.getPluginManager().registerEvents(new FreezeListener(), this);
		Bukkit.getPluginManager().registerEvents(new MuteChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new MassBanListener(), this);
		Bukkit.getPluginManager().registerEvents(new ItemManager(), this);
		Bukkit.getPluginManager().registerEvents(new PanicListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathLookupListener(), this);
		
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public void loadLang() {
		
	    File lang = new File(getDataFolder(), "lang.yml");
	    
	    if (!lang.exists()) {
	    	
	        try {
	        	
	            getDataFolder().mkdir();
	            lang.createNewFile();
	            InputStream defConfigStream = this.getResource("lang.yml");
	            
	            if (defConfigStream != null) {
	            	
	                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	                defConfig.save(lang);
	                Lang.setFile(defConfig);
	                return;
	                
	            }
	            
	        } catch(IOException e) {
	        	
	            e.printStackTrace();
	            this.setEnabled(false);
	            
	        }
	    }
	    
	    YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
	    
	    for(Lang item:Lang.values()) {
	    	
	        if (conf.getString(item.getPath()) == null) {
	        	
	            conf.set(item.getPath(), item.getDefault());
	            
	        }
	    }
	    
	    Lang.setFile(conf);
	    
	    plugin.LANG = conf;
	    
	    plugin.LANG_FILE = lang;
	    
	    try {
	    	
	        conf.save(getLangFile());
	        
	    } catch(IOException e) {
	    	
	        e.printStackTrace();
	        
	    }
	}
	
	
	public Pompeii getInstance() {
		
		return Pompeii.instance;
		
	}
	
	public YamlConfiguration getLang() {
		
	    return LANG;
	    
	}
	 
	public File getLangFile() {
		
	    return LANG_FILE;
	    
	}

}
