package override.studio.strlantian.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.PlayerCharacters;
import override.studio.strlantian.playercharacters.commands.InitialiseCharacters;
import override.studio.strlantian.playercharacters.commands.ViewCharacters;
import override.studio.strlantian.playercharacters.enums.Languages;
import override.studio.strlantian.playercharacters.enums.QuestionOptions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static override.studio.strlantian.Main.inst;
import static override.studio.strlantian.playercharacters.commands.InitialiseCharacters.*;
import static override.studio.strlantian.playercharacters.enums.Characters.*;

public final class InventoryListeners implements Listener
{
    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e)
    {
        Player pl = (Player) e.getPlayer();
        InventoryView inv = e.getView();
        Languages lang = Localisation.getLanguage(pl);
        String title = inv.getTitle();

        String name = Objects.requireNonNull(inv.getItem(13)).getItemMeta().getDisplayName();
        char[] temp = name.toCharArray();
        int num = temp[1];
        int letter = temp[2];

        switch(title)
        {
            case ASKTITLECN, InitialiseCharacters.ASKTITLEEN ->
            {
                pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                switch(lang)
                {
                    case CN-> pl.sendMessage(ChatColor.RED + "你取消了测试");

                    case EN-> pl.sendMessage(ChatColor.RED + "You cancelled the test");
                }
                
            }

            case TESTINGCN, TESTINGEN ->
            {
                if(num != 5)
                {
                    pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    switch(lang)
                    {
                        case CN-> pl.sendMessage(ChatColor.RED + "你取消了测试,结果将不被保存");
                        case EN-> pl.sendMessage(ChatColor.RED + "You cancelled the test so your characters won't be saved");
                    }
                    if(num == 4
                            && letter == 'a')
                    {
                        ISWAITED.remove(pl.getName());
                        NICETRY.remove(pl.getName());
                        switch(lang)
                        {
                            case CN -> pl.sendMessage(ChatColor.RED + "你的计时也是");
                            case EN -> pl.sendMessage(ChatColor.RED + "Your timing, too");
                        }
                    }
                }
            }
        }
    }

    private static final Map<String, Boolean> ISWAITED = new HashMap<>(Collections.emptyMap());
    private static final Map<String, Integer> NICETRY = new HashMap<>(Collections.emptyMap());

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onClickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView invView = e.getView();
        String title = invView.getTitle();
        String plName = pl.getName();
        if(title.equalsIgnoreCase(plName + "的性格页面")
        || title.equalsIgnoreCase(plName + "'s Character Page"))
        {
            e.setCancelled(true);
        }

        switch(title)
        {
            case INITITLEMAINCN, INITITLEMAINEN ->
            {
                List<Integer> tempList = InitialiseCharacters.getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 2-> InitialiseCharacters.randCharacters(pl, tempList);
                    case 4-> InitialiseCharacters.testCharactersPre(pl);
                    case 6-> InitialiseCharacters.chooseCharacters(pl);
                }
            }

            case ASKTITLECN, ASKTITLEEN ->
            {
                List<Integer> tempList = getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();

                switch (slot)
                {
                    case 2->
                    {
                        InitialiseCharacters.testCharacters(pl);
                        CharTempList.put(plName, tempList);
                    }
                    case 6-> pl.closeInventory();
                }
            }

            case TESTINGCN, TESTINGEN ->
            {
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                Inventory invQues = e.getInventory();
                ItemStack item = invQues.getItem(13);
                String queName = Objects.requireNonNull(item).getItemMeta().getDisplayName();
                char[] tempChar = queName.toCharArray();
                int num = tempChar[1];
                char letter = tempChar[2];
                int slot = e.getSlot();
                List<Integer> list = CharTempList.get(plName);
                Languages lang = Localisation.getLanguage(pl);
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
                                        list.set(SANITY.ordinal(), 0);
                                        list.set(BRAVENESS.ordinal(), 0);
                                        list.set(DARKNESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(SANITY.ordinal(), 1);
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
                                        list.set(SANITY.ordinal(), 0);
                                        list.set(DARKNESS.ordinal(), 1);
                                        list.set(BRAVENESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(SANITY.ordinal(), 1);
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
                        int san = list.get(SANITY.ordinal());
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
                                        list.set(SANITY.ordinal(), san);
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
                                        list.set(SANITY.ordinal(), san);
                                        list.set(POSITIVITY.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(SANITY.ordinal(), san);
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
                                        list.set(SANITY.ordinal(), san);
                                    }
                                    case 31 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(POSITIVITY.ordinal(), 0);
                                        list.set(SANITY.ordinal(), san);
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
                                ISWAITED.put(plName, false);
                                NICETRY.put(plName, 0);
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        if(Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "我愿意")
                                        || Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Yes"))
                                        {
                                            if(!ISWAITED.get(plName))
                                            {
                                                switch(lang)
                                                {
                                                    case CN -> PlayerCharacters.createItem(invQues, 29, new ItemStack(Material.GRAY_WOOL), ChatColor.RED + "那你来等等", ChatColor.GRAY + "剩余秒数: ");
                                                    case EN -> PlayerCharacters.createItem(invQues, 29, new ItemStack(Material.GRAY_WOOL), ChatColor.RED + "Then wait actually", ChatColor.GRAY + "Seconds remaining: ");
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
                                                                    case CN -> PlayerCharacters.createItem(invQues, QuestionOptions.OPIA, "我愿意");
                                                                    case EN -> PlayerCharacters.createItem(invQues, QuestionOptions.OPIA, "Yes");
                                                                }
                                                                ISWAITED.put(plName, true);
                                                                NICETRY.remove(plName);
                                                            }
                                                        }
                                                    }
                                                }.runTaskAsynchronously(inst);
                                            }
                                            else
                                            {
                                                ISWAITED.remove(plName);
                                                list.set(PATIENCE.ordinal(), 0);
                                                int next = rand.nextInt(2) + 8;
                                                askQuestion(next, invQues, lang, pl);
                                            }
                                        }
                                        else
                                        {
                                            int haveATry = NICETRY.get(plName);
                                            haveATry++;
                                            NICETRY.put(plName, haveATry);
                                            if(haveATry >= 15)
                                            {
                                                switch(lang)
                                                {
                                                    case CN -> pl.sendMessage(ChatColor.RED + "都说了让你等你不听");
                                                    case EN -> pl.sendMessage(ChatColor.RED + "I said wait but u wasn't listening");
                                                }
                                                pl.closeInventory();
                                            }
                                            else
                                            {
                                                switch(lang)
                                                {
                                                    case CN -> pl.sendMessage(ChatColor.RED + "请耐心等待");
                                                    case EN -> pl.sendMessage(ChatColor.RED + "Please wait");
                                                }
                                            }
                                        }
                                    }

                                    case 33 ->
                                    {
                                        ISWAITED.remove(plName);
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
                                        list.set(HEIGHT.ordinal(), 1);
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
                                        list.set(HEIGHT.ordinal(), 0);
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
                                            list.set(HEIGHT.ordinal(), 1);
                                        }
                                        else
                                        {
                                            list.set(HEIGHT.ordinal(), 0);
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
                                        list.set(HEIGHT.ordinal(), 1);
                                        list.set(BRAVENESS.ordinal(), bra);
                                        list.set(PATIENCE.ordinal(), pat);
                                    }

                                    case 33 ->
                                    {
                                        int rand6 = rand.nextInt(2);
                                        if(rand6 == 0)
                                        {
                                            list.set(HEIGHT.ordinal(), 0);
                                        }
                                        else
                                        {
                                            list.set(HEIGHT.ordinal(), 1);
                                        }
                                    }
                                }
                            }
                        }
                        switch(lang)
                        {
                            case CN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "好,测试完成");
                                pl.sendMessage(ChatColor.GREEN + "结果已经保存,来看看吧");
                            }
                            case EN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "Well done, the test is over");
                                pl.sendMessage(ChatColor.GREEN + "The result has been saved. Have a look!");
                            }
                        }
                        PlayerCharacters.setCharacter(pl, list);
                        pl.closeInventory();
                        PlayerCharacters.setEnable(pl, true);
                        ViewCharacters.viewCharacters(pl);
                    }
                    default ->
                    {
                        pl.closeInventory();
                        switch(lang)
                        {
                            case CN ->
                            {
                                pl.sendMessage(ChatColor.RED + "要么是你改了");
                                pl.sendMessage(ChatColor.RED + "要么是插件坏了");
                                pl.sendMessage(ChatColor.RED + "你看着办吧 =Δ=");
                                
                            }
                            case EN ->
                            {
                                pl.sendMessage(ChatColor.RED + "There must be something wrong");
                                pl.sendMessage(ChatColor.RED + "Either the plugin or yourself");
                                pl.sendMessage(ChatColor.RED + "What do u think =Δ=");
                                
                            }
                        }
                    }
                }
            }
        }
    }
}
