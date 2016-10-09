package io.pompeii.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
	
    PREFIX("PREFIX", "&7&l[&6Staff&7&l] \u226B "),
    STAFF_CHAT_PREFIX("STAFF-CHAT-PREFIX", "&7&l[&6Staff Chat&7&l] "),
    NO_PERMISSION("NO-PERMISSION", "&6You do not have the permission to perform this action!"),
    STAFF_CHAT_ENABLED("STAFF-CHAT-ENABLED", "&6\u226B has been enabled."),
    STAFF_CHAT_DISABLED("STAFF-CHAT-DISABLED", "&6\u226B has been disabled."),
    STAFF_ITEM_DROP_EVENT("STAFF-ITEM-DROP-EVENT", "&6You can't drop item's while in staff mode!"),
    STAFF_ITEM_PICKUP_EVENT("STAFF-ITEM-PICKUP-EVENT", "&6\u226B You can't pickup item's while in staff mode!"),
    STAFF_BLOCK_BREAK_EVENT("STAFF-BLOCK-BREAK-EVENT", "&6\u226B You can't break blocks while in staff mode!"),
    STAFF_BLOCK_PLACE_EVENT("STAFF-BLOCK-PLACE-EVENT", "&6\u226B You can't place blocks while in staff mode!"),
    STAFF_FREEZE_MESSAGE("STAFF-FREEZE-MESSAGE", "&6You have frozen &7&l%p%&6. A message has been sent to them to join teamspeak!"),
	STAFF_UNFREEZE_MESSAGE("STAFF-UNFREEZE-MESSAGE", "&6You have unfrozen &7&l%p%&6. A message has been sent to them."),
	PLAYER_FREEZE_MESSAGE("PLAYER-FREEZE-MESSAGE", "&6&lYou are currently frozen!"),
	PLAYER_UNFREEZE_MESSAGE("PLAYER-UNFREEZE-MESSAGE", "&6&lYou have been unfrozen, you are now free to go. Thanks for cooperating!"),
	STAFF_PUBLIC_UNFREEZE_MESSAGE("STAFF-PUBLIC-UNFREEZE-MESSAGE", "&7&l%fp% &6has been unfrozen by %p%"),
	TEAMSPEAK_IP("TEAMSPEAK-IP", "&6ts.test.com"),
	PLAYER_FREEZE_DROP_ITEM("PLAYER-FREEZE-DROP-ITEM", "&6You can't drop items while frozen!"),
	PLAYER_FREEZE_BLOCK_PLACE("PLAYER-FREEZE-BLOCK-PLACE", "&6You can't place blocks while frozen!"),
	PLAYER_FREEZE_BLOCK_BREAK("PLAYER-FREEZE-BLOCK-BREAK", "&6You can't break blocks while frozen!"),
	PLAYER_DAMAGER_MESSAGE("PLAYER-DAMAGER-MESSAGE", "&6You can't damage %p%, they are currently frozen!"),
	SENDER_MUTE_CHAT_MESSAGE("SENDER-MUTE-CHAT-MESSAGE", "&6You have muted chat!"),
	SENDER_UNMUTE_CHAT_MESSAGE("SENDER-UNMUTE-CHAT-MESASGE", "&6You have unmuted chat!"),
	BROADCAST_MUTE_CHAT_MESSAGE("BROADCAST-MUTE-CHAT-MESSAGE", "&7&l%p% &6has muted chat!"),
	BROADCAST_UNMUTE_CHAT_MESSAGE("BOARDCAST-UNMUTE-CHAT-MESASGE", "&7&l%p% &6has umuted chat!"),
	PLAYER_CHAT_IS_MUTED("PLAYER-CHAT-IS-MUTED", "&6The chat is currently muted! Try again later."),
	PLAYER_RANDOM_TELEPORTED_TO("PLAYER-RANDOM-TELEPORTED-TO", "&6You have randomly teleported to %p%"),
	NOT_ENOUGH_PLAYERS("NOT-ENOUGH-PLAYERS", "&6There are currently not enough players to perform this action!"),
	STAFF_MODE_ENABLED("STAFF-MODE-ENABLED", "&6Staff Mode Enabled"),
	STAFF_MODE_DISABLED("STAFF-MODE-DISABLED", "&6Staff Mode Disabled"),
	PLAYER_INVENTORY_RESTORE("PLAYER-INVENTORY-RESTORE", "&6Your inventory has been restored by %p%."),
	STAFF_INVENTORY_RESTORE("STAFF-INVENTORY-RESTORE", "&6You have restored %p%'s inventory."),
	MASS_BAN_STOPPED("MASS-BAN-STOPPED", "&6Woah! You can't /ban that quickly!"),
	PLAYER_PANIC_MESSAGE("PLAYER-PANIC-MESSAGE", "&6You have paniced, you will stay paniced for 1 hour."),
	STAFF_PANIC_MESSAGE("STAFF-PANIC-MESSAGE", "&7&l%p% &6has paniced, please assist him."),
	PLAYER_UNPANIC_MESSAGE("PLAYER-UNPANIC-MESSAGE", "&6You have unpaniced."),
	STAFF_UNPANIC_MESSAGE("STAFF-UNPANIC-MESSAGE", "&7&l%p% &6has unpaniced."),
	VANISH_ENABLED("VANISH-ENABLED", "&6You are now vanished."),
	VANISH_DISABLED("VANISH-DISABLED", "&6You are no longer vanished. "),
	REQUEST_SUCCESSFUL("REQUEST-SUCCESSFULL", "&6Your request has been sent we will contact you back soon!"),
	REQUEST_COOLDOWN("REQUEST-COOLDOWN", "&6You are currently on cooldown. You can re-request in %time%"),
	SILENT_CHEST_OPEN("SILENT-CHEST-OPEN", "&6You have silently opened the chest."),
	QUIT_BAN_COMMAND("QUIT-BAN-COMMAND", "/ban %p% Quit while frozen");
	
 
    private String path;
    private String def;
    private static YamlConfiguration LANG;
 
    /**
    * Lang enum constructor.
    * @param path The string path.
    * @param start The default string.
    */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }
 
    /**
    * Set the {@code YamlConfiguration} to use.
    * @param config The config to set.
    */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }
 
    @Override
    public String toString() {
        if (this == PREFIX)
            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }
 
    /**
    * Get the default value of the path.
    * @return The default value of the path.
    */
    public String getDefault() {
        return this.def;
    }
 
    /**
    * Get the path to the string.
    * @return The path to the string.
    */
    public String getPath() {
        return this.path;
    }
}
