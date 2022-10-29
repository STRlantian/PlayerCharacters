package override.studio.strlantian.playercharacters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import override.studio.strlantian.playercharacters.PlayerStorage;

import java.util.List;
import java.util.Objects;

import static override.studio.strlantian.playercharacters.ALocalisation.CN;
import static override.studio.strlantian.playercharacters.ALocalisation.EN;
import static override.studio.strlantian.playercharacters.APCFactory.*;
import static override.studio.strlantian.playercharacters.ECharacters.*;

public abstract class AViewCharacters
{
    public static final String VIEWCHARCN = "的性格页面";
    public static final String VIEWCHAREN = "'s Character Page";
    @SuppressWarnings("Deprecation")
    private static Inventory createViewInv(Player pl, int language)
    {       //Create inventories for **VIEWING CHARACTERS**
        String name = pl.getName();
        ItemStack consFlag = new ItemStack(Material.PAPER, 1);
        ItemStack featFlag = new ItemStack(Material.PAPER, 1);
        StringBuilder bas = new StringBuilder(ChatColor.MAGIC.toString()).append(ChatColor.GOLD).append(name);

        switch(language)
        {
            default -> uDidntInit(pl);
            case CN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + VIEWCHARCN);
                setItemToInv(inv, 0, consFlag, bas.append("所属的基本性格").toString(),
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.GOLD + "这是你的基本性格");
                setItemToInv(inv, 9, featFlag, bas.append("所属的特殊性格").toString(),
                        ChatColor.GOLD + "这是你的特殊性格");
                return inv;
            }
            case EN ->
            {
                Inventory inv = Bukkit.createInventory(null, 3 * 9, name + VIEWCHAREN);
                setItemToInv(inv, 0, consFlag, bas.append("'s Basical Characters").toString(),
                        Enchantment.DURABILITY, 1, true,
                        ChatColor.GOLD + "These are your basical characters");
                setItemToInv(inv, 9, featFlag, bas.append("'s Special Characters").toString(),
                        ChatColor.GOLD + "These are your special characters");
                return inv;
            }
        }
        return Bukkit.createInventory(null, 9, ChatColor.RED + "ERROR || 出错了");
    }

    //我没怎么在意代码美观性所以就一个private一个public了（（（（（
    private static void setBasicalItem(Player pl, Inventory inv)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        List<Integer> list = ps.getCharacterList();

        ItemStack hun = SATURATION.repItem();
        ItemStack ene = ENERGY.repItem();
        ItemStack hea = HEALTH.repItem();

        switch(lang)
        {
            case CN ->
            {
                switch (list.get(0))
                {
                    case 0 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "饱食度- 你对吃饭要求不高",
                            SATURATION.repColour() + "饱食度上限少");

                    case 1 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "饱食度- 你对吃饭要求正常",
                            SATURATION.repColour() + "饱食度上限中");

                    case 2 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "饱食度- 你对吃饭要求较高",
                            SATURATION.repColour() + "饱食度上限大");
                }
                switch (list.get(1))
                {
                    case 0 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "能量值- 你体质不好",
                            ENERGY.repColour() + "能量上限低");

                    case 1 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "能量值- 你体质正常",
                            ENERGY.repColour() + "能量上限适中");

                    case 2 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "能量值- 你体制超群",
                            ENERGY.repColour() + "能量上限高");
                }
                switch (list.get(2))
                {
                    case 0 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "健康值- 你不健康",
                            HEALTH.repColour() + "健康值上限低");

                    case 1 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "健康值- 你挺健康",
                            HEALTH.repColour() + "健康值上限中");

                    case 2 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "健康值- 你很健康",
                            HEALTH.repColour() + "健康值上限高");
                }
            }
            case EN ->
            {
                switch (list.get(0))
                {
                    case 0 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "Saturability- Low",
                            SATURATION.repColour() + "Low saturability limit");

                    case 1 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "Saturability- Normal",
                            SATURATION.repColour() + "Normal saturability limit");

                    case 2 -> setItemToInv(inv, 1, hun,
                            SATURATION.repColour() + "Saturability- High",
                            SATURATION.repColour() + "High saturability limit");
                }
                switch (list.get(1))
                {
                    case 0 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "Energy- Low",
                            ENERGY.repColour() + "Low energy limit");

                    case 1 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "Energy- Normal",
                            ENERGY.repColour() + "Normal energy limit");

                    case 2 -> setItemToInv(inv, 2, ene,
                            ENERGY.repColour() + "Energy- High",
                            ENERGY.repColour() + "High energy limit");
                }
                switch (list.get(2))
                {
                    case 0 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "Health- Low",
                            HEALTH.repColour() + "Low health limit");

                    case 1 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "Health- Normal",
                            HEALTH.repColour() + "Normal health limit");

                    case 2 -> setItemToInv(inv, 3, hea,
                            HEALTH.repColour() + "Health- High",
                            HEALTH.repColour() + "High health limit");
                }
            }
        }
        pl.openInventory(inv);
    }
    public static void setSpecialItem(Player pl, Inventory inv)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        List<Integer> list = ps.getCharacterList();

        ItemStack san = PERSEVERANCE.repItem();
        ItemStack dar = DARKNESS.repItem();
        ItemStack pos = POSITIVITY.repItem();
        ItemStack bra = BRAVENESS.repItem();
        ItemStack kin = KINDNESS.repItem();
        ItemStack pat = PATIENCE.repItem();
        ItemStack hig = COLDNESS.repItem();

        switch(lang)
        {
            case CN->
            {
                switch (list.get(3)) //sanity
                {
                    case SANITY_LOW -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "坚毅- 善于坚持不懈",
                            PERSEVERANCE.repColour() + "在硬扛的情况下特殊效果");

                    case 1 -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "坚毅- 你是个正常人",
                            ChatColor.GRAY + "不受影响");

                    case 2 -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "坚毅- 你意志力不强()",
                            PERSEVERANCE.repColour() + "在硬扛的情况下特殊效果");
                }
                switch (list.get(4)) //darkness
                {
                    case 0 -> setItemToInv(inv, 11, dar,
                            DARKNESS.repColour() + "黑暗- 你怕黑",
                            DARKNESS.repColour() + "在黑暗中速度和虚弱效果");

                    case 1 -> setItemToInv(inv, 11, dar,
                            DARKNESS.repColour() + "黑暗- 你不怕黑",
                            ChatColor.GRAY + "不受影响");
                }
                switch (list.get(5)) //positivity
                {
                    case 0 -> setItemToInv(inv, 12, pos,
                            POSITIVITY.repColour() + "态度- 你很乐观",
                            POSITIVITY.repColour() + "健康值低时力量效果");

                    case 1 -> setItemToInv(inv, 12, pos,
                            POSITIVITY.repColour() + "态度- 你较悲观",
                            POSITIVITY.repColour() + "健康值低时抗性效果");
                }
                switch (list.get(6)) //braveness
                {
                    case 0 -> setItemToInv(inv, 13, bra,
                            BRAVENESS.repColour() + "勇气- 你超勇的(bushi",
                            BRAVENESS.repColour() + "持剑时力量效果");

                    case 1 -> setItemToInv(inv, 13, bra,
                            BRAVENESS.repColour() + "勇气- 你很逊的啦(bushi",
                            BRAVENESS.repColour() + "举盾时速度效果");
                }
                switch (list.get(7)) //Kindness
                {
                    case 0 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "善良- 我劝你善良",
                            KINDNESS.repColour() + "健康值少和力量效果");

                    case 1 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "善良- 你是个正常人",
                            KINDNESS.repColour() + "钓鱼时幸运效果(我也不知道为什么我想起来这个");

                    case 2 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "善良- 你很善良",
                            KINDNESS.repColour() + "健康值少和抗性效果");
                }
                switch (list.get(8)) //persistence
                {
                    case 0 -> setItemToInv(inv, 15, pat,
                            PATIENCE.repColour() + "耐心- 你不仅耐心还坚持不懈",
                            PATIENCE.repColour() + "一些情况下缓慢和抗性效果");

                    case 1 -> setItemToInv(inv, 15, pat,
                            PATIENCE.repColour() + "耐心- 你很急躁,经常没耐心",
                            PATIENCE.repColour() + "一些情况下急迫和消耗能量快效果");
                }
                switch (list.get(9))
                {
                    case 0 -> setItemToInv(inv, 16, hig,
                            COLDNESS.repColour() + "抗寒- 你怕冷",
                            COLDNESS.repColour() + "太冷的时候虚弱和速度效果");

                    case 1 -> setItemToInv(inv, 16, hig,
                            COLDNESS.repColour() + "抗寒- 你不怕冷",
                            ChatColor.GRAY + "不受影响");
                }
            }

            case EN->
            {
                switch (list.get(3))
                {
                    case 0 -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "Perseverance- You got this",
                            PERSEVERANCE.repColour() + "Mystery effects");

                    case 1 -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "Perseverance- Normal",
                            ChatColor.GRAY + "No effects");

                    case 2 -> setItemToInv(inv, 10, san,
                            PERSEVERANCE.repColour() + "Perseverance- You are not",
                            PERSEVERANCE.repColour() + "Mystery effects");
                }
                switch (list.get(4)) //darkness
                {
                    case 0 -> setItemToInv(inv, 11, dar,
                            DARKNESS.repColour() + "Darkness- You are afraid of darkness",
                            DARKNESS.repColour() + "[In darkness]Weakness and swiftness");

                    case 1 -> setItemToInv(inv, 11, dar,
                            DARKNESS.repColour() + "Darkness- You are not afraid of darkness",
                            ChatColor.GRAY + "No effects");
                }
                switch (list.get(5)) //positivity
                {
                    case 0 -> setItemToInv(inv, 12, pos,
                            POSITIVITY.repColour() + "Optimism- You are always positive",
                            POSITIVITY.repColour() + "[When low health]Regeneration");

                    case 1 -> setItemToInv(inv, 12, pos,
                            POSITIVITY.repColour() + "Optimism- You are a little negative",
                            POSITIVITY.repColour() + "[When low health]Resistance");
                }
                switch (list.get(6)) //braveness
                {
                    case 0 -> setItemToInv(inv, 13, bra,
                            BRAVENESS.repColour() + "Courage- You are kinda brave",
                            BRAVENESS.repColour() + "[When holding a sword]Strength");

                    case 1 -> setItemToInv(inv, 13, bra,
                            BRAVENESS.repColour() + "Courage- You are not that brave",
                            BRAVENESS.repColour() + "[When using a shield]Swiftness");
                }
                switch (list.get(7)) //Kindness
                {
                    case 0 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "Kindness- Be kind please",
                            KINDNESS.repColour() + "Low health and strength");

                    case 1 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "Kindness- You are normal",
                            KINDNESS.repColour() + "[When fishing]Fortunate (idk why i came up with this");

                    case 2 -> setItemToInv(inv, 14, kin,
                            KINDNESS.repColour() + "Kindness- You are kind",
                            KINDNESS.repColour() + "Low health and resistance");
                }

                switch (list.get(8)) //persistence
                {
                    case 0 -> setItemToInv(inv, 15, pat,
                            PATIENCE.repColour() + "Persistence- You are patient and persistent enough",
                            PATIENCE.repColour() + "[Some situation]Slowness and resistance");

                    case 1 -> setItemToInv(inv, 15, pat,
                            PATIENCE.repColour() + "Persistence- You are impatient",
                            PATIENCE.repColour() + "[Some situation]Fast energy spending and haste");
                }

                switch (list.get(9))
                {
                    case 0 -> setItemToInv(inv, 16, hig,
                            COLDNESS.repColour() + "Height- You are height-sick",
                            COLDNESS.repColour() + "[At high places]Nausea and swiftness");

                    case 1 -> setItemToInv(inv, 16, hig,
                            COLDNESS.repColour() + "Height- You are not afraid of it",
                            ChatColor.GRAY + "No effects");
                }
            }
        }
        pl.openInventory(inv);
    }
    public static void viewCharacters(Player pl)
    {
        PlayerStorage ps = PlayerStorage.getStorage(pl);
        int lang = ps.getLanguage();
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        Inventory inv = Objects.requireNonNull(createViewInv(pl, lang));
        setBasicalItem(pl, inv);
        setSpecialItem(pl, inv);
    }
}