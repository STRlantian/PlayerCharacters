package override.studio.strlantian.playercharacters;

import org.bukkit.entity.Player;
import override.studio.strlantian.playercharacters.enums.Characters;

import java.util.List;

public final class PlayerStorager
{
    private final List<Integer> charList;
    private int lang;

    public List<Integer> getCharacterList()
    {
        return charList;
    }

    public void setCharacterList(Characters which, int value)
    {
        charList.set(which.ordinal(), value);
    }
    public int getLanguage()
    {
        return lang;
    }
    public void setLang()
    {

    }
    public PlayerStorager(Player pl)
    {
        charList = PCFactory.getCharacterList(pl);
        lang = Localisation.getLanguage(pl);
    }
}
