package OverrideStudio.STRlantian.PlayerCharacters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class InitialiseCharacters
{
    public static final String INITITLEMAINCN = "初始化性格";
    public static final String INITITLEMAINEN = "Character Initialisation";
    @SuppressWarnings("Deprecation")
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

        String language = Localisation.getLanguage(pl);
        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "开始初始化性格...");
                Inventory invMain = Bukkit.createInventory(null, 9, INITITLEMAINCN);
                rim.setDisplayName(ChatColor.YELLOW + "纯随机抽取性格");
                tim.setDisplayName(ChatColor.YELLOW + "测试得出你的性格");
                cim.setDisplayName(ChatColor.YELLOW + "手动调整你的性格");
                rim.setLore(List.of(ChatColor.BLUE + "插件自动抽,抽完不能改"));
                tim.setLore(List.of(ChatColor.BLUE + "通过作者的测试,测完不能改"));
                cim.setLore(List.of(ChatColor.BLUE + "自己手动选一些性格,可能你自己更了解你自己"));
                RANDOM.setItemMeta(rim);
                TEST.setItemMeta(tim);
                CHOOSE.setItemMeta(cim);
                invMain.setItem(2, RANDOM);
                invMain.setItem(4, TEST);
                invMain.setItem(6, CHOOSE);
                pl.openInventory(invMain);
            }

            case "EN"->
            {
                pl.sendMessage(ChatColor.GREEN + "Start initialising...");
                Inventory invMain = Bukkit.createInventory(null, 9, INITITLEMAINCN);
                rim.setDisplayName(ChatColor.YELLOW + "Get all-random characters");
                tim.setDisplayName(ChatColor.YELLOW + "Test to get characters");
                cim.setDisplayName(ChatColor.YELLOW + "Choose your characters");
                rim.setLore(List.of(ChatColor.BLUE + "By the plugin. You can't change them"));
                tim.setLore(List.of(ChatColor.BLUE + "Take a test and get your characters"));
                cim.setLore(List.of(ChatColor.BLUE + "Choose your own characters"));
                RANDOM.setItemMeta(rim);
                TEST.setItemMeta(tim);
                CHOOSE.setItemMeta(cim);
                invMain.setItem(2, RANDOM);
                invMain.setItem(4, TEST);
                invMain.setItem(6, CHOOSE);
                pl.openInventory(invMain);
            }
        }
    }
}