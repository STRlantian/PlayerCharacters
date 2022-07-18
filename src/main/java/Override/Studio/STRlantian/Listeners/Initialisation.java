package Override.Studio.STRlantian.Listeners;

import Override.Studio.STRlantian.Localisation;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import static Override.Studio.STRlantian.Main.inst;

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

        cfg.set(cha, "Sanity");
        cfg.set(cha, "Darkness");
        cfg.set(cha, "Positive");
        cfg.set(cha, "Braveness");
        cfg.set(cha, "Outgoing");
        cfg.set(cha, "Persistence");
        cfg.set(cha, "EzAnger");
        cfg.set(cha, "Energy");
        cfg.set(cha, "Health");
        cfg.set(cha, "High");
        pl.openInventory(INV);
    }

    @EventHandler
    public void ifCloseInit(InventoryCloseEvent e)
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
    public void chooseLanguage(InventoryClickEvent e)
    {
        Inventory getInv = e.getInventory();
        if(getInv.equals(Localisation.getLanguageInv()));
        {
            e.setCancelled(true);
            int slot = e.getSlot();
            Player pl = (Player) e.getWhoClicked();
            String name = pl.getName().toLowerCase();
            switch(slot)
            {
                case 3:
                {
                    cfg.set(name + ".Language", "CN");
                }
                case 5:
                {
                    cfg.set(name + ".Language", "EN");
                }
            }
        }
    }
}
