package override.studio.strlantian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import override.studio.strlantian.commands.CharacterCommand;
import override.studio.strlantian.listeners.Init;
import override.studio.strlantian.listeners.InventoryListeners;
import override.studio.strlantian.playercharacters.PlayerStorage;

import java.util.Collections;
import java.util.Objects;

public final class PlayerCharacters extends JavaPlugin
{
    public static JavaPlugin inst;

    @Override
    public void onEnable()
    {
        inst = this;
        saveConfig();
        System.out.println(ChatColor.GREEN + "Player Characters Plugin is enabled");

        Objects.requireNonNull(Bukkit.getPluginCommand("character")).setExecutor(new CharacterCommand());

        Objects.requireNonNull(Bukkit.getPluginCommand("character")).setTabCompleter(new CharacterCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("character")).setAliases(Collections.singletonList("cha"));

        Bukkit.getPluginManager().registerEvents(new Init(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListeners(), this);

        for(Player player : Bukkit.getOnlinePlayers())
        {
            Init.joinInit(player);
        }
    }

    @Override
    public void onDisable()
    {
        saveConfig();
        PlayerStorage.clearStorage();
        System.out.println(ChatColor.RED + "Player Characters Plugin is disabled");
    }
}
