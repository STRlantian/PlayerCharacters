package override.studio.strlantian.playercharacters.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Characters
{
    SATURATION(new ItemStack(Material.COOKED_CHICKEN)),
    ENERGY(new ItemStack(Material.SUGAR)),
    HEALTH(new ItemStack(Material.GOLDEN_CARROT)),
    SANITY(new ItemStack(Material.NAUTILUS_SHELL)),
    DARKNESS(new ItemStack(Material.BLACK_WOOL)),
    POSITIVITY(new ItemStack(Material.SLIME_BALL)),
    BRAVENESS(new ItemStack(Material.WOODEN_SWORD)),
    KINDNESS(new ItemStack(Material.EGG)),
    PATIENCE(new ItemStack(Material.GOLDEN_BOOTS)),
    HEIGHT(new ItemStack(Material.FEATHER)),
    LANGUAGE(),
    CHANGE(),
    ENABLED();

    private ItemStack tempRe;
    Characters(ItemStack represent)
    {
        tempRe = represent;
    }
    Characters(){}
    public ItemStack getRepresentItem()
    {
        return tempRe;
    }
}
