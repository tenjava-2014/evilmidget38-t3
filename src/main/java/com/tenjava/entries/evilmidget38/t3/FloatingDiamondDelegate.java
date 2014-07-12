package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.Material;

public class FloatingDiamondDelegate implements GenerationDelegate {
    @Override
    public void generate(ChunkBuilder builder, int x, int z) {
        builder.set(0,0,0,16,1,16, (byte) Material.BEDROCK.getId());
        builder.set(0,1,0,16,17,16, (byte) Material.STATIONARY_LAVA.getId());

    }
}
