package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.Material;
import org.bukkit.block.Biome;

public class PyramidGenerationDelegate implements GenerationDelegate {
    private final Biome biome;
    private final Material material;
    public PyramidGenerationDelegate(Biome biome, Material material) {
        this.biome = biome;
        this.material = material;
    }
    @Override
    public void generate(ChunkBuilder builder, int x, int z) {
        builder.setBiome(biome);
        // 16x16x1 bedrock layer
        builder.set(0, 0, 0, 16, 1, 16, (byte) Material.BEDROCK.getId());
        // 16x16x16 stone base.
        builder.set(0,1,0,16,16,16, (byte) Material.STONE.getId());
        // stationary lava border, implemented as 4 rectangles.
        builder.set(0,16, 0, 1, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        builder.set(0,16, 0, 16, 17, 1, (byte) Material.STATIONARY_LAVA.getId());
        builder.set(15, 16, 0, 16, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        builder.set(0, 16, 15, 16, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        // Construct a pyramid composed of 7 layers
        for (int y = 16; y < 23; y++) {
            // half_of_chunk_width - (pyramid_max_height - y)
            int xAndZ = 8 - (23 - y);
            builder.set(xAndZ, y, xAndZ, 16 - xAndZ, y + 1, 16 - xAndZ, (byte) material.getId());
        }
    }
}
