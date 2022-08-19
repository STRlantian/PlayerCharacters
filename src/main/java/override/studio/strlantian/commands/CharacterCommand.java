package override.studio.strlantian.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import override.studio.strlantian.Main;
import override.studio.strlantian.playercharacters.Localisation;
import override.studio.strlantian.playercharacters.PlayerCharacters;
import override.studio.strlantian.playercharacters.commands.InitialiseCharacters;
import override.studio.strlantian.playercharacters.commands.ViewCharacters;
import override.studio.strlantian.playercharacters.enums.Languages;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class CharacterCommand implements TabExecutor
{
    FileConfiguration cfg = Main.inst.getConfig();

    private void giveHelp(Player pl)
    {
        Languages language = Localisation.getLanguage(pl);
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        switch(language)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.WHITE + "==========================");
                pl.sendMessage(ChatColor.YELLOW + "Δ玩家性格帮助页面Δ");
                pl.sendMessage(ChatColor.AQUA + "第一部分: 简介");
                pl.sendMessage(ChatColor.BLUE + "这让MC的玩家也可以受到性格的影响多是一件美逝啊,就做了这个插件");
                pl.sendMessage(ChatColor.BLUE + "通过性格,你可以获得相应的buff和debuff,生存变得生动起来");
                pl.sendMessage(ChatColor.BLUE + "如果你不喜欢你的性格,可以删除或更改它,将失去buff和debuff");
                pl.sendMessage(ChatColor.BLUE + "更改性格仅有一次机会(见下文),主用于'不小心填错'的事情.");
                pl.sendMessage(ChatColor.AQUA + "第二部分: 指令");
                pl.sendMessage(ChatColor.GREEN + "使用 /character <参数> 指令来进行操作 以下是可以选择的用法");
                pl.sendMessage(ChatColor.GREEN + "1. /character init: " + ChatColor.DARK_GREEN + "用于首次设置你的性格");
                pl.sendMessage(ChatColor.GREEN + "2. /character view: " + ChatColor.DARK_GREEN + "用于查看你现在的性格");
                pl.sendMessage(ChatColor.GREEN + "3. /character change: " + ChatColor.DARK_GREEN + "用于改变你的性格");
                pl.sendMessage(ChatColor.RED + "-注意! 在这个存档中,你的性格只能改变一次. 践行江山易改 本性难移的原则.[非得要改那就去找管理员吧(恼]");
                pl.sendMessage(ChatColor.GREEN + "4. /character delete: " + ChatColor.DARK_GREEN + "用于删除你的所有性格");
                pl.sendMessage(ChatColor.RED + "-注意! 在这个存档中 一旦你的性格被删除将不可恢复或者重建,因此删除性格需要/character confirm");
                pl.sendMessage(ChatColor.RED + "-注意! 这表示你不会受到任何性格影响,包括buff和debuff");
                pl.sendMessage(ChatColor.GREEN + "5. /character help: " + ChatColor.DARK_GREEN + "显示此帮助页面");
                pl.sendMessage(ChatColor.GREEN + "6. /character credits: " + ChatColor.DARK_GREEN + "展示作者(求求看看吧看看我b站)");
                pl.sendMessage(ChatColor.GREEN + "7. /character language: " + ChatColor.DARK_GREEN + "显示语言调整界面");
                pl.sendMessage(ChatColor.GREEN + "8. /character settings: " + ChatColor.DARK_GREEN + "显示性格设置界面" + ChatColor.RED + "(仅管理员)");
                pl.sendMessage(ChatColor.WHITE + "==========================");
            }
            case EN->
            {
                pl.sendMessage(ChatColor.WHITE + "==========================");
                pl.sendMessage(ChatColor.YELLOW + "ΔPlayer Characters' Help PageΔ");
                pl.sendMessage(ChatColor.AQUA + "Part 1: Introduction");
                pl.sendMessage(ChatColor.BLUE + "I want Players to be influenced by the characters");
                pl.sendMessage(ChatColor.BLUE + "You will get your own buff and debuff by them");
                pl.sendMessage(ChatColor.BLUE + "You can delete or change your characters if you don't like");
                pl.sendMessage(ChatColor.BLUE + "But you you can only change it for one time.(See below)");
                pl.sendMessage(ChatColor.AQUA + "Part2: Commands");
                pl.sendMessage(ChatColor.GREEN + "Use /character <function> to use this plugin");
                pl.sendMessage(ChatColor.GREEN + "1. /character initialise: " + ChatColor.DARK_GREEN + "Initialise your characters for the first time");
                pl.sendMessage(ChatColor.GREEN + "2. /character view: " + ChatColor.DARK_GREEN + "Viewing your characters");
                pl.sendMessage(ChatColor.GREEN + "3. /character change: " + ChatColor.DARK_GREEN + "Change your characters");
                pl.sendMessage(ChatColor.RED + "-Attention! You have only 1 chance to change them.[Go to ask admin if u r that paranoid to change them]");
                pl.sendMessage(ChatColor.GREEN + "4. /character delete: " + ChatColor.DARK_GREEN + "Delete all the characters");
                pl.sendMessage(ChatColor.RED + "-Attention! You can't recreate characters after deleting. So you need to /character confirm if you want to delete them");
                pl.sendMessage(ChatColor.RED + "-Attention! You will no longer be influenced by characters if you delete them");
                pl.sendMessage(ChatColor.GREEN + "5. /character help: " + ChatColor.DARK_GREEN + "Show this help page");
                pl.sendMessage(ChatColor.GREEN + "6. /character credits: " + ChatColor.DARK_GREEN + "Show author(SEE MY BILIBILI PLEEEAAASE)");
                pl.sendMessage(ChatColor.GREEN + "7. /character language: " + ChatColor.DARK_GREEN + "Show language page");
                pl.sendMessage(ChatColor.GREEN + "8. /character settings: " + ChatColor.DARK_GREEN + "Show settings' page" + ChatColor.RED + "(Admin only)");
                pl.sendMessage(ChatColor.WHITE + "==========================");
            }
        }
    }

    private void giveCredits(Player pl)
    {
        Languages language = Localisation.getLanguage(pl);
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        switch(language)
        {
            case CN->
            {
                pl.sendMessage(ChatColor.GREEN + "作者Minecraft: " + ChatColor.BLUE + "STRlantian");
                pl.sendMessage(ChatColor.YELLOW + "B站id: " + ChatColor.BLUE + "这里是陌蓝qwq");
                pl.sendMessage(ChatColor.DARK_BLUE + "Github主页(建设中): " + ChatColor.BLUE + "strlantian.github.io");
                pl.sendMessage(ChatColor.RED + "Discord: " + ChatColor.BLUE + "STRlantian#5461");
            }
            case EN->
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
        command.setAliases(new ArrayList<>(Collections.singletonList("cha")));

        Player sd = (Player) commandSender;
        Languages lang = Localisation.getLanguage(sd);

        if(!lang.equals(Languages.CN) && !lang.equals(Languages.EN))
        {
            Localisation.uDidntSetLanguage(sd);
            return true;
        }
        if(PlayerCharacters.getCharacterList(sd).get(0) == null)
        {
            if(!strings[0].equals("language") && !strings[0].equals("lang")
            && !strings[0].equals("help") && !strings[0].equals("credit")
            && !strings[0].equals("credits"))
            {
                PlayerCharacters.uDidntInit(sd);
                return true;
            }
        }

        int changingTime = Objects.requireNonNull((Integer) cfg.get(PlayerCharacters.getPathList(sd).get(11)));
        switch (strings.length)
        {
            default ->
            {

                switch(lang)
                {
                    case CN ->
                    {
                        BaseComponent pleaseDoHelpCN = new TextComponent(ChatColor.RED + "麻烦看看/character help吧");
                        pleaseDoHelpCN.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("看啥,点啊")));
                        pleaseDoHelpCN.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/character help"));
                        sd.sendMessage(pleaseDoHelpCN);
                    }
                    case EN ->
                    {
                        BaseComponent pleaseDoHelpEN = new TextComponent(ChatColor.RED + "Please do /character help");
                        pleaseDoHelpEN.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("What r u waiting for")));
                        pleaseDoHelpEN.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/character help"));
                        sd.sendMessage(pleaseDoHelpEN);
                    }
                }
                
            }
            case 0 -> giveHelp(sd);
            case 1 ->
            {
                switch (strings[0])
                {
                    case "help"-> giveHelp(sd);
                    case "credits", "credit"-> giveCredits(sd);
                    case "language", "lang" -> sd.openInventory(Localisation.getLanguageInv());
                    case "view"-> ViewCharacters.viewCharacters(sd);
                    case "init", "initialise", "initialize"->
                    {
                        if(!Objects.equals(changingTime, 1)
                        && !Objects.equals(changingTime, 0))
                        {
                            InitialiseCharacters.initialiseCharacters(sd);
                        }
                        
                    }

                }
            }
        }
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        if(strings.length == 1)
        {
            return List.of(
                    "language",
                    "credits",
                    "help",
                    "init",
                    "change",
                    "delete",
                    "view",
                    "confirm",
                    "decline");
        }
        else
        {
            return Collections.singletonList(null);
        }
    }
}
