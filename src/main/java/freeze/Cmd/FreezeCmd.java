package freeze.Cmd;

import freeze.Main;
import freeze.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCmd implements CommandExecutor {

    Main main = Main.getInstance();
    Utils utils = new Utils(main);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length < 1) {
            p.sendMessage(utils.chat(main.getConfig().getString("usage")));
            return false;
        } else {
            if(args.length < 3) {
                //if no time is specified
                if(args[0].equalsIgnoreCase("All")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    for(Player pl : Bukkit.getOnlinePlayers()) {
                        for(String s : main.getConfig().getStringList("exempt")) {
                            if(pl.hasPermission(s)) {
                                return false;
                            }
                        }
                        utils.freeze(pl);
                        pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        pl.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        pl.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                    return false;
                } else if(args[0].equalsIgnoreCase("PLAYER")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    if(args.length < 2) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    }
                    Player toFreeze = Bukkit.getPlayer(args[1]);
                    if(toFreeze == null) {
                        p.sendMessage(utils.chat(main.getConfig().getString("invalidPlayer")));
                        return false;
                    } else {
                        for(String s : main.getConfig().getStringList("exempt")) {
                            if(toFreeze.hasPermission(s)) {
                                return false;
                            }
                        }
                        utils.freeze(toFreeze);
                        toFreeze.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        toFreeze.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        toFreeze.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                } else if(args[0].equalsIgnoreCase("PERMISSION")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    if(args.length < 3) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    } else {
                        for(Player pl : utils.getPlayer(args[1])) {
                            for(String s : main.getConfig().getStringList("exempt")) {
                                if(pl.hasPermission(s)) {
                                    return false;
                                }
                            }
                            utils.freeze(pl);
                            pl.playSound(p.getLocation(),  Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                            if(main.getConfig().get(args[1]) == null) {
                                pl.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitle")), 1, 20, 1);
                            } else {
                                pl.sendTitle(utils.chat(main.getConfig().getString(args[0] + ".title")), utils.chat(main.getConfig().getString(args[0] + ".subtitle")), 1, 20, 1);
                            }
                            pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        }
                    }
                } else if(args[0].equalsIgnoreCase("RELOAD")) {
                    if(p.hasPermission("freeze.reload")) {
                        main.reloadConfig();
                        p.sendMessage(utils.chat(main.getConfig().getString("reload")));
                    }
                } else if(args[0].equalsIgnoreCase("LOCKDOWN")) {
                    if(p.hasPermission("freeze.lockdown")) {
                        if(utils.getLockdown()) {
                            utils.setLockdown(false);
                            utils.broadcast(utils.chat(main.getConfig().getString("lockdownOff")));
                        } else {
                            utils.setLockdown(true);
                            utils.broadcast(utils.chat(main.getConfig().getString("lockdownOn")));
                        }
                    }
                }
            } else {
                //is time is specified
                Long time = Long.valueOf(args[2]);
                if(args[0].equalsIgnoreCase("All")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    for(Player pl : Bukkit.getOnlinePlayers()) {
                        utils.freeze(pl, time);
                        pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        pl.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        pl.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                    return false;
                } else if(args[0].equalsIgnoreCase("PLAYER")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    Player toFreeze = Bukkit.getPlayer(args[1]);
                    if(toFreeze == null) {
                        p.sendMessage(utils.chat(main.getConfig().getString("invalidPlayer")));
                        return false;
                    } else {
                        utils.freeze(toFreeze, time);
                        toFreeze.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        toFreeze.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        toFreeze.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                } else if(args[0].equalsIgnoreCase("PERMISSION")) {
                    if(!p.hasPermission("freeze.admin")) return false;
                    if(args.length < 3) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    } else {
                        for(Player pl : utils.getPlayer(args[2])) {
                            utils.freeze(pl, time);
                            if(main.getConfig().get(args[1]) == null) {
                                pl.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitle")), 1, 20, 1);
                                pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                                pl.playSound(p.getLocation(),  Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                            } else {
                                pl.sendTitle(utils.chat(main.getConfig().getString(args[0] + ".title")), utils.chat(main.getConfig().getString(args[0] + ".subtitle")), 1, 20, 1);
                                pl.sendMessage(utils.chat(main.getConfig().getString(args[0] + ".message")));
                                pl.playSound(p.getLocation(),  Sound.valueOf(main.getConfig().getString(args[0] + ".sound")), 10, 10);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
