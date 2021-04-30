package freeze;

import freeze.Cmd.FreezeCmd;
import freeze.Cmd.UnfreezeCmd;
import freeze.Events.BreakEvent;
import freeze.Events.JoinEvent;
import freeze.Events.MoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BreakEvent(), this);
        this.getCommand("Freeze").setExecutor(new FreezeCmd());
        this.getCommand("unfreeze").setExecutor(new UnfreezeCmd());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }
}
