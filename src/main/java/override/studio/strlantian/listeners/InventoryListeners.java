package override.studio.strlantian.listeners;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import override.studio.strlantian.playercharacters.ALocalisation;
import override.studio.strlantian.playercharacters.APCFactory;
import override.studio.strlantian.playercharacters.PlayerStorage;
import override.studio.strlantian.playercharacters.commands.AChangeCharacters;
import override.studio.strlantian.playercharacters.commands.AInitialiseCharacters;
import override.studio.strlantian.playercharacters.commands.AViewCharacters;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static override.studio.strlantian.PlayerCharacters.inst;
import static override.studio.strlantian.playercharacters.APCFactory.CHARENABLED;
import static override.studio.strlantian.playercharacters.APCFactory.CHARNOTCHANGED;
import static override.studio.strlantian.playercharacters.commands.AChangeCharacters.POINTMAP;
import static override.studio.strlantian.playercharacters.commands.ADeleteCharacters.DELCONFIRMCN;
import static override.studio.strlantian.playercharacters.commands.ADeleteCharacters.DELCONFIRMEN;
import static override.studio.strlantian.playercharacters.commands.AInitialiseCharacters.*;
import static override.studio.strlantian.playercharacters.commands.AViewCharacters.VIEWCHARCN;
import static override.studio.strlantian.playercharacters.commands.AViewCharacters.VIEWCHAREN;
import static override.studio.strlantian.playercharacters.ECharacters.*;

