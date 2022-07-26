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
import java.util.concurrent.ThreadLocalRandom;

import static OverrideStudio.STRlantian.PlayerCharacters.PlayerCharacters.createItem;

public final class InitialiseCharacters
{
    @SuppressWarnings("Deprecation")
    public static void randCharacters(Player pl)
    { //Full-Randomly get characters
        String lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case "CN"->
                    pl.sendMessage(ChatColor.YELLOW + "抽取中");
            case "EN"->
                    pl.sendMessage(ChatColor.YELLOW + "Rolling your characters");
        }
        final ThreadLocalRandom RAND = ThreadLocalRandom.current();
        int satu = RAND.nextInt(3);
        int ener = RAND.nextInt(3);
        int heal = RAND.nextInt(3);
        if(satu == 2
        && ener == 2)
        {
            if(heal < 2)
            {
                heal++;
            }
            int rand1 = RAND.nextInt(2);
            switch(rand1)
            {
                case 0->
                    satu--;
                case 1->
                    ener--;
            }
        }
        else if(satu == 0
        && ener == 0)
        {
            if(heal == 2)
            {
                heal--;
            }
            int rand2 = RAND.nextInt(2);
            switch(rand2)
            {
                case 0->
                    satu++;
                case 1->
                    ener++;
            }
        }
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.SATURATION, satu);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.ENERGY, ener);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.HEALTH, heal);

        int sani = RAND.nextInt(3);
        int bound1 = 2;
        if(sani == 2)
        {
            bound1++;
        }
        int brav = RAND.nextInt(bound1);
        int bound2 = 2;
        if(brav == 2)
        {
            brav = 1;
            bound2++;
        }
        int dark = RAND.nextInt(bound2);
        int posi = RAND.nextInt();

        int kind = RAND.nextInt(3);
        int bound3 = 2;
        if(kind == 2)
        {
            bound3++;
        }
        int pati = RAND.nextInt(bound3);
        int heig = RAND.nextInt(bound2);
        if(dark == 2)
        {
            dark = 1;
        }
        if(heig == 2)
        {
            heig = 1;
        }
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.SANITY, sani);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.DARKNESS, dark);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.POSITIVITY, posi);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.BRAVENESS, brav);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.KINDNESS, kind);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.PATIENCE, pati);
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.HEIGHT, heig);

        switch(lang)
        {
            case "CN"->
                    pl.sendMessage(ChatColor.GREEN + "已完成,如图是结果(不可更改)");
            case "EN"->
                    pl.sendMessage(ChatColor.GREEN + "Finished. Here's your result(Can't be changed)");
        }
        ViewCharacters.viewCharacters(pl);
    }

    public static final String ASKTITLECN = "您确定吗";
    public static final String ASKTITLEEN = "Are you sure";
    public static final String TESTINGCN = "正在测试";
    public static final String TESTINGEN = "Testing...";
    @SuppressWarnings("Deprecation")
    public static void testCharacters(Player pl)
    {
        final ItemStack ATTENTION = new ItemStack(Material.PAPER, 1);
        final ItemStack CONFIRM = new ItemStack(Material.GREEN_WOOL, 1);
        final ItemStack DECLINE = new ItemStack(Material.RED_WOOL, 1);

        String lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "你将会收到一个简短的小调查");
                pl.sendMessage(ChatColor.GREEN + "最多花费5分钟时间");
                pl.sendMessage(ChatColor.YELLOW + "建议在一个安全的地方做来避免可能的事故");
                pl.sendMessage(ChatColor.RED + "如果你关掉测试窗口...欸对哦关掉会怎样我没试");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLECN);
                createItem(invAsk, 2, DECLINE, ChatColor.RED + "我不想我不要", null);
                createItem(invAsk, 4, ATTENTION, "请参阅聊天栏", null);
                createItem(invAsk, 6, CONFIRM, ChatColor.GREEN + "好的快开始吧", null);
            }

            case "EN"->
            {
                pl.sendMessage(ChatColor.GREEN + "You will receive a short survey");
                pl.sendMessage(ChatColor.GREEN + "It will take you about 5 minutes");
                pl.sendMessage(ChatColor.YELLOW + "I suggest you doing this in a safe place to avoid any possible accidents");
                pl.sendMessage(ChatColor.RED + "If you close the page...Ops i forgot to test it lol");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLEEN);
                createItem(invAsk, 2, DECLINE, ChatColor.RED + "No please", null);
                createItem(invAsk, 4, ATTENTION, "Read the chat", null);
                createItem(invAsk, 6, CONFIRM, ChatColor.GREEN + "OK I will be quick", null);
            }
        }
    }

    public static void chooseCharacters(Player pl)
    {

    }

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