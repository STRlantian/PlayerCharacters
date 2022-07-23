package OverrideStudio.STRlantian.PlayerCharacters;

import OverrideStudio.STRlantian.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class PlayerCharacters
{
    static FileConfiguration cfg = Main.inst.getConfig(); //Config
    public enum Characters   //Characters Enum
    {
        SATURATION,
        ENERGY,
        HEALTH,
        SANITY,
        DARKNESS,
        POSITIVITY,
        BRAVENESS,
        KINDNESS,
        PATIENCE,
        HEIGHT
    }

    public static List<String> getPathList(Player pl)  //Character Path
    {
        List<String> list = new ArrayList<>(Collections.emptyList());

        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters.";
        String language = name + ".Language";
        String change = name + ".ChangingTime";

        String satu = cha + "Saturation";
        String ene = cha + "Energy";
        String heal = cha + "Health";
        String san = cha + "Sanity";
        String dark = cha + "Darkness";
        String posi = cha + "Positivity";
        String cour = cha + "Braveness";
        String kind = cha + "Kindness";
        String pat = cha + "Patience";
        String high = cha + "Height";

        list.set(0, satu);
        list.set(1, ene);
        list.set(2, heal);
        list.set(3, san);
        list.set(4, dark);
        list.set(5, posi);
        list.set(6, cour);
        list.set(7, kind);
        list.set(8, pat);
        list.set(9, high);
        list.set(10, language);
        list.set(11, change);

        return list;
    }

    public static void uDidntInit(Player pl) //When someone hasn't initialised
    {
        String language = Localisation.getLanguage(pl);
        switch(language)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.RED + "你还没初始化你的性格");
                pl.sendMessage(ChatColor.RED + "使用/character init");
            }
            case "EN"->
            {
                pl.sendMessage(ChatColor.RED + "You haven't initialise your characters");
                pl.sendMessage(ChatColor.RED + "Please use /character init");
            }
        }
    }

    public static List<String> getCharacterList(Player pl) //Get Characters
    {
        List<String> list = new ArrayList<>(Collections.emptyList());
        String name = pl.getName().toLowerCase();
        String cha = name + ".Characters";

        String hunger = (String) cfg.get(getPathList(pl).get(0));
        String ener = (String) cfg.get(getPathList(pl).get(1));
        String health = (String) cfg.get(getPathList(pl).get(2));
        String san = (String) cfg.get(getPathList(pl).get(3));
        String dark = (String) cfg.get(getPathList(pl).get(4));
        String pos = (String) cfg.get(getPathList(pl).get(5));
        String brave = (String) cfg.get(getPathList(pl).get(6));
        String kind = (String) cfg.get(getPathList(pl).get(7));
        String pat = (String) cfg.get(getPathList(pl).get(8));
        String high = (String) cfg.get(getPathList(pl).get(9));

        list.set(0, hunger);
        list.set(1, ener);
        list.set(2, health);
        list.set(3, san);
        list.set(4, dark);
        list.set(5, pos);
        list.set(6, brave);
        list.set(7, kind);
        list.set(8, pat);
        list.set(9, high);
        return list;
    }


    public static void addChangingTime(Player pl)
    {         //When the changing time changes
        String name = pl.getName().toLowerCase();
        String origin = Objects.requireNonNull((String) cfg.get(getPathList(pl).get(11)));
        int og = Integer.parseInt(origin);
        int a = og + 1;
        cfg.set(getPathList(pl).get(11), a);
    }
    public static void setCharacter(Player pl, Characters what, int value)
    {      //Set characters
        switch(what)
        {
            case SATURATION ->
                    cfg.set(getPathList(pl).get(0), value);
            case ENERGY ->
                    cfg.set(getPathList(pl).get(1), value);
            case HEALTH ->
                    cfg.set(getPathList(pl).get(2), value);
            case SANITY ->
                    cfg.set(getPathList(pl).get(3), value);
            case DARKNESS ->
                    cfg.set(getPathList(pl).get(4), value);
            case POSITIVITY ->
                    cfg.set(getPathList(pl).get(5), value);
            case BRAVENESS ->
                    cfg.set(getPathList(pl).get(6), value);
            case KINDNESS ->
                    cfg.set(getPathList(pl).get(7), value);
            case PATIENCE ->
                    cfg.set(getPathList(pl).get(8), value);
            case HEIGHT ->
                    cfg.set(getPathList(pl).get(9), value);
        }
    }

    public static void randCharacters(Player pl)
    { //Full-Randomly get characters
        String lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case "CN"->
                pl.sendMessage(ChatColor.YELLOW + "抽取中");
            case "EN"->
                pl.sendMessage(ChatColor.YELLOW + "Rolling your characters");
        }
        final ThreadLocalRandom RAND = ThreadLocalRandom.current();
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.SATURATION, RAND.nextInt(3));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.ENERGY, RAND.nextInt(3));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.HEALTH, RAND.nextInt(3));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.SANITY, RAND.nextInt(3));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.DARKNESS, RAND.nextInt(2));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.POSITIVITY, RAND.nextInt(2));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.BRAVENESS, RAND.nextInt(2));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.KINDNESS, RAND.nextInt(3));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.PATIENCE, RAND.nextInt(2));
        PlayerCharacters.setCharacter(pl, PlayerCharacters.Characters.HEIGHT, RAND.nextInt(2));
        switch(lang)
        {
            case "CN"->
                    pl.sendMessage(ChatColor.GREEN + "已完成,如图是结果(不可更改)");
            case "EN"->
                    pl.sendMessage(ChatColor.GREEN + "Finished. Here's your result(Can't be changed)");
        }
        ViewCharacters.viewCharacters(pl);
    }

    public static void testCharacters(Player pl)
    {
        String lang = Localisation.getLanguage(pl);
        switch(lang)
        {
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "你将会收到一个简短的小调查");
                pl.sendMessage(ChatColor.GREEN + "最多花费5分钟时间");
                pl.sendMessage(ChatColor.YELLOW + "建议在一个安全的地方做,否则不负责");
                pl.sendMessage(ChatColor.RED + "如果你关掉测试窗口...欸对哦关掉会怎样我没试");

            }

            case "EN"->
            {
                pl.sendMessage(ChatColor.GREEN + "You will receive a short survey");
                pl.sendMessage(ChatColor.GREEN + "It will take you about 5 minutes");
                pl.sendMessage(ChatColor.YELLOW + "I suggest you doing this in a safe place to avoid any possible accidents");
                pl.sendMessage(ChatColor.RED + "If you close the page...Ops i forgot to test it lol");
            }
        }
    }
}
