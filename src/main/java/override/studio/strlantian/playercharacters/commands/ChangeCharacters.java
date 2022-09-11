package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.PCFactory;
import override.studio.strlantian.playercharacters.PlayerStorage;
import override.studio.strlantian.playercharacters.enums.Characters;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static override.studio.strlantian.PlayerCharacters.CN;
import static override.studio.strlantian.PlayerCharacters.EN;
import static override.studio.strlantian.playercharacters.commands.ViewCharacters.VIEWCHARCN;
import static override.studio.strlantian.playercharacters.commands.ViewCharacters.VIEWCHAREN;

public abstract class ChangeCharacters
{
    public static final Map<Player, List<Integer>> TEMPCHANGECHARLIST = new HashMap<>(Collections.emptyMap());
    public static final Map<Player, Integer> POINTMAP = new HashMap<>(Collections.emptyMap());

    private static void alreadyMax(Player pl)
    {
        PlayerStorage ps = new PlayerStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN -> pl.sendMessage(ChatColor.RED + "该值已经达到极限");
            case EN -> pl.sendMessage(ChatColor.RED + "This value reaches the maximum/minimum");
        }
    }

    private static void noPoint(Player pl)
    {
        PlayerStorage ps = new PlayerStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN -> pl.sendMessage(ChatColor.RED + "您  没  分  了");
            case EN -> pl.sendMessage(ChatColor.RED + "You have ran out of points");
        }
    }

    @SuppressWarnings("Deprecation")
    public static void changeCharacters(Player pl)
    {
        PlayerStorage ps = new PlayerStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, pl.getName() + VIEWCHARCN + "-更改页面");
                PCFactory.setItemToInv(inv, 0, new ItemStack(Material.GREEN_WOOL), ChatColor.GOLD + "剩余更改点数", ChatColor.GREEN + "2");
                PCFactory.setItemToInv(inv, 1, new ItemStack(Material.OAK_SIGN), ChatColor.GOLD + "说明:",
                        "左边的点数是你还可以更改的数字", "左键点击以下性格来增加点数 右键来减少", "当点数用完性格将无法更改");
                PCFactory.setItemToInv(inv, 9, new ItemStack(Material.PAPER), ChatColor.GOLD + "你可以更改的性格->",
                        Enchantment.DURABILITY, 1, true);
                ViewCharacters.setSpecialItem(pl, inv);
                pl.openInventory(inv);
            }
            case EN ->
            {
                Inventory inv = Bukkit.createInventory(null, 4 * 9, pl.getName() + VIEWCHAREN + "-Changing Page");
                PCFactory.setItemToInv(inv, 0, new ItemStack(Material.GREEN_WOOL), ChatColor.GOLD + "Points remaining", ChatColor.GREEN + "2");
                PCFactory.setItemToInv(inv, 1, new ItemStack(Material.OAK_SIGN), ChatColor.GOLD + "Tips:",
                        "Item left shows the changing points", "Left-Click the item below to add, Right-Click to reduce", "You can't change anything if you run out points");
                PCFactory.setItemToInv(inv, 9, new ItemStack(Material.PAPER), ChatColor.GOLD + "Changeable characters->",
                        Enchantment.DURABILITY, 1, true);
                ViewCharacters.setSpecialItem(pl, inv);
                pl.openInventory(inv);
            }
        }
    }

    public static void checkAndModify(Player pl, ClickType click, Characters which, List<Integer> what)
    {           //HOW TO REALISE THE POINTS
                //UNDER CONSTRUCTION
        PlayerStorage ps = new PlayerStorage(pl);
        int lang = ps.getLanguage();
        int og = ps.getCharacterList().get(which.ordinal());           //Get Original Number
        int now = what.get(which.ordinal());                                    //Get Target Number
        int point = POINTMAP.get(pl);                                        //Get Point
        int ogMinus = Math.abs(og - now);
        int nowMinus = 0;           //Sooooo how to??????????????????????????????????????

        if(click.isLeftClick())                         //If left click -> plus
        {
            if(now < which.maxValue())                  //If now < max value of the cha
            {
                int temp = Math.abs(og - now - 1);      //If

                now++;
            }
            else
            {
                alreadyMax(pl);
            }
        }
        else if(click.isRightClick())       //If right click -> minus
        {
            if(now > 0)                     //If now > 0
            {
                now--;
            }
            else
            {
                alreadyMax(pl);
            }
        }

        if(ogMinus > nowMinus)
        {
            point++;
            switch(lang)
            {
                case CN -> pl.sendMessage(ChatColor.GREEN + "成功将 " + which.charName(CN)
                        + " 性格从最开始的" + og + "更改为" + now + ",现在还剩下" + point + "分");
                case EN -> pl.sendMessage(ChatColor.GREEN + "Changed " + which.charName(EN)
                        + "from original" + og + "to" + now + "successfully, now you have" + point + "points to use");
            }
        }
        else if(ogMinus < nowMinus)
        {
            if(point <= 0)
            {
                noPoint(pl);
                if(click.isLeftClick())
                {

                }
            }
            else
            {
                point--;
                switch(lang)
                {
                    case CN -> pl.sendMessage(ChatColor.GREEN + "成功将 " + which.charName(CN)
                            + " 性格从最开始的" + og + "更改为" + now + ",现在还剩下" + point + "分");
                    case EN -> pl.sendMessage(ChatColor.GREEN + "Changed " + which.charName(EN)
                            + "from original" + og + "to" + now + "successfully, now you have" + point + "points to use");
                }
            }
        }

        POINTMAP.put(pl, point);
        what.set(which.ordinal(), now);
    }
}
