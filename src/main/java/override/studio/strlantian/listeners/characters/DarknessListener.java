package override.studio.strlantian.listeners.characters;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import override.studio.strlantian.playercharacters.PlayerStorage;

import static override.studio.strlantian.PlayerCharacters.inst;
import static override.studio.strlantian.playercharacters.enums.Characters.DARKNESS;

public class DarknessListener implements Listener
{
    @EventHandler
    public void onIntoDark(PlayerMoveEvent e)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Location loc = e.getTo();
                Player pl = e.getPlayer();
                PlayerStorage ps = PlayerStorage.getStorage(pl);
                int dark = ps.getCharacter(DARKNESS);

            }
        }.runTaskAsynchronously(inst);
    }
}
