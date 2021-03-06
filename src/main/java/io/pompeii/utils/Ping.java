package io.pompeii.utils;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ping {
	
	public static int pingPlayer(Player p) {
        try {
            // Building the version of the server in such a form we can use it
            // in NMS code.
            String bukkitversion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);
            // Getting craftplayer
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitversion + ".entity.CraftPlayer");
            // Invoking method getHandle() for the player
            Object handle = craftPlayer.getMethod("getHandle").invoke(p);
            // Getting field "ping" that holds player's ping obviously
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
            // Returning the ping
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            // Handle exceptions however you like, i chose to return value of
            // -1; since player's ping can't be -1.
            return -1;
        }
    }

}
