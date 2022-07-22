package OverrideStudio.STRlantian.PlayerCharacters;

import OverrideStudio.STRlantian.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class PlayerCharacters
{
    static FileConfiguration cfg = Main.inst.getConfig();

    public static List<String> getPathList(Player pl)
    {
        List<String> list = new ArrayList<>(Collections.emptyList());

        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters.";
        String language = name + ".Language";
        String change = name + ".ChangingTime";

        String satu = cha + "Saturation";
        String ene = cha + "Energy";
        String heal = cha + "Health";
        String san = cha + "Sanity";
        String dark = cha + "Darkness";
        String posi = cha + "Positivity";
        String cour = cha + "Courage";
        String kind = cha + "Kindness";
        String pat = cha + "Patience";
        String high = cha + "Height";

        list.set(0, satu);
        list.set(1, ene);
        list.set(2, heal);
        list.set(3, san);
        list.set(4, dark);
        list.set(5, posi);
        list.set(6, cour);
        list.set(7, kind);
        list.set(8, pat);
        list.set(9, high);
        list.set(10, language);
        list.set(11, change);

        return list;
    }
    public static void uDidntInit(Player pl)
    {
        String language = Localisation.getLanguage(pl);
        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.RED + "你还没初始化你的性格");
                pl.sendMessage(ChatColor.RED + "使用/character init");
            }
            case "EN"->
            {
                pl.sendMessage(ChatColor.RED + "You haven't initialise your characters");
                pl.sendMessage(ChatColor.RED + "Please use /character init");
            }
        }
    }

    public static List<String> getCharacterList(Player pl)
    {
        List<String> list = new ArrayList<>(Collections.emptyList());
        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters";

        String hunger = (String) cfg.get(cha + "Hunger");
        String ener = (String) cfg.get(cha + "Energy");
        String health = (String) cfg.get(cha + "Health");
        String san = (String) cfg.get(cha + "Sanity");
        String dark = (String) cfg.get(cha + "Darkness");
        String pos = (String) cfg.get(cha + "Positive");
        String brave = (String) cfg.get(cha + "Braveness");
        String wat = (String) cfg.get(cha + "Kindness");
        String pers = (String) cfg.get(cha + "Persistence");
        String high = (String) cfg.get(cha + "High");

        list.set(0, hunger);
        list.set(1, ener);
        list.set(2, health);
        list.set(3, san);
        list.set(4, dark);
        list.set(5, pos);
        list.set(6, brave);
        list.set(7, wat);
        list.set(8, pers);
        list.set(9, high);
        return list;
    }

    static void createItem(Inventory inv, int slot, ItemStack i, String name, String lore)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Collections.singletonList(lore));
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }

    static void addChangingTime(Player pl)
    {
        String name = pl.getName().toLowerCase();
        String origin = Objects.requireNonNull((String) cfg.get(getPathList(pl).get(11)));
        int og = Integer.parseInt(origin);
        int a = og - 1;
        cfg.set(getPathList(pl).get(11), a);
    }
}
