package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class MySuperCoolBlockPopulatorThatDoesThings extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        Biome biome = chunk.getBlock(0,0,0).getBiome();
        switch (biome) {
            case HELL:
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        if (random.nextBoolean()) {
                            world.getHighestBlockAt(chunk.getX() * 16 + x, chunk.getZ() * 16 + z).getRelative(BlockFace.UP).setType(Material.FIRE);
                        }
                    }
                }
                break;
            case JUNGLE:
            case JUNGLE_EDGE:
            case JUNGLE_EDGE_MOUNTAINS:
            case JUNGLE_HILLS:
            case JUNGLE_MOUNTAINS:
                generateTree(chunk, TreeType.JUNGLE);
                break;
            case FOREST:
            case FOREST_HILLS:
                generateTree(chunk, TreeType.BIG_TREE);
                break;
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                generateTree(chunk, TreeType.BIRCH);
                break;
            default:

        }
    }

    public static void generateTree(Chunk chunk, TreeType type) {
        chunk.getWorld().generateTree(new Location(chunk.getWorld(), chunk.getX() * 16 + 8, 23, chunk.getZ() * 16 + 8), type);
    }
}
