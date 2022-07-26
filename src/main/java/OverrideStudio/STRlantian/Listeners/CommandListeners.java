package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.PlayerCharacters.Localisation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;

import static OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters.*;

public class CommandListeners implements Listener
{
    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e)
    {
        Player pl = (Player) e.getPlayer();
        InventoryView inv = e.getView();
        String title = inv.getTitle();
        if(Objects.equals(title, ASKTITLECN)
        || Objects.equals(title, ASKTITLEEN))
        {
            String lang = Localisation.getLanguage(pl);
            pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            switch(lang)
            {
                case "CN"->
                        pl.sendMessage(ChatColor.RED + "你取消了测试");
                case "EN"->
                        pl.sendMessage(ChatColor.RED + "You cancelled the test");
            }
        }
    }

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onClickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView inv = e.getView();
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
            case INITITLEMAINCN, INITITLEMAINEN ->
            {
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 2->
                        randCharacters(pl);
                    case 4->
                        testCharacters(pl);
                    case 6->
                        chooseCharacters(pl);
                }
            }

            case TESTINGCN, TESTINGEN ->
            {
                e.setCancelled(true);
                int slot = e.getSlot();
                switch (slot)
                {

                }
            }
        }
    }
}
