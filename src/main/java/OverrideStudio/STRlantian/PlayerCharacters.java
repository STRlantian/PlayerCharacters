package OverrideStudio.STRlantian;

import org.bukkit.*;
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
import java.util.Objects;

public final class PlayerCharacters
{
    static FileConfiguration cfg = Main.inst.getConfig();

    static List<String> getPathList(Player pl)
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
        String wat = (String) cfg.get(cha + "Water");
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
    public static List<String> getCharacterList(Player pl)
    {
        List<String> list = new java.util.ArrayList<>(Collections.emptyList());

        List<String> pList = getPathList(pl);
        list.set(0, pList.get(0));
        list.set(1, pList.get(1));
        list.set(2, pList.get(2));
        list.set(3, pList.get(3));
        list.set(4, pList.get(4));
        list.set(5, pList.get(5));
        list.set(6, pList.get(6));
        list.set(7, pList.get(7));
        list.set(8, pList.get(8));
        list.set(9, pList.get(9));
        return list;
    }

    public static void initialiseCharacters(Player pl)
    {
        String name = pl.getName().toLowerCase();
        pl.setGameMode(GameMode.SPECTATOR);
        String language = Localisation.getLanguage(pl);

        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "");
            }

            case "EN"->
            {

            }
        }
    }

    private static Inventory createInv(Player pl, String language)
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

    private static void createItem(Inventory inv, int slot, ItemStack i, String name, String lore)
    {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Collections.singletonList(lore));
        i.setItemMeta(im);
        inv.setItem(slot, i);
    }
    public static void viewCharacters(Player pl)
    {
        List<String> list = getCharacterList(pl);
        String language = Localisation.getLanguage(pl);
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        Inventory inv = Objects.requireNonNull(createInv(pl, language));

        ItemStack hun = new ItemStack(Material.COOKED_CHICKEN, 1);
        ItemStack ene = new ItemStack(Material.SUGAR, 1);
        ItemStack hea = new ItemStack(Material.GOLDEN_CARROT, 1);
        ItemStack san = new ItemStack(Material.NAUTILUS_SHELL, 1);
        ItemStack dar = new ItemStack(Material.BLACK_WOOL, 1);
        ItemStack pos = new ItemStack(Material.SLIME_BALL, 1);
        ItemStack bra = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemStack wat = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack per = new ItemStack(Material.GOLDEN_BOOTS, 1);
        ItemStack hig = new ItemStack(Material.FEATHER , 1);

        switch(language)
        {
            case "CN"->
            {
                switch (list.get(0))
                {
                    case "0" ->
                        createItem(inv, 1, hun,
                                ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求不高",
                                ChatColor.DARK_GREEN + "饱食度上限少");
                    case "1" ->
                        createItem(inv, 1, hun,
                                ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求正常",
                                ChatColor.DARK_GREEN + "饱食度上限中");
                    case "2" ->
                        createItem(inv, 1, hun,
                                ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求较高",
                                ChatColor.DARK_GREEN + "饱食度上限大");
                }
                switch (list.get(1))
                {
                    case "0" ->
                        createItem(inv, 2, ene,
                                ChatColor.DARK_BLUE + "能量值- 你体质不好",
                                ChatColor.DARK_BLUE + "能量上限低");
                    case "1" ->
                        createItem(inv, 2, ene,
                                ChatColor.DARK_BLUE + "能量值- 你体质正常",
                                ChatColor.DARK_BLUE + "能量上限适中");
                    case "2" ->
                        createItem(inv, 2, ene,
                                ChatColor.DARK_BLUE + "能量值- 你体制超群",
                                ChatColor.DARK_BLUE + "能量上限高");
                }
                switch (list.get(2))
                {
                    case "0" ->
                        createItem(inv, 3, hea,
                                ChatColor.DARK_RED + "健康值- 你不健康",
                                ChatColor.DARK_RED + "健康值上限低");
                    case "1" ->
                            createItem(inv, 3, hea,
                                    ChatColor.DARK_RED + "健康值- 你挺健康",
                                    ChatColor.DARK_RED + "健康值上限中");
                    case "2" ->
                        createItem(inv, 3, hea,
                                ChatColor.DARK_RED + "健康值- 你很健康",
                                ChatColor.DARK_RED + "健康值上限高");
                }
                switch (list.get(3)) //sanity
                {
                    case "0" -> {
                        createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你有时不清醒",
                                ChatColor.AQUA + "概率性反胃和急迫效果");
                    }
                    case "1" -> {
                        createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你比较清醒",
                                ChatColor.GRAY + "不受影响");
                    }
                    case "2" -> {
                        createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你沉着冷静",
                                ChatColor.AQUA + "抗性和速度效果");
                    }
                }
                switch (list.get(4)) //darkness
                {
                    case "0" -> {
                        pl.sendMessage(ChatColor.BLACK + "5- 你怕黑");
                        pl.sendMessage(ChatColor.BLACK + "   你在黑暗中受到速度和虚弱效果");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.BLACK + "5- 你不怕黑");
                        pl.sendMessage(ChatColor.BLACK + "   你在黑暗中不受影响");
                    }
                }
                switch (list.get(5)) //positivity
                {
                    case "0" -> {
                        pl.sendMessage(ChatColor.YELLOW + "6- 你乐观向上");
                        pl.sendMessage(ChatColor.YELLOW + "   你在健康值低的时候可以生命恢复");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.YELLOW + "6- 你较为悲观");
                        pl.sendMessage(ChatColor.YELLOW + "   你在健康值低的时候可以抗性提升");
                    }
                }
                switch (list.get(6)) //braveness
                {
                    case "0" -> {
                        pl.sendMessage(ChatColor.RED + "7- 你超勇的(bushi");
                        pl.sendMessage(ChatColor.RED + "   当你拿剑或斧的时候提升力量");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.RED + "7- 你很逊(bushi");
                        pl.sendMessage(ChatColor.RED + "   当你持盾牌的时候提升速度");
                    }
                }
                switch (list.get(7)) //water
                {
                    case "0" -> {
                        pl.sendMessage(ChatColor.BLUE + "8- 你水性不好");
                        pl.sendMessage(ChatColor.BLUE + "   在水中憋气时间较短,速度慢且获得力量");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.BLUE + "8- 你只是会游泳");
                        pl.sendMessage(ChatColor.BLUE + "   你在水中不受影响");
                    }
                    case "2" -> {
                        pl.sendMessage(ChatColor.BLUE + "8- 你就是个水猴子(bushi");
                        pl.sendMessage(ChatColor.BLUE + "   在水中憋气时间短,速度快");
                    }
                }
                switch (list.get(8)) //persistence
                {
                    case "0" -> {
                        pl.sendMessage(ChatColor.LIGHT_PURPLE + "9- 你很有耐心,不急不躁");
                        pl.sendMessage(ChatColor.LIGHT_PURPLE + "   在一些情况下(自己猜)缓慢和抗性");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.LIGHT_PURPLE + "9- 你没有耐心,易怒");
                        pl.sendMessage(ChatColor.LIGHT_PURPLE + "   在一些情况下(自己猜)急迫,但能量消耗快");
                    }
                }
                switch (list.get(9)) {
                    case "0" -> {
                        pl.sendMessage(ChatColor.DARK_RED + "10- 你恐高");
                        pl.sendMessage(ChatColor.DARK_RED + "    你会在高处获得反胃和速度");
                    }
                    case "1" -> {
                        pl.sendMessage(ChatColor.DARK_RED + "10- 你不恐高");
                        pl.sendMessage(ChatColor.DARK_RED + "    不受影响");
                    }
                }
            }
        }
    }
}
