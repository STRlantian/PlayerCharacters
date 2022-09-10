package override.studio.strlantian.playercharacters;

import org.bukkit.entity.Player;
import override.studio.strlantian.PlayerCharacters;
import override.studio.strlantian.playercharacters.enums.Characters;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlayerStorager
{
    private static final Map<Player, PlayerStorager> STORAGERMAP = new HashMap<>(Collections.emptyMap());
    private final Player PL;
    private List<Integer> charList;
    private int lang;
    private int enable;
    private int changed;
    public List<Integer> getCharacterList()
    {
        return charList;
    }

    public static PlayerStorager getStorager(Player pl)
    {
        return STORAGERMAP.get(pl);
    }
    public static void addToStorageMap(Player pl)
    {
        STORAGERMAP.put(pl, new PlayerStorager(pl));
    }
    public static void removeFromStorageMap(Player pl)
    {
        STORAGERMAP.remove(pl);
    }
    public static void clearStorager()
    {
        STORAGERMAP.clear();
    }
    public void setCharacterList(Characters which, int value)
    {
        charList.set(which.ordinal(), value);
        PCFactory.setCharacter(PL, which, value);
    }
    public void setCharacterList(List<Integer> list)
    {
        charList = list;
        PCFactory.setCharacter(PL, list);
    }
    public int getLanguage()
    {
        return lang;
    }
    public void setLanguage(int value)
    {
        lang = value;
        PCFactory.getCharacterList(PL).set(Characters.LANGUAGE.ordinal(), value);
    }
    public int getEnable()
    {
        return enable;
    }
    public void setEnable(int value)
    {
        enable = value;
        PCFactory.setEnable(PL, value);
    }
    public int getChanged()
    {
        return changed;
    }
    public void setChanged(int value)
    {
        changed = value;
        PCFactory.setChanged(PL, value);
    }
    public void refresh()
    {
        charList = PCFactory.getCharacterList(PL);
        lang = PlayerCharacters.inst.getConfig().getInt(PCFactory.getPathList(PL).get(Characters.LANGUAGE.ordinal()));
        enable = PCFactory.getEnable(PL);
        changed = PCFactory.getChanged(PL);
    }
    public PlayerStorager(Player pl)
    {
        charList = PCFactory.getCharacterList(pl);
        lang = PlayerCharacters.inst.getConfig().getInt(PCFactory.getPathList(pl).get(Characters.LANGUAGE.ordinal()));
        enable = PCFactory.getEnable(pl);
        changed = PCFactory.getChanged(pl);
        PL = pl;
    }
}
