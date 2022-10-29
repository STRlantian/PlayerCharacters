package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.APCFactory;
import override.studio.strlantian.playercharacters.PlayerStorage;
import override.studio.strlantian.playercharacters.ECharacters;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static override.studio.strlantian.playercharacters.ALocalisation.CN;
import static override.studio.strlantian.playercharacters.ALocalisation.EN;
import static override.studio.strlantian.playercharacters.commands.AViewCharacters.VIEWCHARCN;
import static override.studio.strlantian.playercharacters.commands.AViewCharacters.VIEWCHAREN;

public abstract class AChangeCharacters
{
    public static final Map<Player, List<Integer>> TEMPCHANGECHARLIST = new HashMap<>(Collections.emptyMap());
    public static final Map<Player, Integer> POINTMAP = new HashMap<>(Collections.emptyMap());

    private static void alreadyMax(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN -> pl.sendMessage(ChatColor.RED + "该值已经达到极限");
            case EN -> pl.sendMessage(ChatColor.RED + "This value reaches the maximum/minimum");
        }
    }

    private static void noPoint(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
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
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        switch(lang)
        {
            case CN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, pl.getName() + VIEWCHARCN + "-更改页面");
                APCFactory.setItemToInv(inv, 0, new ItemStack(Material.GREEN_WOOL), ChatColor.GOLD + "剩余更改点数", ChatColor.GREEN + "2");
                APCFactory.setItemToInv(inv, 1, new ItemStack(Material.OAK_SIGN), ChatColor.GOLD + "说明:",
                        "左边的点数是你还可以更改的数字", "左键点击以下性格来增加点数 右键来减少", "当点数用完性格将无法更改");
                APCFactory.setItemToInv(inv, 9, new ItemStack(Material.PAPER), ChatColor.GOLD + "你可以更改的性格->",
                        Enchantment.DURABILITY, 1, true);
                AViewCharacters.setSpecialItem(pl, inv);
                pl.openInventory(inv);
            }
            case EN ->
            {
                Inventory inv = Bukkit.createInventory(null, 4 * 9, pl.getName() + VIEWCHAREN + "-Changing Page");
                APCFactory.setItemToInv(inv, 0, new ItemStack(Material.GREEN_WOOL), ChatColor.GOLD + "Points remaining", ChatColor.GREEN + "2");
                APCFactory.setItemToInv(inv, 1, new ItemStack(Material.OAK_SIGN), ChatColor.GOLD + "Tips:",
                        "Item left shows the changing points", "Left-Click the item below to add, Right-Click to reduce", "You can't change anything if you run out points");
                APCFactory.setItemToInv(inv, 9, new ItemStack(Material.PAPER), ChatColor.GOLD + "Changeable characters->",
                        Enchantment.DURABILITY, 1, true);
                AViewCharacters.setSpecialItem(pl, inv);
                pl.openInventory(inv);
            }
        }
    }

    public static void checkAndModify(Player pl, ClickType click, ECharacters which, List<Integer> what)
    {           //HOW TO REALISE THE POINTS
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        List<Integer> ogCharacterList = ps.getCharacterList();
        int ogSumup = 3;
        for(int i : ogCharacterList)
        {
            ogSumup += i;
        }
        
    }
}
