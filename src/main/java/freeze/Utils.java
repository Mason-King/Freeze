package freeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class Utils {

    private Main main;
    private static HashMap<Player, Long> frozen = new HashMap<>();
    private static boolean lockdown;

    public Utils(Main main) {
        this.main = main;
    }

    public void freeze(Player p) {
        frozen.put(p, null);
    }

    public void freeze(Player p, Long time) {
        long start = System.currentTimeMillis() + (time * 1000);
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

    public void setLockdown(Boolean b) {
        if(b == true) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(chat(main.getConfig().getString("lockdownOn")));
                p.sendTitle(chat(main.getConfig().getString("lockdownOnTitle")), chat(main.getConfig().getString("lockdownOnSubTitle")), 1, main.getConfig().getInt("lockdownOnTitleTime"), 1);
                p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("lockdownOnSound")), 10, 10);
            }
        } else {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(chat(main.getConfig().getString("lockdownOff")));
                p.sendTitle(chat(main.getConfig().getString("lockdownOffTitle")), chat(main.getConfig().getString("lockdownOffSubTitle")), 1, main.getConfig().getInt("lockdownOffTitleTime"), 1);
                p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("lockdownOffSound")), 10, 10);
            }
        }
        lockdown = b;
    }

    public Boolean getLockdown() {
        return lockdown;
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
