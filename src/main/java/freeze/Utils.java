package freeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Utils {

    private Main main;
    private static HashMap<Player, Long> frozen = new HashMap<>();

    public Utils(Main main) {
        this.main = main;
    }

    public void freeze(Player p) {
        frozen.put(p, null);
    }

    public void freeze(Player p, Long time) {
        long start = System.currentTimeMillis() * 1000 + time;
        frozen.put(p, start);
    }

    public List<Player> getPlayer(String permission) {
        List<Player> players = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission(permission)) {
                players.add(p);
            }
        }
        return players;
    }

    public HashMap<Player, Long> getFrozen() {
        return frozen;
    }

    public void unFreeze(Player p) {
        frozen.remove(p);
    }

    public String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void broadcast(String s) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(s);
        }
    }

}
