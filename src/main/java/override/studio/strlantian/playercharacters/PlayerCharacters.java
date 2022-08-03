package override.studio.strlantian.playercharacters;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import override.studio.strlantian.Main;
import override.studio.strlantian.playercharacters.enums.Characters;
import override.studio.strlantian.playercharacters.enums.Languages;
import override.studio.strlantian.playercharacters.enums.QuestionOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class PlayerCharacters
{
    static FileConfiguration cfg = Main.inst.getConfig(); //Config
    @SuppressWarnings("Deprecation")
    public static void createItem(Inventory inv, int slot, ItemStack i, String name, String ... lore)
    {        //Create items for an ITEM IN **VIEWING PAGE**
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(List.of(lore));
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    @SuppressWarnings("Deprecation")
    public static void createItem(Inventory inv, int slot, ItemStack i, String name)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    public static void createItem(Inventory inv, String num, String lore)
    {
        final ItemStack Q = new ItemStack(Material.BOOK, 1);
        createItem(inv, 13, Q, "(" + num + "/5)", ChatColor.GREEN + lore);
    }
    @SuppressWarnings("Deprecation")
    public static void createItem(Inventory inv, QuestionOptions ques, String name)
    {
        final ItemStack A = new ItemStack(Material.LIGHT_BLUE_WOOL, 1);
        final ItemStack B = new ItemStack(Material.YELLOW_WOOL, 1);
        final ItemStack C = new ItemStack(Material.PINK_WOOL, 1);

        switch(ques)
        {
            case OPIA -> createItem(inv, 29, A, ChatColor.BLUE + name);
            case OPIB -> createItem(inv, 31, B, ChatColor.YELLOW + name);
            case OPIC -> createItem(inv, 33, C, ChatColor.LIGHT_PURPLE + name);
        }
    }
    public static List<String> getPathList(Player pl)  //Character Path
    {
        List<String> list = new ArrayList<>(Collections.emptyList());

        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters.";
        String language = name + ".Language";
        String change = name + ".ChangingTime";
        String enable = name + ".isEnabled";

        String satu = cha + "Saturation";
        String ene = cha + "Energy";
        String heal = cha + "Health";
        String san = cha + "Sanity";
        String dark = cha + "Darkness";
        String posi = cha + "Positivity";
        String cour = cha + "Braveness";
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
        list.set(12, enable);

        return list;
    }
    public static void uDidntInit(Player pl) //When someone hasn't initialised
    {
        Languages language = Localisation.getLanguage(pl);
        switch(language)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.RED + "你还没初始化你的性格");
                pl.sendMessage(ChatColor.RED + "使用/character init");
                
            }
            case EN->
            {
                pl.sendMessage(ChatColor.RED + "You haven't initialise your characters");
                pl.sendMessage(ChatColor.RED + "Please use /character init");
                
            }
        }
    }
    public static List<Integer> getCharacterList(Player pl)
    {
        List<Integer> list = new ArrayList<>(Collections.emptyList());
        int i = 0;

        while(i < 10)
        {
            list.set(i, (Integer) cfg.get(getPathList(pl).get(i)));
            i++;
        }
        return list;
    }
    public static List<String> getCharacterList(Player pl, List<Integer> tempList) //Get Characters
    {
        List<String> list = new ArrayList<>(Collections.emptyList());
        int i = 0;

        while(i < 10)
        {
            list.set(i, String.valueOf(tempList.get(i)));
            i++;
        }
        return list;
    }

    public static void addChangingTime(Player pl)
    {         //When the changing time changes
        String name = pl.getName().toLowerCase();
        String origin = Objects.requireNonNull((String) cfg.get(getPathList(pl).get(11)));
        int og = Integer.parseInt(origin);
        int a = og + 1;
        cfg.set(getPathList(pl).get(11), a);
    }

    public static void setCharacter(Player pl, Characters what, int value)
    {      //Set characters
        switch(what)
        {
            case SATURATION -> cfg.set(getPathList(pl).get(Characters.SATURATION.ordinal()), value);
            case ENERGY -> cfg.set(getPathList(pl).get(Characters.ENERGY.ordinal()), value);
            case HEALTH -> cfg.set(getPathList(pl).get(Characters.HEALTH.ordinal()), value);
            case SANITY -> cfg.set(getPathList(pl).get(Characters.SANITY.ordinal()), value);
            case DARKNESS -> cfg.set(getPathList(pl).get(Characters.DARKNESS.ordinal()), value);
            case POSITIVITY -> cfg.set(getPathList(pl).get(Characters.POSITIVITY.ordinal()), value);
            case BRAVENESS -> cfg.set(getPathList(pl).get(Characters.BRAVENESS.ordinal()), value);
            case KINDNESS -> cfg.set(getPathList(pl).get(Characters.KINDNESS.ordinal()), value);
            case PATIENCE -> cfg.set(getPathList(pl).get(Characters.PATIENCE.ordinal()), value);
            case HEIGHT -> cfg.set(getPathList(pl).get(Characters.HEIGHT.ordinal()), value);
        }
    }

    public static void setCharacter(Player pl, List<Integer> list)
    {
        int i = 0;
        while(i < 10)
        {
            cfg.set(getPathList(pl).get(i), list.get(i));
            i++;
        }
    }

    public static void setEnable(Player pl, boolean whatNow)
    {
        cfg.set(getPathList(pl).get(Characters.ENABLED.ordinal()), whatNow);
        Languages lang = Localisation.getLanguage(pl);
        if (whatNow)
        {
            switch (lang)
            {
                case CN -> pl.sendMessage(ChatColor.GREEN + "你的性格已启用");
                case EN -> pl.sendMessage(ChatColor.GREEN + "Your characters have been enabled");
            }
        }
        else
        {
            switch (lang)
            {
                case CN -> pl.sendMessage(ChatColor.RED + "你的性格已经删除");
                case EN -> pl.sendMessage(ChatColor.RED + "Your characters has been deleted");
            }
        }
    }

    public static boolean getEnable(Player pl)
    {
        return Boolean.parseBoolean((String) cfg.get(getPathList(pl).get(Characters.ENABLED.ordinal())));
    }
}