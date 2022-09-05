package override.studio.strlantian.playercharacters.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static override.studio.strlantian.PlayerCharacters.CN;
import static override.studio.strlantian.PlayerCharacters.EN;

public enum Characters
{
    SATURATION(new ItemStack(Material.COOKED_CHICKEN), ChatColor.DARK_GREEN, 2, "饱食度", "Saturation"),
    ENERGY(new ItemStack(Material.SUGAR), ChatColor.DARK_BLUE, 2, "能量值", "Energy"),
    HEALTH(new ItemStack(Material.GOLDEN_CARROT), ChatColor.DARK_RED, 2, "健康值", "Health"),
    SANITY(new ItemStack(Material.NAUTILUS_SHELL), ChatColor.AQUA, 2, "理智", "Sanity"),
    DARKNESS(new ItemStack(Material.BLACK_WOOL), ChatColor.BLACK, 1, "怕黑", "Darkfear"),
    POSITIVITY(new ItemStack(Material.SLIME_BALL), ChatColor.YELLOW, 1, "态度", "Attitude"),
    BRAVENESS(new ItemStack(Material.WOODEN_SWORD), ChatColor.RED, 1, "勇气", "Courage"),
    KINDNESS(new ItemStack(Material.EGG), ChatColor.BLUE, 2, "善良", "Kindness"),
    PATIENCE(new ItemStack(Material.GOLDEN_BOOTS), ChatColor.LIGHT_PURPLE, 1, "耐心", "Patience"),
    HEIGHT(new ItemStack(Material.FEATHER), ChatColor.WHITE, 1, "恐高", "Heightfear"),
    LANGUAGE(),
    CHANGE(),
    ENABLED();

    private ItemStack tempRe;
    private ChatColor tempCo;
    private int maxValue;
    private String cn, en;
    Characters(ItemStack represent, ChatColor colour, int maxValue, String cnName, String enName)
    {
        tempRe = represent;
        tempCo = colour;
        this.maxValue = maxValue;
        cn = cnName;
        en = enName;
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
    public String charName(int lang)
    {
        switch(lang)
        {
            case CN ->
            {
                return cn;
            }
            case EN ->
            {
                return en;
            }
            default ->
            {
                return ChatColor.RED + "Error: 错误";
            }
        }
    }
}
