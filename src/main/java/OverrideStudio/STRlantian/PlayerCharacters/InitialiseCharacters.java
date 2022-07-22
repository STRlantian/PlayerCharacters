package OverrideStudio.STRlantian.PlayerCharacters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

import static OverrideStudio.STRlantian.Main.inst;
import static OverrideStudio.STRlantian.PlayerCharacters.PlayerCharacters.getPathList;

public final class InitialiseCharacters
{
    static FileConfiguration cfg = inst.getConfig();
    static Inventory createInv(Player pl, String language)
    {
        String name = pl.getName();
        ItemStack consFlag = new ItemStack(Material.PAPER, 1);
        ItemStack featFlag = new ItemStack(Material.PAPER, 1);
        ItemMeta consim = consFlag.getItemMeta();
        ItemMeta featim = featFlag.getItemMeta();
        switch(language)
        {
            case "CN" ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + "的性格页面");
                consim.setDisplayName(ChatColor.MAGIC + "" + ChatColor.GOLD + name + "所属的基本性格");
                consim.setLore(Collections.singletonList(ChatColor.GOLD + "这是你的基本性格"));
                consim.addEnchant(Enchantment.DURABILITY, 1, true);
                consim.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                featim.setDisplayName(ChatColor.MAGIC + "" + ChatColor.GOLD + name + "所属的特殊性格");
                featim.setLore(Collections.singletonList(ChatColor.GOLD + "这是你的特殊性格"));
                featim.addEnchant(Enchantment.DURABILITY, 1, true);
                featim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                consFlag.setItemMeta(consim);
                featFlag.setItemMeta(featim);

                inv.setItem(0, consFlag);
                inv.setItem(9, featFlag);
                return inv;
            }
            case "EN" ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + "'s Character Page");
                consim.setDisplayName(ChatColor.MAGIC + "" + ChatColor.GOLD + name + "'s basical characters");
                consim.setLore(Collections.singletonList(ChatColor.GOLD + "These are your basical characters"));
                consim.addEnchant(Enchantment.DURABILITY, 1, true);
                consim.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                featim.setDisplayName(ChatColor.MAGIC + "" + ChatColor.GOLD + name + "'s special characters'");
                featim.setLore(Collections.singletonList(ChatColor.GOLD + "These are your special characters"));
                featim.addEnchant(Enchantment.DURABILITY, 1, true);
                featim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                consFlag.setItemMeta(consim);
                featFlag.setItemMeta(featim);

                inv.setItem(0, consFlag);
                inv.setItem(9, featFlag);
                return inv;
            }
        }
        return null;
    }

    public static final String INITITLEMAINCN = "初始化性格";
    public static final String INITITLEMAINEN = "Character Initialisation";
    public static void initialiseCharacters(Player pl)
    {
        final ItemStack RANDOM = new ItemStack(Material.APPLE, 1);
        final ItemStack TEST = new ItemStack(Material.PAPER, 1);
        final ItemStack CHOOSE = new ItemStack(Material.COBWEB, 1);
        ItemMeta rim = RANDOM.getItemMeta();
        rim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        rim.addEnchant(Enchantment.DURABILITY, 1, true);
        ItemMeta tim = TEST.getItemMeta();
        tim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tim.addEnchant(Enchantment.DURABILITY, 1, true);
        ItemMeta cim = CHOOSE.getItemMeta();
        cim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        cim.addEnchant(Enchantment.DURABILITY, 1, true);

        String name = pl.getName().toLowerCase();
        pl.setGameMode(GameMode.SPECTATOR);
        String language = Localisation.getLanguage(pl);
        switch(language)
        {
            case "CN"->
            {
                cfg.set(getPathList(pl).get(11), 0);

                pl.sendMessage(ChatColor.GREEN + "开始初始化性格...");
                Inventory invMain = Bukkit.createInventory(null, 1 * 9, INITITLEMAINCN);
                rim.setDisplayName(ChatColor.YELLOW + "随机抽取性格");
                tim.setDisplayName(ChatColor.YELLOW + "测试得出你的性格");
                cim.setDisplayName(ChatColor.YELLOW + "手动调整你的性格");
                rim.setLore(List.of(ChatColor.BLUE + "插件自动抽,抽完不能改"));
                tim.setLore(List.of(ChatColor.BLUE + "通过作者的测试,测完不能改"));
                cim.setLore(List.of(ChatColor.BLUE + "自己手动选性格,可能你自己更了解你自己"));
                RANDOM.setItemMeta(rim);
                TEST.setItemMeta(tim);
                CHOOSE.setItemMeta(cim);
                invMain.setItem(2, RANDOM);
                invMain.setItem(4, TEST);
                invMain.setItem(6, CHOOSE);

            }

            case "EN"->
            {

            }
        }
    }
}
