package freeze.Cmd;

import freeze.Main;
import freeze.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezeCmd implements CommandExecutor {

    Main main = Main.getInstance();
    Utils utils = new Utils(main);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length < 1) {
            p.sendMessage(utils.chat(main.getConfig().getString("unFreezeUsage")));
            return false;
        } else {
                //if no time is specified
                if(args[0].equalsIgnoreCase("All")) {
                    for(Player pl : Bukkit.getOnlinePlayers()) {
                        utils.freeze(pl);
                        pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                    }
                    return false;
                } else if(args[0].equalsIgnoreCase("PLAYER")) {
                    if(args.length < 2) {
                        p.sendMessage(utils.chat(main.getConfig().getString("unFreezeUsage")));
                        return false;
                    }
                    Player toFreeze = Bukkit.getPlayer(args[1]);
                    if(toFreeze == null) {
                        p.sendMessage(utils.chat(main.getConfig().getString("invalidPlayer")));
                        return false;
                    } else {
                        utils.unFreeze(toFreeze);
                        toFreeze.sendMessage(utils.chat(main.getConfig().getString("unFrozen")));
                    }
                } else if(args[0].equalsIgnoreCase("PERMISSION")) {
                    if(args.length < 3) {
                        p.sendMessage(utils.chat(main.getConfig().getString("usage")));
                        return false;
                    } else {
                        for(Player pl : utils.getPlayer(args[1])) {
                            utils.freeze(pl);
                            pl.sendMessage(utils.chat(main.getConfig().getString("frozen")));
                        }
                    }
                }
        }
        return false;
    }
}
