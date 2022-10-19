package override.studio.strlantian.playercharacters;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerStatus
{
    private final Player PL;
    public static Map<Player, PlayerStatus> STATUSMAP = new HashMap<>(Collections.emptyMap());
    private boolean dark;
    public static PlayerStatus getStatus(Player pl)
    {
        return STATUSMAP.get(pl);
    }
    public void setIntoDark(boolean v)
    {
        dark = v;
    }
    public boolean isIntoDark()
    {
        return dark;
    }
    public PlayerStatus(Player pl)
    {
        this.PL = pl;
    }
}
