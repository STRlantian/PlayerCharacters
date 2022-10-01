package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.PCFactory;
import override.studio.strlantian.playercharacters.PlayerStorage;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static override.studio.strlantian.playercharacters.Localisation.CN;
import static override.studio.strlantian.playercharacters.Localisation.EN;
import static override.studio.strlantian.playercharacters.PCFactory.*;

public abstract class InitialiseCharacters
{
    public static final Map<Player, List<Integer>> CHARTEMPLIST = new HashMap<>(Collections.emptyMap());
    private static void createItemForQuestion(Inventory inv, String num, String lore)
    {
        final ItemStack Q = new ItemStack(Material.BOOK, 1);
        PCFactory.setItemToInv(inv, 13, Q, "(" + num + "/5)", ChatColor.GREEN + lore);
    }

    public static List<Integer> getRandomConstList()
    {
        List<Integer> tempList = new ArrayList<>();
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
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
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

        ps.setCharacterList(list);
        switch(lang)
        {
            case CN-> pl.sendMessage(ChatColor.GREEN + "已完成,如图是结果(不可更改)");
            case EN-> pl.sendMessage(ChatColor.GREEN + "Finished. Here's your result(Can't be changed)");
        }
        ps.setEnable(CHARENABLED);
        ps.setChanged(CHARNOTCHANGED);
        ViewCharacters.viewCharacters(pl);
    }
    public static final String ASKTITLECN = "您确定吗";
    public static final String ASKTITLEEN = "Are you sure";
    @SuppressWarnings("Deprecation")
    public static void testCharactersPre(Player pl)
    {
        final ItemStack ATTENTION = new ItemStack(Material.PAPER, 1);
        final ItemStack CONFIRM = new ItemStack(Material.GREEN_WOOL, 1);
        final ItemStack DECLINE = new ItemStack(Material.RED_WOOL, 1);

        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.GREEN + "你将会收到一个简短的小调查");
                pl.sendMessage(ChatColor.GREEN + "最多花费5分钟时间");
                pl.sendMessage(ChatColor.YELLOW + "建议在一个安全的地方做来避免可能的事故");
                pl.sendMessage(ChatColor.RED + "如果你关掉测试窗口,那么结果将不会保存");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLECN);
                setItemToInv(invAsk, 2, DECLINE, ChatColor.RED + "我不想我不要");
                setItemToInv(invAsk, 4, ATTENTION, "请参阅聊天栏");
                setItemToInv(invAsk, 6, CONFIRM, ChatColor.GREEN + "好的快开始吧");
            }

            case EN->
            {
                pl.sendMessage(ChatColor.GREEN + "You will receive a short survey");
                pl.sendMessage(ChatColor.GREEN + "It will take you about 5 minutes");
                pl.sendMessage(ChatColor.YELLOW + "I suggest you doing this in a safe place to avoid any possible accidents");
                pl.sendMessage(ChatColor.RED + "If you close the page, your characters won't be saved");
                Inventory invAsk = Bukkit.createInventory(null, 9, ASKTITLEEN);
                setItemToInv(invAsk, 2, DECLINE, ChatColor.RED + "No please");
                setItemToInv(invAsk, 4, ATTENTION, "Read the chat");
                setItemToInv(invAsk, 6, CONFIRM, ChatColor.GREEN + "OK I will be quick");
            }
        }
    }
    
    public static void askQuestion(int which, Inventory inv, int lang, Player pl)
    {
        switch(lang)
        {
            case CN ->
            {
                switch(which)
                {
                    case 0 ->
                    {
                        createItemForQuestion(inv, "1A", "对于看过的恐怖片你敢再看一遍吗");
                        createItemForOption(inv, 0, "敢,才不怕呢");
                        createItemForOption(inv, 2, "不敢,想吓死我?");
                    }
                    case 1 ->
                    {
                        createItemForQuestion(inv, "1B", "晚上你睡觉想开个灯吗,哪怕是小灯");
                        createItemForOption(inv, 0, "非常想,我怕黑");
                        createItemForOption(inv, 2, "才不想,,,,我才不");
                    }
                    case 2 ->
                    {
                        createItemForQuestion(inv, "2A", "假如你打PVP忽然断网了 你会(最接近)怎么反应");
                        createItemForOption(inv, 0, "气死我了 对面肯定打不过开挂断网");
                        createItemForOption(inv, 1, "咋突然断网了??我快赢了啊啊啊!!");
                        createItemForOption(inv, 2, "这路由器是不是出问题了 我去修修");
                    }
                    case 3 ->
                    {
                        createItemForQuestion(inv, "2B", "面对很重要的考试前 你通常怎么表现");
                        createItemForOption(inv, 0, "紧张 很紧张");
                        createItemForOption(inv, 1, "自信 觉得认真学了不怎么会失误");
                        createItemForOption(inv, 2, "不当回事 啊对对对(划掉");
                    }
                    case 4 ->
                    {
                        createItemForQuestion(inv, "3A", "有人借你钱一直不还 你咋办");
                        createItemForOption(inv, 0, "不急 他只要有时间肯定会还的");
                        createItemForOption(inv, 1, "直接给他说 能不能还我?");
                        createItemForOption(inv, 2, "阴阳怪气 核疝问候(物理)他");
                    }
                    case 5 ->
                    {
                        createItemForQuestion(inv, "3B", "你喜欢小动物吗,想养吗");
                        createItemForOption(inv, 0, "喜欢 好可爱的 正在养呢");
                        createItemForOption(inv, 1, "喜欢 但是麻烦 不想养");
                        createItemForOption(inv, 2, "不喜欢 我嫌他们烦");
                    }
                    case 6 ->
                    {
                        createItemForQuestion(inv, "4A", "现在让你干等60秒 你愿意等吗");
                        createItemForOption(inv, 0, "愿意");
                        createItemForOption(inv, 2, "不愿意");
                    }
                    case 7 ->
                    {
                        createItemForQuestion(inv, "4B", "看动画的时候你通常跳过开头片尾吗");
                        createItemForOption(inv, 0, "跳过");
                        createItemForOption(inv, 2, "不跳过");
                    }
                    case 8 ->
                    {
                        createItemForQuestion(inv, "5A", "如果有个可以保证绝对安全的机会 你敢在高楼之间跑酷吗");
                        createItemForOption(inv, 0, "我敢");
                        createItemForOption(inv, 1, "不敢");
                    }
                    case 9 ->
                    {
                        createItemForQuestion(inv, "5B", "假如你睁眼忽然发现你在一个很高山上 你会怎么办");
                        createItemForOption(inv, 0, "吓一下然后冷静地观察周围 慢慢下");
                        createItemForOption(inv, 1, "根本不怕 直接滑下去");
                        createItemForOption(inv, 2, "吓一下然后掉下去(真有人选这个嘛");
                    }
                    default ->
                    {
                        createItemForQuestion(inv, ".͆.̓", "出bug了 你觉得是咋回事 =▲=");
                        createItemForOption(inv, 0, "ị̬̰̤̺̹̊ͦ̍ͭ͂̽͑ͫ͡ͅM͚̝̘̞̯̦͉̌̂͑ͤ̓ͭ̀");
                        createItemForOption(inv, 1, "aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼");
                        createItemForOption(inv, 2, "ĺ̳͕͖̬̮̳͋̄ǫ̥͖͕̃͌̉̈ͮ̿ś̳͕͖̮̳͋̄t̨̥͖͕̃͌̉̈ͮ̿");
                    }
                }
            }

            case EN ->
            {
                switch(which)
                {
                    case 0 ->
                    {
                        createItemForQuestion(inv, "1A", "Do you get afraid when you see a scary film for another time");
                        createItemForOption(inv, 0, "Yes");
                        createItemForOption(inv, 2, "No");
                    }
                    case 1 ->
                    {
                        createItemForQuestion(inv, "1B", "Do you often light up a light when sleeping in darkness");
                        createItemForOption(inv, 0, "Yes");
                        createItemForOption(inv, 2, "No");
                    }
                    case 2 ->
                    {
                        createItemForQuestion(inv, "2A", "What will you think if you are disconnected during PVP");
                        createItemForOption(inv, 0, "My opponent must cheat to make me disconnected -□-");
                        createItemForOption(inv, 1, "Why disconnected??? Reconnect pleeeease!!");
                        createItemForOption(inv, 2, "Is the wifi broken? Let me have a look");
                    }
                    case 3 ->
                    {
                        createItemForQuestion(inv, "2B", "Brfore important examinations, what do you often think");
                        createItemForOption(inv, 0, "Nervous");
                        createItemForOption(inv, 1, "Confident, I will pass");
                        createItemForOption(inv, 2, "(Ignore this exam, don't even think about it)");
                    }
                    case 4 ->
                    {
                        createItemForQuestion(inv, "3A", "If someone borrowed your money and hasn't payed back now");
                        createItemForOption(inv, 0, "It's just not the right time. He will pay me back someday");
                        createItemForOption(inv, 1, "Tell him directly, when to pay me back??");
                        createItemForOption(inv, 2, "Ask him \"politely\" and \"Greet\" him(substantively)");
                    }
                    case 5 ->
                    {
                        createItemForQuestion(inv, "3B", "Do you like pets and do you feed any pets");
                        createItemForOption(inv, 0, "I like them and I am feeding at least one");
                        createItemForOption(inv, 1, "I like them but I think I don't have time to feed one");
                        createItemForOption(inv, 2, "I don't like them. They are quite naughty and dirty");
                    }
                    case 6 ->
                    {
                        createItemForQuestion(inv, "4A", "If I ask you to wait for 60 seconds, will you accept");
                        createItemForOption(inv, 0, "Yes");
                        createItemForOption(inv, 2, "No");
                    }
                    case 7 ->
                    {
                        createItemForQuestion(inv, "4B", "Do you often skip the staring and ending songs of animations");
                        createItemForOption(inv, 0, "Yes");
                        createItemForOption(inv, 2, "No");
                    }
                    case 8 ->
                    {
                        createItemForQuestion(inv, "5A", "If there is a completely safe chance for you to do parkour above high buildings, do you dare to do it");
                        createItemForOption(inv, 0, "Yes");
                        createItemForOption(inv, 1, "No");
                    }
                    case 9 ->
                    {
                        createItemForQuestion(inv, "5B", "If you find yourself at a high place when waking up, what will you prob do");
                        createItemForOption(inv, 0, "Get scared and calm down, then climb down slowly");
                        createItemForOption(inv, 1, "Nothing scary, I will just slide down");
                        createItemForOption(inv, 2, "Get scared and fall down(Will there be anyone choose this)");
                    }
                    default ->
                    {
                        createItemForQuestion(inv, ".͆.̓", "It seems that we get a bug =▲=");
                        createItemForOption(inv, 0, "ị̬̰̤̺̹̊ͦ̍ͭ͂̽͑ͫ͡ͅM͚̝̘̞̯̦͉̌̂͑ͤ̓ͭ̀");
                        createItemForOption(inv, 1, "aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼aͩ͑̇҉̨̠͈̼");
                        createItemForOption(inv, 2, "ĺ̳͕͖̬̮̳͋̄ǫ̥͖͕̃͌̉̈ͮ̿ś̳͕͖̮̳͋̄t̨̥͖͕̃͌̉̈ͮ̿");
                    }
                }
            }
        }
        pl.openInventory(inv);
    }
    public static final String TESTINGCN = "正在测试";
    public static final String TESTINGEN = "Testing...";
    @SuppressWarnings("Deprecation")
    public static void testCharacters(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
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
    public static final String INITITLEMAINCN = "初始化性格";
    public static final String INITITLEMAINEN = "Character Initialisation";
    @SuppressWarnings("Deprecation")
    public static void initialiseCharacters(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int language = ps.getLanguage();
        switch(language)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.GREEN + "开始初始化性格...");
                Inventory invMain = Bukkit.createInventory(null, 9, INITITLEMAINCN);

                setItemToInv(invMain, 3, new ItemStack(Material.APPLE), ChatColor.YELLOW + "纯随机抽取你的性格",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.BLUE + "插件自动抽,抽完不能改");
                setItemToInv(invMain, 5, new ItemStack(Material.APPLE), ChatColor.YELLOW + "测试得出你的性格",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.BLUE + "通过作者的测试,测完不能改");
                pl.openInventory(invMain);
            }

            case EN->
            {
                pl.sendMessage(ChatColor.GREEN + "Start initialising...");
                Inventory invMain = Bukkit.createInventory(null, 9, INITITLEMAINCN);
                setItemToInv(invMain, 3, new ItemStack(Material.APPLE), ChatColor.YELLOW + "Get your characters randomly",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.BLUE + "Fully-randomly get your characters");
                setItemToInv(invMain, 5, new ItemStack(Material.APPLE), ChatColor.YELLOW + "Test your characters",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.BLUE + "Have a short test");
                pl.openInventory(invMain);
            }
        }
    }
}