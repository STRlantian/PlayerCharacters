package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.PlayerCharacters.Localisation;
import OverrideStudio.STRlantian.PlayerCharacters.PlayerCharacters;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
import static OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters.INITITLEMAINCN;
import static OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters.INITITLEMAINEN;
import static OverrideStudio.STRlantian.PlayerCharacters.Localisation.LANGTITLE;

public final class Initialisation implements Listener
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
            String name = pl.getName().toLowerCase();

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
        switch(title)
        {
            case LANGTITLE->
            {
                pl.sendMessage(ChatColor.GRAY + "你仍然可以使用 /character language 来设置");
                pl.sendMessage(ChatColor.GRAY + "You can still use /character language to set language");
            }
            case INITITLEMAINCN, INITITLEMAINEN->
            {
                String lang = Localisation.getLanguage(pl);
                switch(lang)
                {
                    case "CN"->
                        pl.sendMessage(ChatColor.GRAY + "有时间再继续啊");
                    case "EN"->
                        pl.sendMessage(ChatColor.GRAY + "Don't forget to set them when you are ready");
                }
            }
        }
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView inv = pl.getOpenInventory();
        String title = inv.getTitle();
        if(title.contains("的性格页面")
        || title.contains("'s Character Page"))
        {
            if(title.contains(ChatColor.MAGIC.toString())
            && title.contains(ChatColor.GOLD.toString()))
            {
                e.setCancelled(true);
            }
        }

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
                        pl.setGameMode(GameMode.SURVIVAL);
                    }
                    case 5:
                    {
                        cfg.set(name + ".Language", "EN");
                        pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        pl.sendMessage(ChatColor.BLUE + "Language has been set to: English");
                        pl.setGameMode(GameMode.SURVIVAL);
                        pl.closeInventory();
                    }
                }
            }

            case INITITLEMAINCN, INITITLEMAINEN ->
            {
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 2->
                        PlayerCharacters.randCharacters(pl);
                    case 4->
                    case 6->
                }
            }
        }
    }
}
