package override.studio.strlantian.playercharacters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import override.studio.strlantian.PlayerCharacters;

import java.util.Objects;

public abstract class Localisation
{
    /*
    IS THERE ANYONE
    THAT HAS BETTER ADVICES ON LOCALISATION???
    */

    static FileConfiguration cfg = PlayerCharacters.inst.getConfig();

    public static final int CN = 0;
    public static final int EN = 1;
    public static final String LANGTITLE = "选语言 | Choose a Language";

    public static Inventory getLanguageInv()
    {
        Inventory inv = Bukkit.createInventory(null, 9, LANGTITLE);
        ItemStack CN = new ItemStack(Material.RED_WOOL);
        ItemStack EN = new ItemStack(Material.BLUE_WOOL);
        ItemMeta cn = Objects.requireNonNull(CN.getItemMeta());
        ItemMeta en = Objects.requireNonNull(EN.getItemMeta());
        cn.setDisplayName(ChatColor.RED + "Chinese | 简体中文");
        en.setDisplayName(ChatColor.BLUE + "English | 英语");
        CN.setItemMeta(cn);
        EN.setItemMeta(en);

        inv.setItem(3, CN);
        inv.setItem(5, EN);
        return inv;
    }

    public static void uDidntSetLanguage(Player pl)
    {
        pl.sendMessage(ChatColor.RED + "你貌似还没选择语言,请/character language");
        pl.sendMessage(ChatColor.RED + "It seems that you haven't set your language yet.Please /character language");
    }

    public static boolean checkLang(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        return lang == CN
                || lang == EN;
    }
}