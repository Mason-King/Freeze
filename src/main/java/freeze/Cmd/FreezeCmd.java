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
                    for(Player pl : Bukkit.getOnlinePlayers()) {
                        utils.freeze(pl);
                        pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                    return false;
                } else if(args[0].equalsIgnoreCase("PLAYER")) {
                    if(args.length < 2) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    }
                    Player toFreeze = Bukkit.getPlayer(args[1]);
                    if(toFreeze == null) {
                        p.sendMessage(utils.chat(main.getConfig().getString("invalidPlayer")));
                        return false;
                    } else {
                        utils.freeze(toFreeze);
                        toFreeze.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                } else if(args[0].equalsIgnoreCase("PERMISSION")) {
                    if(args.length < 3) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    } else {
                        for(Player pl : utils.getPlayer(args[1])) {
                            utils.freeze(pl);
                            p.playSound(p.getLocation(),  Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                            if(main.getConfig().get(args[1]) == null) {
                                p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitle")), 1, 20, 1);
                            } else {
                                p.sendTitle(utils.chat(main.getConfig().getString(args[0] + ".title")), utils.chat(main.getConfig().getString(args[0] + ".subtitle")), 1, 20, 1);
                            }
                            pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        }
                    }
                }
            } else {
                //is time is specified
                Long time = Long.valueOf(args[2]);
                if(args[0].equalsIgnoreCase("All")) {
                    for(Player pl : Bukkit.getOnlinePlayers()) {
                        utils.freeze(pl, time);
                        pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                    return false;
                } else if(args[0].equalsIgnoreCase("PLAYER")) {
                    Player toFreeze = Bukkit.getPlayer(args[1]);
                    if(toFreeze == null) {
                        p.sendMessage(utils.chat(main.getConfig().getString("invalidPlayer")));
                        return false;
                    } else {
                        utils.freeze(toFreeze, time);
                        toFreeze.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        p.playSound(p.getLocation(), Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                        p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitleSubText")), 1, 20,1);
                    }
                } else if(args[0].equalsIgnoreCase("PERMISSION")) {
                    if(args.length < 3) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    } else {
                        for(Player pl : utils.getPlayer(args[2])) {
                            utils.freeze(pl, time);
                            pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                            p.playSound(p.getLocation(),  Sound.valueOf(main.getConfig().getString("freezeSound")), 10, 10);
                            if(main.getConfig().get(args[1]) == null) {
                                p.sendTitle(utils.chat(main.getConfig().getString("freezeTitle")), utils.chat(main.getConfig().getString("freezeTitle")), 1, 20, 1);
                            } else {
                                p.sendTitle(utils.chat(main.getConfig().getString(args[0] + ".title")), utils.chat(main.getConfig().getString(args[0] + ".subtitle")), 1, 20, 1);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
