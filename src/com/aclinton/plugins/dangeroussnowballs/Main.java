package com.aclinton.plugins.dangeroussnowballs;

import com.aclinton.plugins.dangeroussnowballs.events.SnowballDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SnowballDamageEvent(), this);
    }
}
