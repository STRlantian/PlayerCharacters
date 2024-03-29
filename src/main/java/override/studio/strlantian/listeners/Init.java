package override.studio.strlantian.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import override.studio.strlantian.playercharacters.ALocalisation;
import override.studio.strlantian.playercharacters.PlayerStorage;

import static override.studio.strlantian.PlayerCharacters.inst;
import static override.studio.strlantian.playercharacters.ALocalisation.CN;
import static override.studio.strlantian.playercharacters.ALocalisation.EN;
import static override.studio.strlantian.playercharacters.APCFactory.CHARENABLED;
import static override.studio.strlantian.playercharacters.APCFactory.NOINIT;

public final class Init implements Listener
{
    static FileConfiguration cfg = inst.getConfig();

    public static void joinInit(Player pl)
    {
        if(PlayerStorage.getStorage(pl).getEnable() == CHARENABLED)
        {
            PlayerStorage.addToStorageMap(pl);
        }
        if(!ALocalisation.checkLang(pl))
        {
            if(PlayerStorage.getStorage(pl).getEnable() == NOINIT)
            {
                String name = pl.getName().toLowerCase();

                pl.sendMessage(ChatColor.GRAY + "[玩家性格]本服务器开启了 玩家性格 插件, 输入 /character 进行了解");
                pl.sendMessage(ChatColor.GRAY + "[玩家性格]建议不要改名字因为插件要用 改了您数据就没");
                pl.sendMessage(ChatColor.GRAY + "[PlayerCharacters]This server enables PlayerCharacter Plugin. Learn more by /character");
                pl.sendMessage(ChatColor.GRAY + "[PlayerCharacters]Players are recommended not to change names bcs the plugin uses them");
                String cha = name + ".Characters";
                cfg.set(name, "Language");
                cfg.set(name, "Characters");
                cfg.set(name + ".Changed", NOINIT);
                cfg.set(name + ".isEnabled", NOINIT);

                cfg.set(cha, "Saturation");
                cfg.set(cha, "Energy");
                cfg.set(cha, "Health");
                cfg.set(cha, "Sanity");
                cfg.set(cha, "Darkness");
                cfg.set(cha, "Positivity");
                cfg.set(cha, "Braveness");
                cfg.set(cha, "Kindness");
                cfg.set(cha, "Patience");
                cfg.set(cha, "Height");
                pl.openInventory(ALocalisation.getLanguageInv());
            }
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        joinInit(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        PlayerStorage.removeFromStorageMap(e.getPlayer());
    }

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void ifCloseInventory(InventoryCloseEvent e)
    {
        InventoryView inv = e.getView();
        Player pl = (Player) e.getPlayer();
        String title = inv.getTitle();
        if (ALocalisation.LANGTITLE.equals(title))
        {
            pl.sendMessage(ChatColor.GRAY + "你仍然可以使用 /character language 来设置");
            pl.sendMessage(ChatColor.GRAY + "You can still use /character language to set language");
        }
    }

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void clickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView inv = pl.getOpenInventory();
        String title = inv.getTitle();
        PlayerStorage ps = PlayerStorage.getStorage(pl);

        if (ALocalisation.LANGTITLE.equals(title))
        {
            e.setCancelled(true);
            int slot = e.getSlot();
            switch (slot)
            {
                case 3 ->
                {
                    ps.setLanguage(CN);
                    pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                    pl.sendMessage(ChatColor.RED + "已更改语言到: 简体中文");
                    pl.closeInventory();
                }
                case 5 ->
                {
                    ps.setLanguage(EN);
                    pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                    pl.sendMessage(ChatColor.BLUE + "Language has been set to: English");
                    pl.closeInventory();
                }
            }
        }
    }
}
