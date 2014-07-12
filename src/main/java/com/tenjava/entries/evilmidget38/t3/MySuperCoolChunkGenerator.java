package com.tenjava.entries.evilmidget38.t3;

import com.google.common.collect.ImmutableList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.List;
import java.util.Random;

public class MySuperCoolChunkGenerator extends ChunkGenerator {
    private final String world;
    private final String id;
    private boolean firstchunk = true;
    private int spawnX;
    private int spawnZ;
    public MySuperCoolChunkGenerator(String world, String id) {
        this.world = world;
        this.id = id;
    }
    @Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        if (firstchunk) {
            firstchunk = false;
            spawnX = x;
            spawnZ = z;
        }
        ChunkBuilder chunkBuilder = new ChunkBuilder(world, random, biomes);
        // 16x16x1 bedrock layer
        chunkBuilder.set(0, 0, 0, 16, 1, 16, (byte) Material.BEDROCK.getId());
        // 16x16x16 stone base.
        chunkBuilder.set(0,1,0,16,16,16, (byte) Material.STONE.getId());
        // stationary lava border, implemented as 4 rectangles.
        chunkBuilder.set(0,16, 0, 1, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        chunkBuilder.set(0,16, 0, 16, 17, 1, (byte) Material.STATIONARY_LAVA.getId());
        chunkBuilder.set(15, 16, 0, 16, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        chunkBuilder.set(0, 16, 15, 16, 17, 16, (byte) Material.STATIONARY_LAVA.getId());
        // Construct a pyramid composed of 7 layers
        for (int y = 16; y < 23; y++) {
            // half_of_chunk_width - (pyramid_max_height - y)
            int xAndZ = 8 - (23 - y);
            chunkBuilder.set(xAndZ, y, xAndZ, 16 - xAndZ, y + 1, 16 - xAndZ, (byte) Material.GRASS.getId());
        }
        return chunkBuilder.build();
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return ImmutableList.<BlockPopulator>of(new MySuperCoolBlockPopulatorThatDoesThings());
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, spawnX * 16 + 8, 18, spawnZ * 16 + 8);
    }
}