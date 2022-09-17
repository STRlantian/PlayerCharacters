package override.studio.strlantian.playercharacters;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static override.studio.strlantian.PlayerCharacters.CN;
import static override.studio.strlantian.PlayerCharacters.EN;

public abstract class PCFactory
{
    private static final FileConfiguration CFG = override.studio.strlantian.PlayerCharacters.inst.getConfig(); //Config
    public static final int CHARNOTCHANGED = 0;
    public static final int CHARCHANGED = 1;
    public static final int CHARDISALED = 0;
    public static final int CHARENABLED = 1;
    public static final int NOINIT = 2;
    @SuppressWarnings("Deprecation")
    public static void setItemToInv(Inventory inv, int slot, ItemStack i, String name,
                                    Enchantment ench, int level, boolean isHideEnchant,
                                    String ... lore)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(List.of(lore));
        im.addEnchant(ench, level, true);
        if(isHideEnchant)
        {
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    @SuppressWarnings("Deprecation")
    public static void setItemToInv(Inventory inv, int slot, ItemStack i, String name, String ... lore)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(List.of(lore));
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    @SuppressWarnings("Deprecation")
    public static void setItemToInv(Inventory inv, int slot, ItemStack i, String name)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    public static void createItemForOption(Inventory inv, int ques, String name)
    {
        final ItemStack A = new ItemStack(Material.LIGHT_BLUE_WOOL, 1);
        final ItemStack B = new ItemStack(Material.YELLOW_WOOL, 1);
        final ItemStack C = new ItemStack(Material.PINK_WOOL, 1);

        switch(ques)
        {
            case 0 -> setItemToInv(inv, 29, A, ChatColor.BLUE + name);
            case 1 -> setItemToInv(inv, 31, B, ChatColor.YELLOW + name);
            case 2 -> setItemToInv(inv, 33, C, ChatColor.LIGHT_PURPLE + name);
        }
    }
    public static List<String> getPathList(Player pl)  //Character Path
    {
        List<String> list = new ArrayList<>(Collections.emptyList());

        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters.";
        String language = name + ".Language";
        String change = name + ".Changed";
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
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
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

}
