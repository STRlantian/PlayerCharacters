package override.studio.strlantian.playercharacters.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Characters
{
    SATURATION(new ItemStack(Material.COOKED_CHICKEN), ChatColor.DARK_GREEN, 2),
    ENERGY(new ItemStack(Material.SUGAR), ChatColor.DARK_BLUE, 2),
    HEALTH(new ItemStack(Material.GOLDEN_CARROT), ChatColor.DARK_RED, 2),
    SANITY(new ItemStack(Material.NAUTILUS_SHELL), ChatColor.AQUA, 2),
    DARKNESS(new ItemStack(Material.BLACK_WOOL), ChatColor.BLACK, 1),
    POSITIVITY(new ItemStack(Material.SLIME_BALL), ChatColor.YELLOW, 1),
    BRAVENESS(new ItemStack(Material.WOODEN_SWORD), ChatColor.RED, 1),
    KINDNESS(new ItemStack(Material.EGG), ChatColor.BLUE, 2),
    PATIENCE(new ItemStack(Material.GOLDEN_BOOTS), ChatColor.LIGHT_PURPLE, 1),
    HEIGHT(new ItemStack(Material.FEATHER), ChatColor.WHITE, 1),
    LANGUAGE(),
    CHANGE(),
    ENABLED();

    private ItemStack tempRe;
    private ChatColor tempCo;
    private int maxValue;
    Characters(ItemStack represent, ChatColor colour, int maxValue)
    {
        tempRe = represent;
        tempCo = colour;
        this.maxValue = maxValue;
    }
    Characters(){}
    public ItemStack repItem()
    {
        return tempRe;
    }

    public ChatColor repColour()
    {
        return tempCo;
    }
    public int maxValue()
    {
        return maxValue;
    }
}
