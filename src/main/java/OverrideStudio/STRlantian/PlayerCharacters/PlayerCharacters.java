package OverrideStudio.STRlantian.PlayerCharacters;

import OverrideStudio.STRlantian.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class PlayerCharacters
{
    @SuppressWarnings("Deprecation")
    public static void createItem(Inventory inv, int slot, ItemStack i, String name, @Nullable String lore)
    {        //Create items for an ITEM IN **VIEWING PAGE**
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        if(!Objects.equals(lore, null))
        {
            im.setLore(Collections.singletonList(lore));
        }
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }

    static FileConfiguration cfg = Main.inst.getConfig(); //Config
    public enum Characters   //Characters Enum
    {
        SATURATION,
        ENERGY,
        HEALTH,
        SANITY,
        DARKNESS,
        POSITIVITY,
        BRAVENESS,
        KINDNESS,
        PATIENCE,
        HEIGHT
    }

    public static List<String> getPathList(Player pl)  //Character Path
    {
        List<String> list = new ArrayList<>(Collections.emptyList());

        String name = pl.getUniqueId().toString();
        String cha = name + ".Characters.";
        String language = name + ".Language";
        String change = name + ".ChangingTime";

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

        return list;
    }

    public static void uDidntInit(Player pl) //When someone hasn't initialised
    {
        Localisation.Languages language = Localisation.getLanguage(pl);
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

    public static List<String> getCharacterList(Player pl,@Nullable List<Integer> tempList) //Get Characters
    {
        List<String> list = new ArrayList<>(Collections.emptyList());
        int i = 0;

        if(tempList == null)
        {
            String name = pl.getUniqueId().toString();

            while(i < 10)
            {
                list.set(i, (String) cfg.get(getPathList(pl).get(i)));
                i++;
            }
            /*
            String hunger = (String) cfg.get(getPathList(pl).get(0));
            String ener = (String) cfg.get(getPathList(pl).get(1));
            String health = (String) cfg.get(getPathList(pl).get(2));
            String san = (String) cfg.get(getPathList(pl).get(3));
            String dark = (String) cfg.get(getPathList(pl).get(4));
            String pos = (String) cfg.get(getPathList(pl).get(5));
            String brave = (String) cfg.get(getPathList(pl).get(6));
            String kind = (String) cfg.get(getPathList(pl).get(7));
            String pat = (String) cfg.get(getPathList(pl).get(8));
            String high = (String) cfg.get(getPathList(pl).get(9));

            list.set(0, hunger);
            list.set(1, ener);
            list.set(2, health);
            list.set(3, san);
            list.set(4, dark);
            list.set(5, pos);
            list.set(6, brave);
            list.set(7, kind);
            list.set(8, pat);
            list.set(9, high);
             */
            return list;
        }


        while(i < 10)
        {
            list.set(i, String.valueOf(tempList.get(i)));
            i++;
        }
        /*
        list.set(0, String.valueOf(tempList.get(0)));
        list.set(1, String.valueOf(tempList.get(1)));
        list.set(2, String.valueOf(tempList.get(2)));
        list.set(3, String.valueOf(tempList.get(3)));
        list.set(4, String.valueOf(tempList.get(4)));
        list.set(5, String.valueOf(tempList.get(5)));
        list.set(6, String.valueOf(tempList.get(6)));
        list.set(7, String.valueOf(tempList.get(7)));
        list.set(8, String.valueOf(tempList.get(8)));
        list.set(9, String.valueOf(tempList.get(9)));
        */
        return list;
    }


    public static void addChangingTime(Player pl)
    {         //When the changing time changes
        String name = pl.getUniqueId().toString();
        String origin = Objects.requireNonNull((String) cfg.get(getPathList(pl).get(11)));
        int og = Integer.parseInt(origin);
        int a = og + 1;
        cfg.set(getPathList(pl).get(11), a);
    }
    public static void setCharacter(Player pl, Characters what, int value)
    {      //Set characters
        switch(what)
        {
            case SATURATION ->
                    cfg.set(getPathList(pl).get(0), value);
            case ENERGY ->
                    cfg.set(getPathList(pl).get(1), value);
            case HEALTH ->
                    cfg.set(getPathList(pl).get(2), value);
            case SANITY ->
                    cfg.set(getPathList(pl).get(3), value);
            case DARKNESS ->
                    cfg.set(getPathList(pl).get(4), value);
            case POSITIVITY ->
                    cfg.set(getPathList(pl).get(5), value);
            case BRAVENESS ->
                    cfg.set(getPathList(pl).get(6), value);
            case KINDNESS ->
                    cfg.set(getPathList(pl).get(7), value);
            case PATIENCE ->
                    cfg.set(getPathList(pl).get(8), value);
            case HEIGHT ->
                    cfg.set(getPathList(pl).get(9), value);
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
}
