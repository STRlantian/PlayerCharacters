package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.PlayerCharacters.Enums.Languages;
import OverrideStudio.STRlantian.PlayerCharacters.Localisation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import static OverrideStudio.STRlantian.Main.inst;
import static OverrideStudio.STRlantian.PlayerCharacters.Localisation.LANGTITLE;

public final class JoinInit implements Listener
{
    FileConfiguration cfg = inst.getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player pl = e.getPlayer();

        boolean hasLang = Localisation.checkLang(pl);
        if(!hasLang)
        {
            final Inventory INV = Localisation.getLanguageInv();
            String name = e.getPlayer().getUniqueId().toString();

            pl.sendMessage(ChatColor.GRAY + "本服务器开启了 玩家性格 插件, 输入 /character 进行了解");
            pl.sendMessage(ChatColor.GRAY + "This server enables PlayerCharacter Plugin. Learn more by /character");
            String cha = name + ".Characters";
            cfg.set(name, "Language");
            cfg.set(name, "Characters");
            cfg.set(name + ".ChangingTime", 0);

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
            pl.openInventory(INV);
        }
    }

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void ifCloseInventory(InventoryCloseEvent e)
    {
        InventoryView inv = e.getView();
        Player pl = (Player) e.getPlayer();
        String title = inv.getTitle();
        if (LANGTITLE.equals(title))
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

        if (LANGTITLE.equals(title))
        {
            e.setCancelled(true);
            int slot = e.getSlot();
            String name = pl.getUniqueId().toString();
            switch (slot)
            {
                case 3:
                {
                    cfg.set(name + ".Language", Languages.CN);
                    pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                    pl.sendMessage(ChatColor.RED + "已更改语言到: 简体中文");
                    pl.closeInventory();
                    break;
                }
                case 5:
                {
                    cfg.set(name + ".Language", Languages.EN);
                    pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                    pl.sendMessage(ChatColor.BLUE + "Language has been set to: English");
                    pl.closeInventory();
                    break;
                }
            }
        }
    }
}
