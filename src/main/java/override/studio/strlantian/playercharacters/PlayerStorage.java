package override.studio.strlantian.playercharacters;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

import static override.studio.strlantian.PlayerCharacters.inst;
import static override.studio.strlantian.playercharacters.APCFactory.CHARDISALED;
import static override.studio.strlantian.playercharacters.APCFactory.CHARENABLED;

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
    public int getCharacter(ECharacters which)
    {
        return charList.get(which.ordinal());
    }
    public void setCharacterList(ECharacters what, int value)
    {
        charList.set(what.ordinal(), value);
        switch(what)
        {
            case SATURATION -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.SATURATION.ordinal()), value);
            case ENERGY -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.ENERGY.ordinal()), value);
            case HEALTH -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.HEALTH.ordinal()), value);
            case PERSEVERANCE -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.PERSEVERANCE.ordinal()), value);
            case DARKNESS -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.DARKNESS.ordinal()), value);
            case POSITIVITY -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.POSITIVITY.ordinal()), value);
            case BRAVENESS -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.BRAVENESS.ordinal()), value);
            case KINDNESS -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.KINDNESS.ordinal()), value);
            case PATIENCE -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.PATIENCE.ordinal()), value);
            case COLDNESS -> CFG.set(APCFactory.getPathList(PL).get(ECharacters.COLDNESS.ordinal()), value);
        }
    }
    public void setCharacterList(List<Integer> list)
    {
        charList = list;
        int i = 0;
        while(i < 10)
        {
            CFG.set(APCFactory.getPathList(PL).get(i), list.get(i));
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
        CFG.set(APCFactory.getPathList(PL).get(ECharacters.LANGUAGE.ordinal()), value);
    }
    public int getEnable()
    {
        return enable;
    }
    public void setEnable(int whatNow)
    {
        CFG.set(APCFactory.getPathList(PL).get(ECharacters.ENABLED.ordinal()), whatNow);
        PlayerStorage ps = PlayerStorage.getStorage(PL);
        int lang = ps.getLanguage();
        if (whatNow == CHARENABLED)
        {
            switch (lang)
            {
                case ALocalisation.CN -> PL.sendMessage(ChatColor.GREEN + "你的性格已启用");
                case ALocalisation.EN -> PL.sendMessage(ChatColor.GREEN + "Your characters have been enabled");
            }
        }
        else if(whatNow == CHARDISALED)
        {
            removeFromStorageMap(PL);
            switch (lang)
            {
                case ALocalisation.CN -> PL.sendMessage(ChatColor.RED + "你的性格已经删除");
                case ALocalisation.EN -> PL.sendMessage(ChatColor.RED + "Your characters has been deleted");
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
        CFG.set(APCFactory.getPathList(PL).get(ECharacters.CHANGE.ordinal()), value);
    }
    public void refresh()
    {
        charList = new ArrayList<>(Collections.emptyList());
        for(int i = 0; i < 10; i++)
        {
            charList.set(i, (Integer) CFG.get(APCFactory.getPathList(PL).get(i)));
            i++;
        }
        lang = CFG.getInt(APCFactory.getPathList(PL).get(ECharacters.LANGUAGE.ordinal()));
        enable = CFG.getInt(APCFactory.getPathList(PL).get(ECharacters.ENABLED.ordinal()));
        changed = CFG.getInt(APCFactory.getPathList(PL).get(ECharacters.CHANGE.ordinal()));
    }
    private PlayerStorage(Player pl)
    {
        PL = pl;
        refresh();
    }
}
