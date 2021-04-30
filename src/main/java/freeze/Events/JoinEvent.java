package freeze.Events;

import freeze.Main;
import freeze.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    Main main = Main.getInstance();
    Utils utils = new Utils(main);

    @EventHandler
    public void onJOin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        if(utils.getLockdown()) {
            utils.freeze(p);
            p.sendMessage(utils.chat(main.getConfig().getString("frozen")));
            p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
            p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
        }
    }

}
