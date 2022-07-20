package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.Localisation;
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

import static OverrideStudio.STRlantian.Localisation.LANGTITLE;
import static OverrideStudio.STRlantian.Main.inst;
import static OverrideStudio.STRlantian.PlayerCharacters.INITITLECN;
import static OverrideStudio.STRlantian.PlayerCharacters.INITITLEEN;

public final class Initialisation implements Listener
{
    FileConfiguration cfg = inst.getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        final Inventory INV = Localisation.getLanguageInv();
        Player pl = e.getPlayer();
        String name = pl.getName().toLowerCase();
        pl.sendMessage(ChatColor.GRAY + "本服务器开启了 玩家性格 插件, 输入 /character 进行了解");
        pl.sendMessage(ChatColor.GRAY + "This server enables PlayerCharacter Plugin. Learn more by /character");
        String cha = name + ".Characters";
        cfg.set(name, "Language");
        cfg.set(name, "Characters");
        cfg.set(name, "ChangingTime");

        cfg.set(cha, "Saturation");
        cfg.set(cha, "Energy");
        cfg.set(cha, "Health");
        cfg.set(cha, "Sanity");
        cfg.set(cha, "Darkness");
        cfg.set(cha, "Positive");
        cfg.set(cha, "Courage");
        cfg.set(cha, "Kindness");
        cfg.set(cha, "Patience");
        cfg.set(cha, "High");
        pl.openInventory(INV);
    }

    @EventHandler
    public void ifCloseLanguage(InventoryCloseEvent e)
    {
        Inventory invClo = e.getInventory();
        Player pl = (Player) e.getPlayer();
        if(invClo.equals(Localisation.getLanguageInv()))
        {
            pl.sendMessage(ChatColor.GRAY + "你仍然可以使用 /character language 来设置");
            pl.sendMessage(ChatColor.GRAY + "You can still use /character language to set language");
        }
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView inv = pl.getOpenInventory();
        String title = inv.getTitle();
        switch(title)
        {
            case LANGTITLE->
            {
                e.setCancelled(true);
                int slot = e.getSlot();
                String name = pl.getName().toLowerCase();
                switch(slot)
                {
                    case 3:
                    {
                        cfg.set(name + ".Language", "CN");
                        pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        pl.sendMessage(ChatColor.RED + "已更改语言到: 简体中文");
                        pl.closeInventory();
                    }
                    case 5:
                    {
                        cfg.set(name + ".Language", "EN");
                        pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        pl.sendMessage(ChatColor.BLUE + "Language has been set to: English");
                        pl.closeInventory();
                    }
                }
            }

            case INITITLECN, INITITLEEN->
            {
                e.setCancelled(true);
                int slot = e.getSlot();

            }
        }
    }
}
