package Override.Studio.STRlantian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Override.Studio.STRlantian.Main.inst;

public final class PlayerCharacters
{
    static FileConfiguration cfg = inst.getConfig();

    static List<String> getPathList(Player pl)
    {
        List<String> list = new ArrayList<>(Collections.emptyList());
        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters";
        String san = (String) cfg.get(cha + "Sanity");
        String dark = (String) cfg.get(cha + "Darkness");
        String pos = (String) cfg.get(cha + "Positive");
        String brave = (String) cfg.get(cha + "Braveness");
        String wat = (String) cfg.get(cha + "Water");
        String pers = (String) cfg.get(cha + "Persistence");
        String hunger = (String) cfg.get(cha + "Hunger");
        String ener = (String) cfg.get(cha + "Energy");
        String health = (String) cfg.get(cha + "Health");
        String high = (String) cfg.get(cha + "High");

        list.set(0, san);
        list.set(1, dark);
        list.set(2, pos);
        list.set(3, brave);
        list.set(4, wat);
        list.set(5, pers);
        list.set(6, hunger);
        list.set(7, ener);
        list.set(8, health);
        list.set(9, high);
        return list;
    }
    public static List<String> getCharacterList(Player pl)
    {
        List<String> list = new java.util.ArrayList<>(Collections.emptyList());

        List<String> pList = getPathList(pl);
        list.set(0, pList.get(0));
        list.set(1, pList.get(1));
        list.set(2, pList.get(2));
        list.set(3, pList.get(3));
        list.set(4, pList.get(4));
        list.set(5, pList.get(5));
        list.set(6, pList.get(6));
        list.set(7, pList.get(7));
        list.set(8, pList.get(8));
        list.set(9, pList.get(9));
        return list;
    }

