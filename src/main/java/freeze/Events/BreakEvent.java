package freeze.Events;

import freeze.Main;
import freeze.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class BreakEvent implements Listener {

    Main main = Main.getInstance();
    Utils utils = new Utils(main);

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = (Player) e.getPlayer();
        if(utils.getFrozen().containsKey(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        if(utils.getFrozen().containsKey(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if(utils.getFrozen().containsKey(p)) {
            e.setCancelled(true);
        }
    }

}
