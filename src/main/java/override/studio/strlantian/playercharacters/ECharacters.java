package override.studio.strlantian.playercharacters;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static override.studio.strlantian.playercharacters.ALocalisation.CN;
import static override.studio.strlantian.playercharacters.ALocalisation.EN;

public enum ECharacters
{
    SATURATION(new ItemStack(Material.COOKED_CHICKEN), ChatColor.DARK_GREEN, 2, "饱食度", "Saturation"),
    ENERGY(new ItemStack(Material.SUGAR), ChatColor.DARK_BLUE, 2, "能量值", "Energy"),
    HEALTH(new ItemStack(Material.GOLDEN_CARROT), ChatColor.DARK_RED, 2, "健康值", "Health"),
    PERSEVERANCE(new ItemStack(Material.NAUTILUS_SHELL), ChatColor.AQUA, 2, "坚毅", "Perseverance"),
    DARKNESS(new ItemStack(Material.BLACK_WOOL), ChatColor.BLACK, 1, "怕黑", "Darkness"),
    POSITIVITY(new ItemStack(Material.SLIME_BALL), ChatColor.YELLOW, 1, "态度", "Optimism"),
    BRAVENESS(new ItemStack(Material.WOODEN_SWORD), ChatColor.RED, 1, "勇气", "Courage"),
    KINDNESS(new ItemStack(Material.EGG), ChatColor.BLUE, 2, "善良", "Kindness"),
    PATIENCE(new ItemStack(Material.GOLDEN_BOOTS), ChatColor.LIGHT_PURPLE, 1, "耐心", "Patience"),
    COLDNESS(new ItemStack(Material.SNOWBALL), ChatColor.WHITE, 1, "抗寒", "Coldness"),
    LANGUAGE(),
    CHANGE(),
    ENABLED();

    private ItemStack tempRe;
    private ChatColor tempCo;
    private int maxValue;
    private String cn, en;
    ECharacters(ItemStack represent, ChatColor colour, int maxValue, String cnName, String enName)
    {
        tempRe = represent;
        tempCo = colour;
        this.maxValue = maxValue;
        cn = cnName;
        en = enName;
    }
    ECharacters(){}
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
