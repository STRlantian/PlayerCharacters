package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.PCFactory;
import override.studio.strlantian.playercharacters.enums.Languages;

import static override.studio.strlantian.PlayerCharacters.inst;

public final class DeleteCharacters
{
    static FileConfiguration cfg = inst.getConfig();
    private static void itIsDisabled(Player pl)
    {
        Languages lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case CN ->
            {
                pl.sendMessage(ChatColor.RED + "你已经删过了,都说了恢复不了就别试了");
                pl.sendMessage(ChatColor.RED + "如果你确信这是个错误那么就联系管理员吧");
            }
            case EN ->
            {
                pl.sendMessage(ChatColor.RED + "You have deleted them so don't try to recover");
                pl.sendMessage(ChatColor.RED + "Contact admin if you believe this is an error");
            }
        }
    }
    public static final String DELCONFIRMCN = "你确定要删除吗";
    public static final String DELCONFIRMEN = "Are you sure about deleting";
    @SuppressWarnings("Deprecation")
    public static void confirmDelete(Player pl)
    {
        Languages lang = Localisation.getLanguage(pl);
        if(PCFactory.getEnable(pl) == PCFactory.CHARDISALED)
        {
            itIsDisabled(pl);
        }
        else if(PCFactory.getEnable(pl) == PCFactory.CHARENABLED)
        {
            switch(lang)
            {
                case CN ->
                {
                    Inventory inv = Bukkit.createInventory(null, 9, DELCONFIRMCN);
                    PCFactory.setItemToInv(inv, 3, new ItemStack(Material.GREEN_WOOL), ChatColor.GREEN + "嗯 我确定删除");
                    PCFactory.setItemToInv(inv, 5, new ItemStack(Material.RED_WOOL), ChatColor.RED + "不 我点错了");
                    pl.openInventory(inv);
                }
                case EN ->
                {
                    Inventory inv = Bukkit.createInventory(null, 9, DELCONFIRMEN);
                    PCFactory.setItemToInv(inv, 3, new ItemStack(Material.GREEN_WOOL), ChatColor.GREEN + "Yes I want to delete them");
                    PCFactory.setItemToInv(inv, 5, new ItemStack(Material.RED_WOOL), ChatColor.RED + "No I sent the wrong command");
                    pl.openInventory(inv);
                }
            }
        }
        else
        {
            PCFactory.uDidntInit(pl);
        }
    }

    public static void deleteCharacters(Player pl)
    {
        String name = pl.getName().toLowerCase();
        if(PCFactory.getEnable(pl) == PCFactory.CHARENABLED)
        {
            PCFactory.setEnable(pl, PCFactory.CHARDISALED);
            if(cfg.contains(name))
            {
                cfg.set(name + ".Characters", "");
            }
        }
        else if(PCFactory.getEnable(pl) == PCFactory.CHARDISALED)
        {
            itIsDisabled(pl);
        }
        else
        {
            PCFactory.uDidntInit(pl);
        }
    }
}
