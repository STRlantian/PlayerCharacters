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
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.PlayerCharacters;
import override.studio.strlantian.playercharacters.commandrealisation.InitialiseCharacters;
import override.studio.strlantian.playercharacters.commandrealisation.ViewCharacters;
import override.studio.strlantian.playercharacters.enums.Characters;
import override.studio.strlantian.playercharacters.enums.Languages;
import override.studio.strlantian.playercharacters.enums.QuestionOptions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static override.studio.strlantian.playercharacters.commandrealisation.InitialiseCharacters.*;

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
                pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                switch(lang)
                {
                    case CN-> pl.sendMessage(ChatColor.RED + "你取消了测试,结果将不被保存");
                    case EN-> pl.sendMessage(ChatColor.RED + "You cancelled the test so your characters won't be saved");
                }
                
            }
        }
    }

    private static final Map<String, Boolean> ISWAITED = new HashMap<>(Collections.emptyMap());
    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onClickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView invView = e.getView();
        String title = invView.getTitle();
        String name = pl.getName();
        if(title.equalsIgnoreCase(name + "的性格页面")
        || title.equalsIgnoreCase(name + "'s Character Page"))
        {
            e.setCancelled(true);
        }

        switch(title)
        {
            case InitialiseCharacters.INITITLEMAINCN, InitialiseCharacters.INITITLEMAINEN ->
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
                List<Integer> tempList = InitialiseCharacters.getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();

                switch (slot)
                {
                    case 2->
                    {
                        InitialiseCharacters.testCharacters(pl, tempList);
                        List<Integer> randList = getRandomConstList(pl);
                        CharTempList.put(name, randList);

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
                List<Integer> list = CharTempList.get(name);
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
                                        list.set(Characters.SANITY.ordinal(), 0);
                                        list.set(Characters.BRAVENESS.ordinal(), 0);
                                        list.set(Characters.DARKNESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(Characters.SANITY.ordinal(), 1);
                                        list.set(Characters.BRAVENESS.ordinal(), 1);
                                        list.set(Characters.DARKNESS.ordinal(), 0);
                                    }
                                }
                            }

                            case 'B' ->
                            {
                                switch (slot)
                                {
                                    case 29 ->
                                    {
                                        list.set(Characters.SANITY.ordinal(), 0);
                                        list.set(Characters.DARKNESS.ordinal(), 1);
                                        list.set(Characters.BRAVENESS.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        list.set(Characters.SANITY.ordinal(), 1);
                                        list.set(Characters.DARKNESS.ordinal(), 0);
                                        list.set(Characters.BRAVENESS.ordinal(), 0);
                                    }
                                }
                                
                            }
                        }
                        int next = rand.nextInt(2) + 2;
                        askQuestion(next, invQues, lang, pl);
                    }
                    case 2 ->
                    {
                        int san = list.get(Characters.SANITY.ordinal());
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        if(san > 0)
                                        {
                                            san--;
                                        }
                                        list.set(Characters.SANITY.ordinal(), san);
                                        list.set(Characters.POSITIVITY.ordinal(), 0);
                                    }
                                    case 31 ->
                                    {
                                        if(san > 0)
                                        {
                                            san--;
                                        }
                                        list.set(Characters.SANITY.ordinal(), san);
                                        list.set(Characters.POSITIVITY.ordinal(), 1);
                                    }
                                    case 33 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(Characters.SANITY.ordinal(), san);
                                        list.set(Characters.POSITIVITY.ordinal(), 0);
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
                                        list.set(Characters.POSITIVITY.ordinal(), 1);
                                        list.set(Characters.SANITY.ordinal(), san);
                                    }
                                    case 31 ->
                                    {
                                        if(san < 2)
                                        {
                                            san++;
                                        }
                                        list.set(Characters.POSITIVITY.ordinal(), 0);
                                        list.set(Characters.SANITY.ordinal(), san);
                                    }
                                    case 33 ->
                                    {
                                        int bra = list.get(Characters.BRAVENESS.ordinal());
                                        if(bra == 0)
                                        {
                                            bra++;
                                        }
                                        list.set(Characters.POSITIVITY.ordinal(), 0);
                                        list.set(Characters.BRAVENESS.ordinal(), bra);
                                    }
                                }
                            }
                        }
                        int next = rand.nextInt(2) + 4;
                        askQuestion(next, invQues, lang, pl);
                    }
                    case 3 ->
                    {
                        int next = rand.nextInt(2) + 6;
                        askQuestion(next, invQues, lang, pl);
                        
                    }
                    case 4 ->
                    {
                        switch(letter)
                        {
                            case 'A' ->
                            {
                                ISWAITED.put(name, false);
                                switch(slot)
                                {
                                    case 29 ->
                                    {
                                        if(Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "我愿意"))
                                        {
                                            if(!ISWAITED.get(name))
                                            {
                                                PlayerCharacters.createItem(invQues, 29, new ItemStack(Material.GRAY_WOOL), ChatColor.RED + "那你来等等(点一下重置版)", ChatColor.GRAY + "剩余秒数: ");
                                                for(int i = 60; i >= 0; i--)
                                                {
                                                    ItemStack counter = invQues.getItem(29);
                                                    ItemMeta im = Objects.requireNonNull(counter).getItemMeta();
                                                    List<String> lore = im.getLore();
                                                    String uh = Objects.requireNonNull(lore).get(0);
                                                    String now = uh + i + "秒";
                                                    im.setLore(Collections.singletonList(now));
                                                    counter.setItemMeta(im);
                                                    if(i == 0)
                                                    {
                                                        PlayerCharacters.createItem(invQues, QuestionOptions.OPIA, ChatColor.BLUE + "我愿意");
                                                        ISWAITED.put(name, true);
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                ISWAITED.remove(name);
                                                list.set(Characters.PATIENCE.ordinal(), 0);
                                                int next = rand.nextInt(2) + 8;
                                                askQuestion(next, invQues, lang, pl);
                                            }
                                        }
                                    }

                                    case 33 ->
                                    {
                                        ISWAITED.remove(name);
                                        list.set(Characters.PATIENCE.ordinal(), 1);
                                        int next = rand.nextInt(2) + 8;
                                        askQuestion(next, invQues, lang, pl);
                                    }
                                }
                            }
                            case 'B' ->
                            {
                                int next = rand.nextInt(2) + 8;
                                askQuestion(next, invQues, lang, pl);
                                
                            }
                        }

                    }
                    case 5 ->
                    {
                        switch(lang)
                        {
                            case CN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "好,测试完成");
                                pl.sendMessage(ChatColor.GREEN + "结果已经保存,来看看吧");
                                PlayerCharacters.setEnable(pl, true);
                                ViewCharacters.viewCharacters(pl);
                            }
                            case EN ->
                            {
                                pl.sendMessage(ChatColor.GREEN + "Well done, the test is over");
                                pl.sendMessage(ChatColor.GREEN + "The result has been saved. Have a look!");
                                ViewCharacters.viewCharacters(pl);
                            }
                        }
                    }
                    default ->
                    {
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
