package Override.Studio.STRlantian.Commands;

import Override.Studio.STRlantian.Localisation;
import Override.Studio.STRlantian.PlayerCharacters;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static Override.Studio.STRlantian.Main.inst;

public final class Character implements TabExecutor
{
    FileConfiguration cfg = inst.getConfig();
    void giveHelp(Player pl, String language)
    {
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        switch(language)
        {
            default->
                Localisation.uDidntSetLanguage(pl);
            case "CN"->
            {
                pl.sendMessage(ChatColor.BLACK + "===================");
                pl.sendMessage(ChatColor.YELLOW + "Δ玩家性格帮助页面Δ");
                pl.sendMessage(ChatColor.AQUA + "第一部分: 简介");
                pl.sendMessage(ChatColor.BLUE + "这让MC的玩家也可以受到性格的影响多是一件美逝啊,所以作者突发奇想就做了这个插件");
                pl.sendMessage(ChatColor.BLUE + "人为填入个人的性格后,你就可以获得相应的buff和debuff,生存变得生动起来");
                pl.sendMessage(ChatColor.BLUE + "但是所谓江山易改 本性难移,如果你不喜欢你的性格,你可以删除或者更改它,你将失去所有buff和debuff");
                pl.sendMessage(ChatColor.BLUE + "更改性格仅有一次机会(见下文),主用于'不小心填错'的事情.");
                pl.sendMessage(ChatColor.AQUA + "第二部分: 指令");
                pl.sendMessage(ChatColor.GREEN + "使用 /character <参数> 指令来进行操作 以下是可以选择的用法");
                pl.sendMessage(ChatColor.GREEN + "1. /character init: 用于首次设置你的性格");
                pl.sendMessage(ChatColor.GREEN + "2. /character view: 用于查看你现在的性格");
                pl.sendMessage(ChatColor.GREEN + "3. /character change: 用于改变你的性格");
                pl.sendMessage(ChatColor.RED + "-注意! 在这个存档中,你的性格只能改变一次. 践行江山易改 本性难移的原则.[非得要改那就去找管理员吧(恼]");
                pl.sendMessage(ChatColor.GREEN + "4. /character delete: 用于删除你的所有性格");
                pl.sendMessage(ChatColor.RED + "-注意! 在这个存档中 一旦你的性格被删除将不可恢复或者重建,因此删除性格需要/character confirm");
                pl.sendMessage(ChatColor.RED + "-注意! 这表示你不会受到任何性格影响,包括buff和debuff");
                pl.sendMessage(ChatColor.GREEN + "5. /character help: 显示此帮助页面");
                pl.sendMessage(ChatColor.GREEN + "6. /character credits: 展示作者(求求看看吧看看我b站)");
                pl.sendMessage(ChatColor.GREEN + "7. /character language: 显示语言调整界面");
                pl.sendMessage(ChatColor.BLACK + "===================");
            }
            case "EN"->
            {
                pl.sendMessage(ChatColor.BLACK + "===================");
                pl.sendMessage(ChatColor.YELLOW + "ΔPlayer Characters' Help PageΔ");
                pl.sendMessage(ChatColor.AQUA + "Part 1: Introduction");
                pl.sendMessage(ChatColor.BLUE + "The author wants Minecarft Players to be influenced with their characters");
                pl.sendMessage(ChatColor.BLUE + "After inputting your characters, you will get your own buff and debuff");
                pl.sendMessage(ChatColor.BLUE + "You can delete or change your characters if you don't like them");
                pl.sendMessage(ChatColor.BLUE + "But you should know that you can only change it for one time.(See below)");
                pl.sendMessage(ChatColor.AQUA + "Part2: Commands");
                pl.sendMessage(ChatColor.GREEN + "Use /character <function> to use this plugin");
                pl.sendMessage(ChatColor.GREEN + "1. /character initialise: Initialise your characters for the first time");
                pl.sendMessage(ChatColor.GREEN + "2. /character view: Viewing your characters");
                pl.sendMessage(ChatColor.GREEN + "3. /character change: Change your characters");
                pl.sendMessage(ChatColor.RED + "-Attention! You have only 1 chance to change them.[Go to ask admin if u r that paranoid to change them]");
                pl.sendMessage(ChatColor.GREEN + "4. /character delete: Delete all the characters");
                pl.sendMessage(ChatColor.RED + "-Attention! You can't recreate characters after deleting. So you need to /character confirm if you want to delete them");
                pl.sendMessage(ChatColor.RED + "-Attention! You will no longer be influenced by characters if you delete them");
                pl.sendMessage(ChatColor.GREEN + "5. /character help: Show this help page");
                pl.sendMessage(ChatColor.GREEN + "6. /character credits: Show author(SEE MY BILIBILI PLEEEAAASE)");
                pl.sendMessage(ChatColor.GREEN + "7. /character language: Show language page");
                pl.sendMessage(ChatColor.BLACK + "===================");
            }
        }
    }

    void giveCredits(Player pl, String language)
    {
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        switch(language)
        {
            default->
                Localisation.uDidntSetLanguage(pl);
            case "CN"->
            {
                pl.sendMessage(ChatColor.GREEN + "作者Minecraft: " + ChatColor.BLUE + "STRlantian");
                pl.sendMessage(ChatColor.YELLOW + "B站id: " + ChatColor.BLUE + "这里是陌蓝qwq");
                pl.sendMessage(ChatColor.DARK_BLUE + "Github主页(建设中): " + ChatColor.BLUE + "strlantian.github.io");
                pl.sendMessage(ChatColor.RED + "Discord: " + ChatColor.BLUE + "STRlantian#5461");
            }
            case "EN"->
            {
                pl.sendMessage(ChatColor.GREEN + "Author's Minecraft username: " + ChatColor.BLUE + "STRlantian");
                pl.sendMessage(ChatColor.YELLOW + "Bilibili: " + ChatColor.BLUE + "这里是陌蓝qwq");
                pl.sendMessage(ChatColor.DARK_BLUE + "Github Page(Under construction): " + ChatColor.BLUE + "strlantian.github.io");
                pl.sendMessage(ChatColor.RED + "Discord: " + ChatColor.BLUE + "STRlantian#5461");
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        Player sd = (Player) commandSender;
        String lang = Localisation.getLanguage(sd);

        switch (strings.length)
        {
            default ->
            {
                sd.sendMessage(ChatColor.RED + "U KNOW THE RULES AND SO DO I");
                return true;
            }
            case 0 ->
            {
                giveHelp(sd, lang);
                return true;
            }
            case 1 ->
            {
                switch (strings[0])
                {
                    case "help"->
                    {
                        giveHelp(sd, lang);
                        return true;
                    }
                    case "credits", "credit"->
                    {
                        giveCredits(sd, lang);
                        return true;
                    }
                    case "language", "lang" ->
                    {
                        sd.openInventory(Localisation.getLanguageInv());
                        return true;
                    }
                    case "view"->
                    {


                    }
                }
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        if(strings.length == 0)
        {
            List<String> list = new java.util.ArrayList<>(Collections.emptyList());
            list.add("language");
            list.add("credits");
            list.add("help");
            list.add("init");
            list.add("change");
            list.add("delete");
            list.add("view");
            list.add("confirm");
            list.add("decline");
            return list;
        }
        else
        {
            return null;
        }
    }
}
