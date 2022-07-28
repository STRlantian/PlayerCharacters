package OverrideStudio.STRlantian;

import OverrideStudio.STRlantian.Commands.CharacterCommand;
import OverrideStudio.STRlantian.Listeners.InventoryListeners;
import OverrideStudio.STRlantian.Listeners.JoinInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
public final class Main extends JavaPlugin
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

        Bukkit.getPluginManager().registerEvents(new JoinInit(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListeners(), this);
    }

    @Override
    public void onDisable()
    {
        saveConfig();
        System.out.println(ChatColor.RED + "Player Characters Plugin is disabled");
    }
}