public final class InventoryListeners implements Listener
{
    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e)
    {
        Player pl = (Player) e.getPlayer();
        InventoryView inv = e.getView();
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        String title = inv.getTitle();

        String name = Objects.requireNonNull(inv.getItem(13)).getItemMeta().getDisplayName();
        char[] temp = name.toCharArray();
        int num = temp[1];
        int letter = temp[2];

        switch(title)
        {
            case ASKTITLECN, ASKTITLEEN ->
            {
                pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                switch(lang)
                {
                    case ALocalisation.CN-> pl.sendMessage(ChatColor.RED + "你取消了测试");
                    case ALocalisation.EN-> pl.sendMessage(ChatColor.RED + "You cancelled the test");
                }
                
            }
            case TESTINGCN, TESTINGEN ->
            {
                if(num != 5)
                {
                    pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    switch(lang)
                    {
                        case ALocalisation.CN-> pl.sendMessage(ChatColor.RED + "你取消了测试,结果将不被保存");
                        case ALocalisation.EN-> pl.sendMessage(ChatColor.RED + "You cancelled the test so your characters won't be saved");
                    }
                    if(num == 4
                            && letter == 'a')
                    {
                        ISWAITED.remove(pl);
                        NICETRY.remove(pl);
                        CHARTEMPLIST.remove(pl);
                        switch(lang)
                        {
                            case ALocalisation.CN -> pl.sendMessage(ChatColor.RED + "你的计时也是");
                            case ALocalisation.EN -> pl.sendMessage(ChatColor.RED + "Your timing, too");
                        }
                    }
                }
            }
        }
    }

    private static final Map<Player, Boolean> ISWAITED = new HashMap<>(Collections.emptyMap());
    private static final Map<Player, Integer> NICETRY = new HashMap<>(Collections.emptyMap());

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onClickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView invView = e.getView();
        String title = invView.getTitle();
        String plName = pl.getName();
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        if(title.equalsIgnoreCase(plName + VIEWCHARCN)
        || title.equalsIgnoreCase(plName + VIEWCHAREN))
        {                       //View Page
            e.setCancelled(true);
        }
        switch(title)
        {
            case INITITLEMAINCN, INITITLEMAINEN ->      //Initialise page
            {
                List<Integer> tempList = AInitialiseCharacters.getRandomConstList();
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 3-> AInitialiseCharacters.randCharacters(pl, tempList);
                    case 5-> AInitialiseCharacters.testCharactersPre(pl);
                }
            }
            case ASKTITLECN, ASKTITLEEN ->              //Ask if going to take a test
            {
                List<Integer> tempList = getRandomConstList();
                e.setCancelled(true);
                int slot = e.getSlot();

                switch (slot)
                {
                    case 2->
                    {
                        AInitialiseCharacters.testCharacters(pl);
                        CHARTEMPLIST.put(pl, tempList);
                    }
                    case 6-> pl.closeInventory();
                }
            }
            case TESTINGCN, TESTINGEN ->            //Characters' test page
            {
                e.setCancelled(true);
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                Inventory invQues = e.getInventory();
                ItemStack item = invQues.getItem(13);
                String queName = Objects.requireNonNull(item).getItemMeta().getDisplayName();
                char[] tempChar = queName.toCharArray();
                int num = tempChar[1];
                char letter = tempChar[2];
                int slot = e.getSlot();
                List<Integer> list = CHARTEMPLIST.get(pl);
                switch(num)
                {
                    case 1 ->
                    {
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(PERSEVERANCE.ordinal(), 0);
                                        list.set(BRAVENESS.ordinal(), 0);
                                        list.set(DARKNESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(PERSEVERANCE.ordinal(), 1);
                                        list.set(BRAVENESS.ordinal(), 1);
                                        list.set(DARKNESS.ordinal(), 0);
                                    }
                                }
                            }

                            case 'B' ->
                            {
                                switch (slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(PERSEVERANCE.ordinal(), 0);
                                        list.set(DARKNESS.ordinal(), 1);
                                        list.set(BRAVENESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(PERSEVERANCE.ordinal(), 1);
                                        list.set(DARKNESS.ordinal(), 0);
                                        list.set(BRAVENESS.ordinal(), 0);
                                    }
                                }
                                
                            }
                        }
                        int next = rand.nextInt(2) + 2;
                        askQuestion(next, invQues, lang, pl);
                    }
                    case 2 ->
                    {
                        int san = list.get(PERSEVERANCE.ordinal());
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        int randa = rand.nextInt(5);
                                        if(san > 0
                                        && randa != 0)
                                        {
                                            san--;
                                        }
                                        list.set(PERSEVERANCE.ordinal(), san);
                                        list.set(POSITIVITY.ordinal(), 0);
                                    }
                                    case 31 ->
                                    {
                                        int randb = rand.nextInt(5);
                                        if(san > 0
                                        && randb != 0)
                                        {
                                            san--;
                                        }
                                        list.set(PERSEVERANCE.ordinal(), san);
                                        list.set(POSITIVITY.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(PERSEVERANCE.ordinal(), san);
                                        list.set(POSITIVITY.ordinal(), 0);
                                    }
                                }
                            }
                            case 'B' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        if(san > 0)
                                        {
                                            san--;
                                        }
                                        list.set(POSITIVITY.ordinal(), 1);
                                        list.set(PERSEVERANCE.ordinal(), san);
                                    }
                                    case 31 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(POSITIVITY.ordinal(), 0);
                                        list.set(PERSEVERANCE.ordinal(), san);
                                    }
                                    case 33 ->
                                    {
                                        int bra = list.get(BRAVENESS.ordinal());
                                        if(bra == 0)
                                        {
                                            bra++;
                                        }
                                        list.set(POSITIVITY.ordinal(), 0);
                                        list.set(BRAVENESS.ordinal(), bra);
                                    }
                                }
                            }
                        }
                        int next = rand.nextInt(2) + 4;
                        askQuestion(next, invQues, lang, pl);
                    }
                    case 3 ->
                    {
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(PATIENCE.ordinal(), 0);
                                        list.set(KINDNESS.ordinal(), 2);
                                        int pos = list.get(POSITIVITY.ordinal());
                                        if(pos > 0)
                                        {
                                            pos--;
                                        }
                                        list.set(POSITIVITY.ordinal(), pos);
                                    }
                                    case 31 ->
                                    {
                                        list.set(PATIENCE.ordinal(), 0);
                                        list.set(KINDNESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(PATIENCE.ordinal(), 1);
                                        list.set(KINDNESS.ordinal(), 0);
                                        int pos = list.get(POSITIVITY.ordinal());
                                        if(pos < 1)
                                        {
                                            pos++;
                                        }
                                        list.set(POSITIVITY.ordinal(), pos);
                                    }
                                }
                            }
                            case 'B' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(KINDNESS.ordinal(), 2);
                                        list.set(PATIENCE.ordinal(), 0);
                                    }
                                    case 31 ->
                                    {
                                        list.set(KINDNESS.ordinal(), 2);
                                        list.set(PATIENCE.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(KINDNESS.ordinal(), 0);
                                        list.set(PATIENCE.ordinal(), 1);
                                    }
                                }
                            }
                        }
                        int next = rand.nextInt(2) + 6;
                        askQuestion(next, invQues, lang, pl);
                    }
                    case 4 ->
                    {
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                ISWAITED.put(pl, false);
                                NICETRY.put(pl, 0);
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        if(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).
                                                getItemMeta()).getDisplayName().
                                                equalsIgnoreCase(ChatColor.BLUE + "我愿意")
                                        || Objects.requireNonNull(e.getCurrentItem()).
                                                getItemMeta().getDisplayName().
                                                equalsIgnoreCase(ChatColor.BLUE + "Yes"))
                                        {
                                            if(!ISWAITED.get(pl))
                                            {
                                                switch(lang)
                                                {
                                                    case ALocalisation.CN -> APCFactory.setItemToInv(invQues, 29, new ItemStack(Material.GRAY_WOOL),
                                                            ChatColor.RED + "那你来等等", ChatColor.GRAY + "剩余秒数: ");
                                                    case ALocalisation.EN -> APCFactory.setItemToInv(invQues, 29, new ItemStack(Material.GRAY_WOOL),
                                                            ChatColor.RED + "Then wait actually", ChatColor.GRAY + "Seconds remaining: ");
                                                }
                                                new BukkitRunnable()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        for(int i = 60; i >= 0; i--)
                                                        {
                                                            try
                                                            {
                                                                TimeUnit.SECONDS.sleep(1);
                                                            }
                                                            catch (InterruptedException ex)
                                                            {
                                                                throw new RuntimeException(ex);
                                                            }
                                                            ItemStack counter = invQues.getItem(29);
                                                            ItemMeta im = Objects.requireNonNull(counter).getItemMeta();
                                                            List<String> lore = im.getLore();
                                                            String uh = Objects.requireNonNull(lore).get(lore.size() - 1);
                                                            String now = uh + i;
                                                            im.setLore(Collections.singletonList(now));
                                                            counter.setItemMeta(im);
                                                            if(i == 0)
                                                            {
                                                                switch(lang)
                                                                {
                                                                    case ALocalisation.CN -> APCFactory.createItemForOption(invQues, 0, "我愿意");
                                                                    case ALocalisation.EN -> APCFactory.createItemForOption(invQues, 0, "Yes");
                                                                }
                                                                ISWAITED.put(pl, true);
                                                                NICETRY.remove(pl);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }.runTaskAsynchronously(inst);
                                            }
                                            else
                                            {
                                                ISWAITED.remove(pl);
                                                list.set(PATIENCE.ordinal(), 0);
                                                int next = rand.nextInt(2) + 8;
                                                askQuestion(next, invQues, lang, pl);
                                            }
                                        }
                                        else
                                        {
                                            int haveATry = NICETRY.get(pl);
                                            haveATry++;
                                            if(haveATry >= 15)
                                            {
                                                try
                                                {
                                                    NICETRY.remove(pl);
                                                }
                                                catch (Exception ignored)
                                                {}
                                                switch(lang)
                                                {
                                                    case ALocalisation.CN -> pl.sendMessage(ChatColor.RED + "都说了让你等你不听");
                                                    case ALocalisation.EN -> pl.sendMessage(ChatColor.RED + "I said wait but u wasn't listening");
                                                }
                                                pl.closeInventory();
                                            }
                                            else
                                            {
                                                NICETRY.put(pl, haveATry);
                                                switch(lang)
                                                {
                                                    case ALocalisation.CN -> pl.sendMessage(ChatColor.RED + "请耐心等待");
                                                    case ALocalisation.EN -> pl.sendMessage(ChatColor.RED + "Please wait");
                                                }
                                            }
                                        }
                                    }

                                    case 33 ->
                                    {
                                        ISWAITED.remove(pl);
                                        list.set(PATIENCE.ordinal(), 1);
                                        int ene = list.get(ENERGY.ordinal());
                                        if(ene > 0)
                                        {
                                            ene--;
                                        }
                                        list.set(ENERGY.ordinal(), ene);
                                        int next = rand.nextInt(2) + 8;
                                        askQuestion(next, invQues, lang, pl);
                                    }
                                }
                            }
                            case 'B' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        int rand1 = rand.nextInt(5);
                                        switch(rand1)
                                        {
                                            case 0, 1  -> list.set(PATIENCE.ordinal(), 0);
                                            case 2, 3, 4 -> list.set(PATIENCE.ordinal(), 1);
                                        }
                                    }
                                    case 33 -> list.set(PATIENCE.ordinal(), 0);
                                }
                                int next = rand.nextInt(2) + 8;
                                askQuestion(next, invQues, lang, pl);
                            }
                        }
                    }
                    case 5 ->
                    {
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(COLDNESS.ordinal(), 1);
                                        int bra = list.get(BRAVENESS.ordinal());
                                        int rand3 = rand.nextInt(5);
                                        if(bra > 0
                                        && rand3 != 0)
                                        {
                                            bra--;
                                        }
                                        list.set(BRAVENESS.ordinal(), bra);
                                    }
                                    case 33 ->
                                    {
                                        list.set(COLDNESS.ordinal(), 0);
                                        int bra = list.get(BRAVENESS.ordinal());
                                        int rand4 = rand.nextInt(5);
                                        if(bra < 1
                                        && rand4 != 0
                                        && rand4 != 1)
                                        {
                                            bra++;
                                        }
                                        list.set(BRAVENESS.ordinal(), bra);
                                    }
                                }
                            }
                            case 'B' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        int pat = list.get(PATIENCE.ordinal());
                                        int rand5 = rand.nextInt();
                                        if(rand5 == 0
                                        || rand5 == 1)
                                        {
                                            list.set(COLDNESS.ordinal(), 1);
                                        }
                                        else
                                        {
                                            list.set(COLDNESS.ordinal(), 0);
                                        }
                                        if(pat > 0)
                                        {
                                            pat--;
                                        }
                                        list.set(PATIENCE.ordinal(), pat);
                                    }

                                    case 31 ->
                                    {
                                        int bra = list.get(BRAVENESS.ordinal());
                                        if(bra > 0)
                                        {
                                            bra--;
                                        }
                                        int pat = list.get(PATIENCE.ordinal());
                                        if(pat < 1)
                                        {
                                            pat++;
                                        }
                                        list.set(COLDNESS.ordinal(), 1);
                                        list.set(BRAVENESS.ordinal(), bra);
                                        list.set(PATIENCE.ordinal(), pat);
                                    }

                                    case 33 ->
                                    {
                                        int rand6 = rand.nextInt(2);
                                        if(rand6 == 0)
                                        {
                                            list.set(COLDNESS.ordinal(), 0);
                                        }
                                        else
                                        {
                                            list.set(COLDNESS.ordinal(), 1);
                                        }
                                    }
                                }
                            }
                        }
                        switch(lang)
                        {
                            case ALocalisation.CN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "好,测试完成");
                                pl.sendMessage(ChatColor.GREEN + "结果已经保存,来看看吧");
                            }
                            case ALocalisation.EN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "Well done, the test is over");
                                pl.sendMessage(ChatColor.GREEN + "The results has been saved. Have a look!");
                            }
                        }
                        ps.setCharacterList(list);
                        pl.closeInventory();
                        ps.setEnable(CHARENABLED);
                        ps.setChanged(CHARNOTCHANGED);
                        AViewCharacters.viewCharacters(pl);
                    }
                    default ->
                    {
                        pl.closeInventory();
                        switch(lang)
                        {
                            case ALocalisation.CN ->
                            {
                                pl.sendMessage(ChatColor.RED + "要么是你改了");
                                pl.sendMessage(ChatColor.RED + "要么是插件坏了");
                                pl.sendMessage(ChatColor.RED + "你看着办吧 =Δ=");
                            }
                            case ALocalisation.EN ->
                            {
                                pl.sendMessage(ChatColor.RED + "There must be something wrong");
                                pl.sendMessage(ChatColor.RED + "Either the plugin or yourself");
                                pl.sendMessage(ChatColor.RED + "What do u think =Δ=");
                            }
                        }
                    }
                }
            }
            case DELCONFIRMCN, DELCONFIRMEN ->             //Ask if delete page
            {
                e.setCancelled(true);
                switch(e.getSlot())
                {
                    case 3 ->
                    {
                        pl.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        switch(lang)                //I HATE DEPRECATION
                        {
                            case ALocalisation.CN ->
                            {
                                pl.sendMessage(ChatColor.YELLOW + "你真的确定吗,请再选择一次,因为删除后无法恢复");
                                BaseComponent sure = new TextComponent(ChatColor.GREEN + "我真的确认");
                                sure.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("嗯嗯嗯删了吧")));
                                sure.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/character delete and im very sure about this"));
                                pl.spigot().sendMessage(sure);
                            }
                            case ALocalisation.EN ->
                            {
                                pl.sendMessage(ChatColor.YELLOW + "Are you really sure? Please select again bcs you can't recover them");
                                BaseComponent sure = new TextComponent(ChatColor.GREEN + "Yes");
                                sure.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Delete please")));
                                sure.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/character delete and im very sure about this"));
                                pl.spigot().sendMessage(sure);
                            }
                        }
                    }
                    case 5 ->
                    {
                        pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        pl.closeInventory();
                        switch(lang)
                        {
                            case ALocalisation.CN -> pl.sendMessage(ChatColor.RED + "你取消了删除");
                            case ALocalisation.EN -> pl.sendMessage(ChatColor.RED + "You refused to delete");
                        }
                    }
                }
            }
        }
        if(title.equalsIgnoreCase(plName + VIEWCHARCN + "-更改页面")
                || title.equalsIgnoreCase(plName + VIEWCHAREN + "-Changing Page"))
        {                       //Change Page
            e.setCancelled(true);
            int slot = e.getSlot();
            ClickType click = e.getClick();
            List<Integer> ogCharList = ps.getCharacterList();
            List<Integer> nowCharList = ps.getCharacterList();
            int pointNow = POINTMAP.get(pl);
            int og = ogCharList.get(slot - 7);
            int now = nowCharList.get(slot - 7);
            switch(slot)
            {
                case 10 -> AChangeCharacters.checkAndModify(pl, click, PERSEVERANCE, nowCharList);
            }
        }
    }
}
