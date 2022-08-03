package override.studio.strlantian.playercharacters.commandrealisation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.enums.Languages;
import override.studio.strlantian.playercharacters.enums.QuestionOptions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static override.studio.strlantian.playercharacters.PlayerCharacters.*;

public final class InitialiseCharacters
{
    public static final Map<String, List<Integer>> CharTempList = new HashMap<>(Collections.emptyMap());
    public static List<Integer> getRandomConstList(Player pl)
    {
        List<Integer> tempList = new ArrayList<>();
        String name = pl.getName();
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
                case 0-> satu--;
                case 1-> ener--;
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
                case 0-> satu++;
                case 1-> ener++;
            }
        }
        if(heal == 0)
        {
            int rand3 = RAND.nextInt(2);
            switch(rand3)
            {
                case 0->
                {
                    if(satu < 2)
                    {
                        satu++;
                    }
                    
                }
                case 1->
                {
                    if(ener < 2)
                    {
                        ener++;
                    }
                }
            }
        }
        tempList.set(0, satu);
        tempList.set(1, ener);
        tempList.set(2, heal);

        return tempList;
    }
    @SuppressWarnings("Deprecation")
    public static void randCharacters(Player pl, List<Integer> list)
    { //Full-Randomly get characters
        Languages lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case CN-> pl.sendMessage(ChatColor.YELLOW + "抽取中");
            case EN-> pl.sendMessage(ChatColor.YELLOW + "Rolling your characters");
        }
        final ThreadLocalRandom RAND = ThreadLocalRandom.current();
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
        list.set(3, sani);
        list.set(4, dark);
        list.set(5, posi);
        list.set(6, brav);
        list.set(7, kind);
        list.set(8, pati);
        list.set(9, heig);

        setCharacter(pl, list);
        switch(lang)
        {
            case CN-> pl.sendMessage(ChatColor.GREEN + "已完成,如图是结果(不可更改)");
            case EN-> pl.sendMessage(ChatColor.GREEN + "Finished. Here's your result(Can't be changed)");
        }
        ViewCharacters.viewCharacters(pl);
        setEnable(pl, true);
    }
    public static final String ASKTITLECN = "您确定吗";
    public static final String ASKTITLEEN = "Are you sure";
    @SuppressWarnings("Deprecation")
    public static void testCharactersPre(Player pl)
    {
        final ItemStack ATTENTION = new ItemStack(Material.PAPER, 1);
        final ItemStack CONFIRM = new ItemStack(Material.GREEN_WOOL, 1);
        final ItemStack DECLINE = new ItemStack(Material.RED_WOOL, 1);

        Languages lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.GREEN + "你将会收到一个简短的小调查");
                pl.sendMessage(ChatColor.GREEN + "最多花费5分钟时间");
                pl.sendMessage(ChatColor.YELLOW + "建议在一个安全的地方做来避免可能的事故");
                pl.sendMessage(ChatColor.RED + "如果你关掉测试窗口,那么结果将不会保存");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLECN);
                createItem(invAsk, 2, DECLINE, ChatColor.RED + "我不想我不要");
                createItem(invAsk, 4, ATTENTION, "请参阅聊天栏");
                createItem(invAsk, 6, CONFIRM, ChatColor.GREEN + "好的快开始吧");
            }

            case EN->
            {
                pl.sendMessage(ChatColor.GREEN + "You will receive a short survey");
                pl.sendMessage(ChatColor.GREEN + "It will take you about 5 minutes");
                pl.sendMessage(ChatColor.YELLOW + "I suggest you doing this in a safe place to avoid any possible accidents");
                pl.sendMessage(ChatColor.RED + "If you close the page, your characters won't be saved");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLEEN);
                createItem(invAsk, 2, DECLINE, ChatColor.RED + "No please");
                createItem(invAsk, 4, ATTENTION, "Read the chat");
                createItem(invAsk, 6, CONFIRM, ChatColor.GREEN + "OK I will be quick");
            }
        }
    }
    public static void askQuestion(int which, Inventory inv, Languages lang, Player pl)
    {
        switch(lang)
        {
            case CN ->
            {
                switch(which)
                {
                    case 0 ->
                    {
                        createItem(inv, "1A", "对于看过的恐怖片你愿意再看一遍吗");
                        createItem(inv, QuestionOptions.OPIA, "愿意,才不怕呢");
                        createItem(inv, QuestionOptions.OPIC, "不愿意,想吓死我?");
                        
                    }
                    case 1 ->
                    {
                        createItem(inv, "1B", "晚上你睡觉想开个灯吗,哪怕是小灯");
                        createItem(inv, QuestionOptions.OPIA, "非常想,我怕黑");
                        createItem(inv, QuestionOptions.OPIC, "才不想,,,,我才不");
                        
                    }
                    case 2 ->
                    {
                        createItem(inv, "2A", "假如你打PVP忽然断网了 你会(最接近)怎么反应");
                        createItem(inv, QuestionOptions.OPIA, "气死我了 对面肯定打不过开挂断网");
                        createItem(inv, QuestionOptions.OPIB, "咋突然断网了??我快赢了啊啊啊!!");
                        createItem(inv, QuestionOptions.OPIC, "这路由器是不是出问题了 我去修修");
                        
                    }
                    case 3 ->
                    {
                        createItem(inv, "2B", "面对很重要的考试前 你通常怎么表现");
                        createItem(inv, QuestionOptions.OPIA, "紧张 很紧张");
                        createItem(inv, QuestionOptions.OPIB, "自信 觉得认真学了不怎么会失误");
                        createItem(inv, QuestionOptions.OPIC, "不当回事 啊对对对(划掉");
                        
                    }
                    case 4 ->
                    {
                        createItem(inv, "3A", "有人借你钱一直不还 你咋办");
                        createItem(inv, QuestionOptions.OPIA, "不急 他只要有时间肯定会还的");
                        createItem(inv, QuestionOptions.OPIB, "直接给他说 能不能还我?");
                        createItem(inv, QuestionOptions.OPIC, "阴阳怪气 核疝问候他");
                        
                    }
                    case 5 ->
                    {
                        createItem(inv, "3B", "你想养宠物或者在养宠物吗");
                        createItem(inv, QuestionOptions.OPIA, "想(或者正在养)");
                        createItem(inv, QuestionOptions.OPIC, "不想");
                        
                    }
                    case 6 ->
                    {
                        createItem(inv, "4A", "现在让你干等60秒 你愿意等吗");
                        createItem(inv, QuestionOptions.OPIA, "愿意");
                        createItem(inv, QuestionOptions.OPIC, "不愿意");
                    }
                    case 7 ->
                    {
                        createItem(inv, "4B", "看动画的时候你通常跳过开头片尾吗");
                        createItem(inv, QuestionOptions.OPIA, "跳过");
                        createItem(inv, QuestionOptions.OPIC, "不跳过");
                    }
                    case 8 ->
                    {
                        createItem(inv, "5A", "如果有个极其安全机会 你想在高楼之间跑酷吗");
                        createItem(inv, QuestionOptions.OPIA, "想");
                        createItem(inv, QuestionOptions.OPIB, "不想");
                    }
                    case 9 ->
                    {
                        createItem(inv, "5B", "假如你睁眼忽然发现你在一个很高山上 你会怎么办");
                        createItem(inv, QuestionOptions.OPIA, "吓一下然后冷静地观察周围 慢慢下");
                        createItem(inv, QuestionOptions.OPIB, "根本不怕 直接滑下去");
                        createItem(inv, QuestionOptions.OPIC, "吓一下然后掉下去(真有人选这个嘛");
                    }
                    default ->
                    {
                        createItem(inv, ".͆.̓", "出bug了 你觉得是咋回事 =▲=");
                        createItem(inv, QuestionOptions.OPIA, "ị̬̰̤̺̹̊ͦ̍ͭ͂̽͑ͫ͡ͅM͚̝̘̞̯̦͉̌̂͑ͤ̓ͭ̀");
                        createItem(inv, QuestionOptions.OPIB, "aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼");
                        createItem(inv, QuestionOptions.OPIC, "ĺ̳͕͖̬̮̳͋̄ǫ̥͖͕̃͌̉̈ͮ̿ś̳͕͖̮̳͋̄t̨̥͖͕̃͌̉̈ͮ̿");
                    }
                }
            }
        }
        pl.openInventory(inv);
    }
    public static final String TESTINGCN = "正在测试";
    public static final String TESTINGEN = "Testing...";
    @SuppressWarnings("Deprecation")
    public static void testCharacters(Player pl, List<Integer> list)
    {
        Languages lang = Localisation.getLanguage(pl);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int queNum = rand.nextInt(2);

        switch(lang)
        {
            case CN->
            {
                final Inventory INV = Bukkit.createInventory(null, 5 * 9, TESTINGCN);
                askQuestion(queNum, INV, lang, pl);
                
            }
            case EN->
            {
                final Inventory INV = Bukkit.createInventory(null, 5 * 9, TESTINGEN);
                askQuestion(queNum, INV, lang, pl);
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

        Languages language = Localisation.getLanguage(pl);
        switch(language)
        {
            case CN->
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

            case EN->
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