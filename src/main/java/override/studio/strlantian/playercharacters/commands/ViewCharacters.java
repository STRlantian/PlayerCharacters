package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.enums.Languages;

import java.util.List;
import java.util.Objects;

import static override.studio.strlantian.playercharacters.PCFactory.*;
import static override.studio.strlantian.playercharacters.enums.Characters.*;

public final class ViewCharacters
{
    @SuppressWarnings("Deprecation")
    private static Inventory createViewInv(Player pl, Languages language)
    {       //Create inventories for **VIEWING CHARACTERS**
        String name = pl.getName();
        ItemStack consFlag = new ItemStack(Material.PAPER, 1);
        ItemStack featFlag = new ItemStack(Material.PAPER, 1);

        switch(language)
        {
            default -> uDidntInit(pl);
            case CN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + "的性格页面");
                createItem(inv, 0, consFlag, ChatColor.MAGIC + "" + ChatColor.GOLD + name + "所属的基本性格",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.GOLD + "这是你的基本性格");
                createItem(inv, 9, featFlag, ChatColor.MAGIC + "" + ChatColor.GOLD + name + "所属的特殊性格",
                        ChatColor.GOLD + "这是你的特殊性格");
                return inv;
            }
            case EN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + "'s Character Page");
                createItem(inv, 0, consFlag, ChatColor.MAGIC + "" + ChatColor.GOLD + name + "'s basical characters",
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.GOLD + "These are your basical characters");
                createItem(inv, 9, featFlag, ChatColor.MAGIC + "" + ChatColor.GOLD + name + "'s special characters",
                        ChatColor.GOLD + "These are your special characters");
                return inv;
            }
        }
        return Bukkit.createInventory(null, 9, ChatColor.RED + "ERROR |||| 出错了");
    }

    public static void viewCharacters(Player pl)
    {
        List<Integer> list = getCharacterList(pl);
        Languages language = Localisation.getLanguage(pl);
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        Inventory inv = Objects.requireNonNull(createViewInv(pl, language));

        ItemStack hun = SATURATION.getRepresentItem();
        ItemStack ene = ENERGY.getRepresentItem();
        ItemStack hea = HEALTH.getRepresentItem();

        ItemStack san = SANITY.getRepresentItem();
        ItemStack dar = DARKNESS.getRepresentItem();
        ItemStack pos = POSITIVITY.getRepresentItem();
        ItemStack bra = BRAVENESS.getRepresentItem();
        ItemStack kin = KINDNESS.getRepresentItem();
        ItemStack pat = PATIENCE.getRepresentItem();
        ItemStack hig = HEIGHT.getRepresentItem();

        switch(language)
        {
            case CN->
            {
                switch (list.get(0))
                {
                    case 0 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求不高",
                            ChatColor.DARK_GREEN + "饱食度上限少");

                    case 1 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求正常",
                            ChatColor.DARK_GREEN + "饱食度上限中");

                    case 2 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "饱食度- 你对吃饭要求较高",
                            ChatColor.DARK_GREEN + "饱食度上限大");

                }
                switch (list.get(1))
                {
                    case 0 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "能量值- 你体质不好",
                            ChatColor.DARK_BLUE + "能量上限低");

                    case 1 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "能量值- 你体质正常",
                            ChatColor.DARK_BLUE + "能量上限适中");

                    case 2 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "能量值- 你体制超群",
                            ChatColor.DARK_BLUE + "能量上限高");

                }
                switch (list.get(2))
                {
                    case 0 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "健康值- 你不健康",
                            ChatColor.DARK_RED + "健康值上限低");

                    case 1 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "健康值- 你挺健康",
                            ChatColor.DARK_RED + "健康值上限中");

                    case 2 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "健康值- 你很健康",
                            ChatColor.DARK_RED + "健康值上限高");

                }
                switch (list.get(3)) //sanity
                {
                    case 0 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "理智- 你有时不清醒",
                            ChatColor.AQUA + "概率性反胃和急迫效果");

                    case 1 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "理智- 你比较清醒",
                            ChatColor.GRAY + "不受影响");

                    case 2 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "理智- 你沉着冷静",
                            ChatColor.AQUA + "抗性和速度效果");

                }
                switch (list.get(4)) //darkness
                {
                    case 0 -> createItem(inv, 11, dar,
                            ChatColor.BLACK + "黑暗- 你怕黑",
                            ChatColor.BLACK + "在黑暗中速度和虚弱效果");

                    case 1 -> createItem(inv, 11, dar,
                            ChatColor.BLACK + "黑暗- 你不怕黑",
                            ChatColor.GRAY + "不受影响");

                }
                switch (list.get(5)) //positivity
                {
                    case 0 -> createItem(inv, 12, pos,
                            ChatColor.YELLOW + "态度- 你很乐观",
                            ChatColor.YELLOW + "健康值低时力量效果");

                    case 1 -> createItem(inv, 12, pos,
                            ChatColor.YELLOW + "态度- 你较悲观",
                            ChatColor.YELLOW + "健康值低时抗性效果");

                }
                switch (list.get(6)) //braveness
                {
                    case 0 -> createItem(inv, 13, bra,
                            ChatColor.RED + "勇气- 你超勇的(bushi",
                            ChatColor.RED + "持剑时力量效果");

                    case 1 -> createItem(inv, 13, bra,
                            ChatColor.RED + "勇气- 你很逊的啦(bushi",
                            ChatColor.RED + "举盾时速度效果");

                }
                switch (list.get(7)) //Kindness
                {
                    case 0 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "善良- 我劝你善良",
                            ChatColor.BLUE + "健康值少和力量效果");

                    case 1 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "善良- 你是个正常人",
                            ChatColor.BLUE + "钓鱼时幸运效果(我也不知道为什么我想起来这个");

                    case 2 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "善良- 你很善良",
                            ChatColor.BLUE + "健康值少和抗性效果");

                }
                switch (list.get(8)) //persistence
                {
                    case 0 -> createItem(inv, 15, pat,
                            ChatColor.LIGHT_PURPLE + "耐心- 你不仅耐心还坚持不懈",
                            ChatColor.LIGHT_PURPLE + "一些情况下缓慢和抗性效果");

                    case 1 -> createItem(inv, 15, pat,
                            ChatColor.LIGHT_PURPLE + "耐心- 你很急躁,经常没耐心",
                            ChatColor.LIGHT_PURPLE + "一些情况下急迫和消耗能量快效果");

                }
                switch (list.get(9))
                {
                    case 0 -> createItem(inv, 16, hig,
                            ChatColor.RED + "恐高- 你恐高",
                            ChatColor.RED + "高处反胃和速度效果");

                    case 1 -> createItem(inv, 16, hig,
                            ChatColor.RED + "恐高- 你不恐高",
                            ChatColor.GRAY + "不受影响");

                }
                
            }

            case EN->
            {
                switch (list.get(0))
                {
                    case 0 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "Saturability- Low",
                            ChatColor.DARK_GREEN + "Low saturability limit");

                    case 1 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "Saturability- Normal",
                            ChatColor.DARK_GREEN + "Normal saturability limit");

                    case 2 -> createItem(inv, 1, hun,
                            ChatColor.DARK_GREEN + "Saturability- High",
                            ChatColor.DARK_GREEN + "High saturability limit");

                }
                switch (list.get(1))
                {
                    case 0 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "Energy- Low",
                            ChatColor.DARK_BLUE + "Low energy limit");

                    case 1 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "Energy- Normal",
                            ChatColor.DARK_BLUE + "Normal energy limit");

                    case 2 -> createItem(inv, 2, ene,
                            ChatColor.DARK_BLUE + "Energy- High",
                            ChatColor.DARK_BLUE + "High energy limit");

                }
                switch (list.get(2))
                {
                    case 0 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "Health- Low",
                            ChatColor.DARK_RED + "Low health limit");

                    case 1 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "Health- Normal",
                            ChatColor.DARK_RED + "Normal health limit");

                    case 2 -> createItem(inv, 3, hea,
                            ChatColor.DARK_RED + "Health- High",
                            ChatColor.DARK_RED + "High health limit");

                }
                switch (list.get(3)) //sanity
                {
                    case 0 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "Sanity- You are not that clear",
                            ChatColor.AQUA + "[Porobobilitic]Nausea and haste");

                    case 1 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "Sanity- You are clear",
                            ChatColor.GRAY + "No effects");

                    case 2 -> createItem(inv, 10, san,
                            ChatColor.AQUA + "Sanity- You can calm down anytime",
                            ChatColor.AQUA + "Resistance and slowness");

                }
                switch (list.get(4)) //darkness
                {
                    case 0 -> createItem(inv, 11, dar,
                            ChatColor.BLACK + "Darkness- You are afraid of darkness",
                            ChatColor.BLACK + "[In darkness]Weakness and swiftness");

                    case 1 -> createItem(inv, 11, dar,
                            ChatColor.BLACK + "Darkness- You are not afraid of darkness",
                            ChatColor.GRAY + "No effects");

                }
                switch (list.get(5)) //positivity
                {
                    case 0 -> createItem(inv, 12, pos,
                            ChatColor.YELLOW + "Attitude- You are always positive",
                            ChatColor.YELLOW + "[When low health]Regeneration");

                    case 1 -> createItem(inv, 12, pos,
                            ChatColor.YELLOW + "Attitude- You are a little negative",
                            ChatColor.YELLOW + "[When low health]Resistance");

                }
                switch (list.get(6)) //braveness
                {
                    case 0 -> createItem(inv, 13, bra,
                            ChatColor.RED + "Courage- You are kinda brave",
                            ChatColor.RED + "[When holding a sword]Strength");

                    case 1 -> createItem(inv, 13, bra,
                            ChatColor.RED + "Courage- You are not that brave",
                            ChatColor.RED + "[When using a shield]Swiftness");

                }
                switch (list.get(7)) //Kindness
                {
                    case 0 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "Kindness- Be kind please",
                            ChatColor.BLUE + "Low health and strength");

                    case 1 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "Kindness- You are normal",
                            ChatColor.BLUE + "[When fishing]Fortunate (idk why i came up with this");

                    case 2 -> createItem(inv, 14, kin,
                            ChatColor.BLUE + "Kindness- You are kind",
                            ChatColor.BLUE + "Low health and resistance");
                }

                switch (list.get(8)) //persistence
                {
                    case 0 -> createItem(inv, 15, pat,
                            ChatColor.LIGHT_PURPLE + "Persistence- You are patient and persistent enough",
                            ChatColor.LIGHT_PURPLE + "[Some situation]Slowness and resistance");

                    case 1 -> createItem(inv, 15, pat,
                            ChatColor.LIGHT_PURPLE + "Persistence- You are impatient",
                            ChatColor.LIGHT_PURPLE + "[Some situation]Fast energy spending and haste");
                }

                switch (list.get(9))
                {
                    case 0 -> createItem(inv, 16, hig,
                            ChatColor.RED + "Height- You are height-sick",
                            ChatColor.RED + "[At high places]Nausea and swiftness");

                    case 1 -> createItem(inv, 16, hig,
                            ChatColor.RED + "Height- You are not afraid of it",
                            ChatColor.GRAY + "No effects");
                }
                
            }
        }
        pl.openInventory(inv);
    }
}