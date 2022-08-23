package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.PCFactory;
import override.studio.strlantian.playercharacters.enums.Characters;
import override.studio.strlantian.playercharacters.enums.Languages;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static override.studio.strlantian.playercharacters.commands.ViewCharacters.VIEWCHARCN;
import static override.studio.strlantian.playercharacters.commands.ViewCharacters.VIEWCHAREN;
import static override.studio.strlantian.playercharacters.enums.Characters.SANITY;

public final class ChangeCharacters
{
    public static final Map<Player, Integer> POINTMAP = new HashMap<>(Collections.emptyMap());

    private static void alreadyMax(Player pl)
    {
        Languages lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case CN -> pl.sendMessage(ChatColor.RED + "该值已经达到极限");
            case EN -> pl.sendMessage(ChatColor.RED + "This value is the maximum/minimum");
        }
    }

    public void changeCharacters(Player pl)
    {
        Languages lang = Localisation.getLanguage(pl);
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
        int og = PCFactory.getCharacterList(pl).get(which.ordinal());
        int now = what.get(which.ordinal());
        int pointNow = POINTMAP.get(pl);

        if(click.isLeftClick())
        {
            if(now < which.maxValue())
            {
                now++;
            }
            else
            {
                alreadyMax(pl);
            }
        }
        else if(click.isRightClick())
        {
            if(now > 0)
            {
                now--;
            }
            else
            {
                alreadyMax(pl);
            }
        }
        if(now != og)
        {
            POINTMAP.put(pl, pointNow);
        }
        POINTMAP.put(pl, pointNow);
        what.set(which.ordinal(), now);
    }
}
