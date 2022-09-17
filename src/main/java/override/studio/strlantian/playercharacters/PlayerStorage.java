package override.studio.strlantian.playercharacters;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import override.studio.strlantian.playercharacters.enums.Characters;

import java.util.*;

import static override.studio.strlantian.PlayerCharacters.*;
import static override.studio.strlantian.playercharacters.PCFactory.CHARDISALED;
import static override.studio.strlantian.playercharacters.PCFactory.CHARENABLED;

public final class PlayerStorage
{
    private static final FileConfiguration CFG = inst.getConfig();
    private static final Map<Player, PlayerStorage> STORAGEMAP = new HashMap<>(Collections.emptyMap());
    private final Player PL;
    private List<Integer> charList;
    private int lang;
    private int enable;
    private int changed;
    public static PlayerStorage getStorage(Player pl)
    {
        return STORAGEMAP.get(pl);
    }
    public static void addToStorageMap(Player pl)
    {
        STORAGEMAP.put(pl, new PlayerStorage(pl));
    }
    public static void removeFromStorageMap(Player pl)
    {
        STORAGEMAP.remove(pl);
    }
    public static void clearStorage()
    {
        STORAGEMAP.clear();
    }
    public List<Integer> getCharacterList()
    {
        return charList;
    }
    public void setCharacterList(Characters what, int value)
    {
        charList.set(what.ordinal(), value);
        switch(what)
        {
            case SATURATION -> CFG.set(PCFactory.getPathList(PL).get(Characters.SATURATION.ordinal()), value);
            case ENERGY -> CFG.set(PCFactory.getPathList(PL).get(Characters.ENERGY.ordinal()), value);
            case HEALTH -> CFG.set(PCFactory.getPathList(PL).get(Characters.HEALTH.ordinal()), value);
            case SANITY -> CFG.set(PCFactory.getPathList(PL).get(Characters.SANITY.ordinal()), value);
            case DARKNESS -> CFG.set(PCFactory.getPathList(PL).get(Characters.DARKNESS.ordinal()), value);
            case POSITIVITY -> CFG.set(PCFactory.getPathList(PL).get(Characters.POSITIVITY.ordinal()), value);
            case BRAVENESS -> CFG.set(PCFactory.getPathList(PL).get(Characters.BRAVENESS.ordinal()), value);
            case KINDNESS -> CFG.set(PCFactory.getPathList(PL).get(Characters.KINDNESS.ordinal()), value);
            case PATIENCE -> CFG.set(PCFactory.getPathList(PL).get(Characters.PATIENCE.ordinal()), value);
            case HEIGHT -> CFG.set(PCFactory.getPathList(PL).get(Characters.HEIGHT.ordinal()), value);
        }
    }
    public void setCharacterList(List<Integer> list)
    {
        charList = list;
        int i = 0;
        while(i < 10)
        {
            CFG.set(PCFactory.getPathList(PL).get(i), list.get(i));
            i++;
        }
    }
    public int getLanguage()
    {
        return lang;
    }
    public void setLanguage(int value)
    {
        lang = value;
        CFG.set(PCFactory.getPathList(PL).get(Characters.LANGUAGE.ordinal()), value);
    }
    public int getEnable()
    {
        return enable;
    }
    public void setEnable(int whatNow)
    {
        CFG.set(PCFactory.getPathList(PL).get(Characters.ENABLED.ordinal()), whatNow);
        PlayerStorage ps = PlayerStorage.getStorage(PL);
        int lang = ps.getLanguage();
        if (whatNow == CHARENABLED)
        {
            switch (lang)
            {
                case CN -> PL.sendMessage(ChatColor.GREEN + "你的性格已启用");
                case EN -> PL.sendMessage(ChatColor.GREEN + "Your characters have been enabled");
            }
        }
        else if(whatNow == CHARDISALED)
        {
            removeFromStorageMap(PL);
            switch (lang)
            {
                case CN -> PL.sendMessage(ChatColor.RED + "你的性格已经删除");
                case EN -> PL.sendMessage(ChatColor.RED + "Your characters has been deleted");
            }
        }
    }
    public int getChanged()
    {
        return changed;
    }
    public void setChanged(int value)
    {
        changed = value;
        CFG.set(PCFactory.getPathList(PL).get(Characters.CHANGE.ordinal()), value);
    }
    public void refresh()
    {
        charList = new ArrayList<>(Collections.emptyList());
        for(int i = 0; i < 10; i++)
        {
            charList.set(i, (Integer) CFG.get(PCFactory.getPathList(PL).get(i)));
            i++;
        }
        lang = CFG.getInt(PCFactory.getPathList(PL).get(Characters.LANGUAGE.ordinal()));
        enable = CFG.getInt(PCFactory.getPathList(PL).get(Characters.ENABLED.ordinal()));
        changed = CFG.getInt(PCFactory.getPathList(PL).get(Characters.CHANGE.ordinal()));
    }
    public PlayerStorage(Player pl)
    {
        PL = pl;
        refresh();
    }
}
