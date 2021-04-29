package freeze.Events;

import freeze.Main;
import freeze.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class MoveEvent implements Listener {

    Main main = Main.getInstance();
    Utils utils = new Utils(main);

    HashMap<Player, Long> frozen = utils.getFrozen();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = (Player)  event.getPlayer();
        if(!frozen.containsKey(p)) return;
        if(frozen.get(p) == null) {
            //if there is no time limit
            event.setCancelled(true);
            return;
        }
        if(System.currentTimeMillis() < frozen.get(p)) {
            //there is a time limit, and it is not passed yet
            event.setCancelled(true);//
            return;
        } else {
            //time limit has passed.
            frozen.remove(p);
            p.sendMessage(utils.chat(main.getConfig().getString("unFrozen")));
        }
    }

}
