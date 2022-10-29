package override.studio.strlantian.listeners.characters;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import override.studio.strlantian.playercharacters.APCFactory;
import override.studio.strlantian.playercharacters.PlayerStatus;
import override.studio.strlantian.playercharacters.PlayerStorage;

import java.util.Objects;

import static override.studio.strlantian.PlayerCharacters.inst;
import static override.studio.strlantian.playercharacters.ALocalisation.CN;
import static override.studio.strlantian.playercharacters.ALocalisation.EN;
import static override.studio.strlantian.playercharacters.ECharacters.DARKNESS;

public class DarknessListener implements Listener
{
    @EventHandler
    public void onDark(PlayerMoveEvent e)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Player pl = e.getPlayer();
                PlayerStorage ps = PlayerStorage.getStorage(pl);
                PlayerStatus pt = PlayerStatus.getStatus(pl);
                Location loc = Objects.requireNonNull(e.getTo());
                if(loc.getBlock().getLightFromBlocks() <= 4)
                {
                    if(ps.getEnable() == APCFactory.CHARENABLED)
                    {
                        int dark = ps.getCharacter(DARKNESS);
                        if(dark == APCFactory.DARKNESS_YES)
                        {
                            pt.setIntoDark(true);
                            switch(ps.getLanguage())
                            {
                                case CN -> pl.sendMessage(ChatColor.DARK_RED + "你开始有点怕黑...");
                                case EN -> pl.sendMessage(ChatColor.DARK_RED + "You started to feeling scared...");
                            }
                            while(pt.isIntoDark())
                            {
                                pl.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1, 1));
                                pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 1));
                            }
                        }
                    }
                }
                else
                {
                    pt.setIntoDark(false);
                }
            }
        }.runTaskAsynchronously(inst);
    }
}