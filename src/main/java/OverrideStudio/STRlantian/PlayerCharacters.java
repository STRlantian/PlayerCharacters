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

import static java.lang.Integer.valueOf;

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

    private static void addTime(Player pl)
    {
        String name = pl.getName().toLowerCase();
        String origin = Objects.requireNonNull((String) cfg.get(getPathList(pl).get(11)));
        int og = Integer.parseInt(origin);
        int a = og - 1;
        cfg.set(getPathList(pl).get(11), a);
    }
    public static final String INITITLECN = "初始化性格";
    public static final String INITITLEEN = "Initialising Characters";
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
                Inventory inv = Bukkit.createInventory(null, 6 * 9, INITITLECN);
                rim.setDisplayName(ChatColor.YELLOW + "随机抽取性格");
                tim.setDisplayName(ChatColor.YELLOW + "测试得出你的性格");
                cim.setDisplayName(ChatColor.YELLOW + "手动调整你的性格");
            }

            case "EN"->
            {

            }
        }
    }

    private static List<String> getCharacterList(Player pl)
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
        ItemStack kin = new ItemStack(Material.EGG, 1);
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
                    case "0" ->
                            createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你有时不清醒",
                                ChatColor.AQUA + "概率性反胃和急迫效果");

                    case "1" ->
                            createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你比较清醒",
                                ChatColor.GRAY + "不受影响");

                    case "2" ->
                            createItem(inv, 10, san,
                                ChatColor.AQUA + "理智- 你沉着冷静",
                                ChatColor.AQUA + "抗性和速度效果");

                }
                switch (list.get(4)) //darkness
                {
                    case "0" ->
                            createItem(inv, 11, dar,
                                ChatColor.BLACK + "黑暗- 你怕黑",
                                ChatColor.BLACK + "在黑暗中速度和虚弱效果");
                    case "1" ->
                            createItem(inv, 11, dar,
                                ChatColor.BLACK + "黑暗- 你不怕黑",
                                ChatColor.GRAY + "不受影响");
                }
                switch (list.get(5)) //positivity
                {
                    case "0" ->
                            createItem(inv, 12, pos,
                                    ChatColor.YELLOW + "态度- 你很乐观",
                                    ChatColor.YELLOW + "健康值低时力量效果");
                    case "1" ->
                            createItem(inv, 12, pos,
                                    ChatColor.YELLOW + "态度- 你较悲观",
                                    ChatColor.YELLOW + "健康值低时抗性效果");
                }
                switch (list.get(6)) //braveness
                {
                    case "0" ->
                            createItem(inv, 13, bra,
                                ChatColor.RED + "勇气- 你超勇的(bushi",
                                ChatColor.RED + "持剑时力量效果");
                    case "1" ->
                            createItem(inv, 13, bra,
                                ChatColor.RED + "勇气- 你很逊的啦(bushi",
                                ChatColor.RED + "举盾时速度效果");
                }
                switch (list.get(7)) //Kindness
                {
                    case "0" ->
                            createItem(inv, 14, kin,
                                    ChatColor.BLUE + "善良- 我劝你善良",
                                    ChatColor.BLUE + "健康值少和力量效果");
                    case "1" ->
                            createItem(inv, 14, kin,
                                ChatColor.BLUE + "善良- 你是个正常人",
                                ChatColor.BLUE + "钓鱼时幸运效果(我也不知道为什么我想起来这个");
                    case "2" ->
                            createItem(inv, 14, kin,
                                    ChatColor.BLUE + "善良- 你很善良",
                                    ChatColor.BLUE + "健康值少和抗性效果");
                }
                switch (list.get(8)) //persistence
                {
                    case "0" ->
                            createItem(inv, 15, per,
                                ChatColor.LIGHT_PURPLE + "耐心- 你不仅耐心还坚持不懈",
                                ChatColor.LIGHT_PURPLE + "一些情况下缓慢和抗性效果");
                    case "1" ->
                            createItem(inv, 15, per,
                                    ChatColor.LIGHT_PURPLE + "耐心- 你很急躁,经常没耐心",
                                    ChatColor.LIGHT_PURPLE + "一些情况下急迫和消耗能量快效果");
                }
                switch (list.get(9))
                {
                    case "0" ->
                            createItem(inv, 16, hig,
                                ChatColor.RED + "恐高- 你恐高",
                                ChatColor.RED + "高处反胃和速度效果");
                    case "1" ->
                            createItem(inv, 16, hig,
                                    ChatColor.RED + "恐高- 你不恐高",
                                    ChatColor.GRAY + "不受影响");
                }
            }

            case "EN"->
            {
                switch (list.get(0))
                {
                    case "0" ->
                            createItem(inv, 1, hun,
                                    ChatColor.DARK_GREEN + "Saturability- Low",
                                    ChatColor.DARK_GREEN + "Low saturability limit");
                    case "1" ->
                            createItem(inv, 1, hun,
                                    ChatColor.DARK_GREEN + "Saturability- Normal",
                                    ChatColor.DARK_GREEN + "Normal saturability limit");
                    case "2" ->
                            createItem(inv, 1, hun,
                                    ChatColor.DARK_GREEN + "Saturability- High",
                                    ChatColor.DARK_GREEN + "High saturability limit");
                }
                switch (list.get(1))
                {
                    case "0" ->
                            createItem(inv, 2, ene,
                                    ChatColor.DARK_BLUE + "Energy- Low",
                                    ChatColor.DARK_BLUE + "Low energy limit");
                    case "1" ->
                            createItem(inv, 2, ene,
                                    ChatColor.DARK_BLUE + "Energy- Normal",
                                    ChatColor.DARK_BLUE + "Normal energy limit");
                    case "2" ->
                            createItem(inv, 2, ene,
                                    ChatColor.DARK_BLUE + "Energy- High",
                                    ChatColor.DARK_BLUE + "High energy limit");
                }
                switch (list.get(2))
                {
                    case "0" ->
                            createItem(inv, 3, hea,
                                    ChatColor.DARK_RED + "Health- Low",
                                    ChatColor.DARK_RED + "Low health limit");
                    case "1" ->
                            createItem(inv, 3, hea,
                                    ChatColor.DARK_RED + "Health- Normal",
                                    ChatColor.DARK_RED + "Normal health limit");
                    case "2" ->
                            createItem(inv, 3, hea,
                                    ChatColor.DARK_RED + "Health- High",
                                    ChatColor.DARK_RED + "High health limit");
                }
                switch (list.get(3)) //sanity
                {
                    case "0" ->
                            createItem(inv, 10, san,
                                    ChatColor.AQUA + "Sanity- You are not that clear",
                                    ChatColor.AQUA + "[Porobobilitic]Nausea and haste");

                    case "1" ->
                            createItem(inv, 10, san,
                                    ChatColor.AQUA + "Sanity- You are clear",
                                    ChatColor.GRAY + "No effects");

                    case "2" ->
                            createItem(inv, 10, san,
                                    ChatColor.AQUA + "Sanity- You can calm down anytime",
                                    ChatColor.AQUA + "Resistance and slowness");

                }
                switch (list.get(4)) //darkness
                {
                    case "0" ->
                            createItem(inv, 11, dar,
                                    ChatColor.BLACK + "Darkness- You are afraid of darkness",
                                    ChatColor.BLACK + "[In darkness]Weakness and swiftness");
                    case "1" ->
                            createItem(inv, 11, dar,
                                    ChatColor.BLACK + "Darkness- You are not afraid of darkness",
                                    ChatColor.GRAY + "No effects");
                }
                switch (list.get(5)) //positivity
                {
                    case "0" ->
                            createItem(inv, 12, pos,
                                    ChatColor.YELLOW + "Attitude- You are always positive",
                                    ChatColor.YELLOW + "[When low health]Regeneration");
                    case "1" ->
                            createItem(inv, 12, pos,
                                    ChatColor.YELLOW + "Attitude- You are a little negative",
                                    ChatColor.YELLOW + "[When low health]Resistance");
                }
                switch (list.get(6)) //braveness
                {
                    case "0" ->
                            createItem(inv, 13, bra,
                                    ChatColor.RED + "Courage- You are kinda brave",
                                    ChatColor.RED + "[When holding a sword]Strength");
                    case "1" ->
                            createItem(inv, 13, bra,
                                    ChatColor.RED + "Courage- You are not that brave",
                                    ChatColor.RED + "[When using a shield]Swiftness");
                }
                switch (list.get(7)) //Kindness
                {
                    case "0" ->
                            createItem(inv, 14, kin,
                                    ChatColor.BLUE + "Kindness- Be kind please",
                                    ChatColor.BLUE + "Low health and strength");
                    case "1" ->
                            createItem(inv, 14, kin,
                                    ChatColor.BLUE + "Kindness- You are normal",
                                    ChatColor.BLUE + "[When fishing]Fortunate (idk why i came up with this");
                    case "2" ->
                            createItem(inv, 14, kin,
                                    ChatColor.BLUE + "Kindness- You are kind",
                                    ChatColor.BLUE + "Low health and resistance");
                }
                switch (list.get(8)) //persistence
                {
                    case "0" ->
                            createItem(inv, 15, per,
                                    ChatColor.LIGHT_PURPLE + "Persistence- You are patient and persistent enough",
                                    ChatColor.LIGHT_PURPLE + "[Some situation]Slowness and resistance");
                    case "1" ->
                            createItem(inv, 15, per,
                                    ChatColor.LIGHT_PURPLE + "Persistence- You are impatient",
                                    ChatColor.LIGHT_PURPLE + "[Some situation]Fast energy spending and haste");
                }
                switch (list.get(9))
                {
                    case "0" ->
                            createItem(inv, 16, hig,
                                    ChatColor.RED + "Height- You are height-sick",
                                    ChatColor.RED + "[At high places]Nausea and swiftness");
                    case "1" ->
                            createItem(inv, 16, hig,
                                    ChatColor.RED + "Height- You are not afraid of it",
                                    ChatColor.GRAY + "No effects");
                }
            }
        }
    }
}
