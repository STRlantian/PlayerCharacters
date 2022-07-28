package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters;
import OverrideStudio.STRlantian.PlayerCharacters.Localisation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters.*;

public final class InventoryListeners implements Listener
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
            Localisation.Languages lang = Localisation.getLanguage(pl);
            pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            switch(lang)
            {
                case CN->
                        pl.sendMessage(ChatColor.RED + "你取消了测试");
                case EN->
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
                List<Integer> tempList = getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 2->
                        randCharacters(pl, tempList);
                    case 4->
                        testCharactersPre(pl);
                    case 6->
                        chooseCharacters(pl);
                }
            }

            case ASKTITLECN, ASKTITLEEN ->
            {
                List<Integer> tempList = getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();

                switch (slot)
                {
                    case 2->
                    {
                        InitialiseCharacters.testCharacters(pl, tempList);
                    }
                    case 6->
                        pl.closeInventory();
                }
            }
        }
    }
}