    public static void initialiseCharacters(Player pl)
    {
        String name = pl.getName().toLowerCase();
        pl.setGameMode(GameMode.SPECTATOR);
        String language = Localisation.getLanguage(pl);

        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "");
            }

            case "EN"->
            {

            }
        }
    }

    public static void viewCharacters(Player pl)
    {
        List<String> list = getCharacterList(pl);
        String language = Localisation.getLanguage(pl);
        String sanV, sanE, darkV, darkE, posV, posE, braveV, braveE,
                watV, watE, persV, persE, hungerV, hungerE, enerV, enerE,
                healthV, healthE, highV, highE;
        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + pl.getName() + "的性格页面");
                switch(list.get(0)) //sanity
                {
                    case "0" ->
                    {
                        pl.sendMessage(ChatColor.AQUA + "1: 你较不理智");
                        pl.sendMessage(ChatColor.AQUA + "   你会受到概率性反胃和力量效果");
                    }
                    case "1" ->
                    {
                        pl.sendMessage(ChatColor.AQUA + "1: 你比较清醒");
                        pl.sendMessage(ChatColor.AQUA + "   你不受该效果影响");
                    }
                    case "2" ->
                    {
                        pl.sendMessage(ChatColor.AQUA + "1: 你谨慎冷静");
                        pl.sendMessage(ChatColor.AQUA + "   你会受到抗性和缓慢效果");
                    }
                }
                switch(list.get(1)) //darkness
                {
                    case "0" ->
                    {
                        pl.sendMessage(ChatColor.BLACK + "2- 你怕黑");
                        pl.sendMessage(ChatColor.BLACK + "   你在黑暗中受到速度和虚弱效果");
                    }
                    case "1" ->
                    {
                        pl.sendMessage(ChatColor.BLACK + "2- 你不怕黑");
                        pl.sendMessage(ChatColor.BLACK + "   你在黑暗中不受影响");
                    }
                }
                switch(list.get(2)) //positivity
                {
                    case "0" ->
                    {
                        pl.sendMessage(ChatColor.YELLOW + "3- 你乐观向上");
                        pl.sendMessage(ChatColor.YELLOW + "   你在健康值低的时候可以生命恢复");
                    }
                    case "1" ->
                    {
                        pl.sendMessage(ChatColor.YELLOW + "3- 你较为悲观");
                        pl.sendMessage(ChatColor.YELLOW + "   你在健康值低的时候可以抗性提升");
                    }
                }
                switch(list.get(3)) //braveness
                {
                    case "0" ->
                    {
                        pl.sendMessage(ChatColor.RED + "4- 你超勇的(bushi");
                        pl.sendMessage(ChatColor.RED + "   当你拿剑或斧的时候提升力量");
                    }
                    case "1" ->
                    {
                        pl.sendMessage(ChatColor.RED + "4- 你很逊(bushi");
                        pl.sendMessage(ChatColor.RED + "   当你持盾牌的时候提升速度");
                    }
                }
                switch(list.get(4)) //water
                {
                    case "0" ->
                    {
                        pl.sendMessage(ChatColor.BLUE + "5- 你水性不好");
                        pl.sendMessage(ChatColor.BLUE + "   在水中憋气时间较短,速度慢且获得力量");
                    }
                    case "1" ->
                    {
                        pl.sendMessage(ChatColor.BLUE + "6- 你只是会游泳");
                        pl.sendMessage(ChatColor.BLUE + "   你在水中不受影响");
                    }
                    case "2" ->
                    {
                        pl.sendMessage(ChatColor.BLUE + "7- 你就是个水猴子(bushi");
                        pl.sendMessage(ChatColor.BLUE + "   在水中憋气时间短,速度快");
                    }
                }
                switch(list.get(5)) //persistence
                {
                    case "0" ->
                    {
                        persV = "持之以恒,有耐心";
                        persE = "缓慢和抗性";
                    }
                    case "1" ->
                    {
                        persV = "难以平静,急躁";
                        persE = "急迫和易累";
                    }
                }
                switch(list.get(6))
                {
                    case "0" ->
                    {
                        hungerV = "要求不多";
                        hungerE = "饱食上限少,吃的慢";
                    }
                    case "1" ->
                    {
                        hungerV = "饭量正常";
                        hungerE = "不受影响";
                    }
                    case "2" ->
                    {
                        hungerV = "度量较大";
                        hungerE = "饱食上限高,吃的快";
                    }
                }
                switch(list.get(7))
                {
                    case "0" ->
                    {
                        enerV = "能量虚弱";
                        enerE = "能量值上限低";
                    }
                    case "1" ->
                    {
                        enerV = "精力适中";
                        enerE = "能量值上限中";
                    }
                    case "2" ->
                    {
                        enerV = "精力充沛";
                        enerE = "能量值上限高";
                    }
                }
                switch(list.get(8))
                {
                    case "0" ->
                    {
                        enerV = "健康值低";
                        enerE = "健康值上限低";
                    }
                    case "1" ->
                    {
                        enerV = "健康值正常";
                        enerE = "不受影响";
                    }
                    case "2" ->
                    {
                        enerV = "血厚";
                        enerE = "健康值上限高";
                    }
                }
                switch(list.get(9))
                {
                    case "0" ->
                    {
                        highV = "恐高";
                        highE = "高处反胃和抗性";
                    }
                    case "1" ->
                    {
                        highV = "不恐高";
                        highE = "不受影响";
                    }
                }

                pl.sendMessage(ChatColor.GREEN + pl.getName() + "的性格页");
            }

            case "EN"->
            {
                switch(list.get(0)) //sanity
                {
                    case "0" ->
                    {
                        sanV = "Low";
                        sanE = "概率性反胃和力量";
                    }
                    case "1" ->
                    {
                        sanV = "正常";
                        sanE = "无增益";
                    }
                    case "2" ->
                    {
                        sanV = "高";
                        sanE = "缓慢和抗性";
                    }
                }
                switch(list.get(1)) //darkness
                {
                    case "0" ->
                    {
                        darkV = "怕";
                        darkE = "黑暗中速度和虚弱";
                    }
                    case "1" ->
                    {
                        darkV = "不怕";
                        darkE = "不受影响";
                    }
                }
                switch(list.get(2)) //positivity
                {
                    case "0" ->
                    {
                        posV = "乐观";
                        posE = "健康值低时生命恢复";
                    }
                    case "1" ->
                    {
                        posV = "悲观";
                        posE = "健康值低时抗性提升";
                    }
                }
                switch(list.get(3)) //braveness
                {
                    case "0" ->
                    {
                        braveV = "勇气很足";
                        braveE = "持剑伤害吸收";
                    }
                    case "1" ->
                    {
                        braveV = "较为胆小";
                        braveE = "举盾速度提升";
                    }
                }
                switch(list.get(4)) //water
                {
                    case "0" ->
                    {
                        watV = "善于游泳";
                        watE = "水下速度但氧气略少";
                    }
                    case "1" ->
                    {
                        watV = "游泳一般";
                        watE = "没有影响";
                    }
                    case "2" ->
                    {
                        watV = "不善游泳";
                        watE = "水下力量但速度慢";
                    }
                }
                switch(list.get(5)) //persistence
                {
                    case "0" ->
                    {
                        persV = "持之以恒,有耐心";
                        persE = "缓慢和抗性";
                    }
                    case "1" ->
                    {
                        persV = "难以平静,急躁";
                        persE = "急迫和易累";
                    }
                }
                switch(list.get(6))
                {
                    case "0" ->
                    {
                        hungerV = "要求不多";
                        hungerE = "饱食上限少,吃的慢";
                    }
                    case "1" ->
                    {
                        hungerV = "饭量正常";
                        hungerE = "不受影响";
                    }
                    case "2" ->
                    {
                        hungerV = "度量较大";
                        hungerE = "饱食上限高,吃的快";
                    }
                }
                switch(list.get(7))
                {
                    case "0" ->
                    {
                        enerV = "能量虚弱";
                        enerE = "能量值上限低";
                    }
                    case "1" ->
                    {
                        enerV = "精力适中";
                        enerE = "能量值上限中";
                    }
                    case "2" ->
                    {
                        enerV = "精力充沛";
                        enerE = "能量值上限高";
                    }
                }
                switch(list.get(8))
                {
                    case "0" ->
                    {
                        enerV = "健康值低";
                        enerE = "健康值上限低";
                    }
                    case "1" ->
                    {
                        enerV = "健康值正常";
                        enerE = "不受影响";
                    }
                    case "2" ->
                    {
                        enerV = "血厚";
                        enerE = "健康值上限高";
                    }
                }
                switch(list.get(9))
                {
                    case "0" ->
                    {
                        highV = "恐高";
                        highE = "高处反胃和抗性";
                    }
                    case "1" ->
                    {
                        highV = "不恐高";
                        highE = "不受影响";
                    }
                }
            }
        }

    }
}
