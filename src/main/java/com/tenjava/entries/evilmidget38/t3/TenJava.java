package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    public void onEnable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new MySuperCoolChunkGenerator(worldName, id);
    }
}
