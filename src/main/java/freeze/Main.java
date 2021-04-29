package freeze;

import freeze.Cmd.FreezeCmd;
import freeze.Events.MoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        this.getCommand("Freeze").setExecutor(new FreezeCmd());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }
}
