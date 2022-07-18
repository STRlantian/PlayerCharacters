package Override.Studio.STRlantian;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    public static JavaPlugin inst;

    @Override
    public void onEnable()
    {
        inst = this;
        saveConfig();
        System.out.println(ChatColor.GREEN + "Player Characters Plugin is enabled");
    }

    @Override
    public void onDisable()
    {
        saveConfig();
        System.out.println(ChatColor.RED + "Player Characters Plugin is disabled");
    }
}
