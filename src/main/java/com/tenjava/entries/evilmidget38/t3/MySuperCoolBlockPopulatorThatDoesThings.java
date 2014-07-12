package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;
import java.util.TreeMap;

public class MySuperCoolBlockPopulatorThatDoesThings extends BlockPopulator {
    private static final TreeMap<Integer, Material> WEIGHTED_ORES;
    private static int MAX_ORE_KEY;
    static {
        WEIGHTED_ORES = new TreeMap<Integer, Material>();
        MAX_ORE_KEY = 0;
        MAX_ORE_KEY += 15;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.COAL_ORE);
        MAX_ORE_KEY += 10;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.IRON_ORE);
        MAX_ORE_KEY += 10;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.LAPIS_ORE);
        MAX_ORE_KEY += 10;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.REDSTONE_ORE);
        MAX_ORE_KEY += 5;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.GOLD_ORE);
        MAX_ORE_KEY += 5;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.EMERALD_ORE);
        MAX_ORE_KEY += 2;
        WEIGHTED_ORES.put(MAX_ORE_KEY, Material.DIAMOND_ORE);

    }
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        Biome biome = chunk.getBlock(0,0,0).getBiome();
        switch (biome) {
            case HELL:
                // Start some fires
                for (int x = 1; x < 15; x++) {
                    for (int z = 1; z < 15; z++) {
                        if (random.nextBoolean()) {
                            world.getHighestBlockAt(chunk.getX() * 16 + x, chunk.getZ() * 16 + z).setType(Material.FIRE);
                        }
                    }
                }
                // Add some ore
                if (random.nextBoolean()) {
                    generateOre(chunk, Material.QUARTZ_ORE);
                } else {
                    generateOre(chunk, Material.GLOWSTONE);
                }
                break;
            case BEACH:
                // Fill the center with water.
                for (int y = 17; y < 22; y++) {
                    for (int x = 7; x < 9; x++) {
                        for (int z = 7; z < 9; z++) {
                            world.getBlockAt(chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z).setType(Material.STATIONARY_WATER);
                        }
                    }
                }
                // Remove the top so that the water is visible.
                world.getBlockAt(chunk.getX() * 16 + 7, 22, chunk.getZ() * 16 + 7).setType(Material.AIR);
                world.getBlockAt(chunk.getX() * 16 + 8, 22, chunk.getZ() * 16 + 7).setType(Material.AIR);
                world.getBlockAt(chunk.getX() * 16 + 8, 22, chunk.getZ() * 16 + 8).setType(Material.AIR);
                world.getBlockAt(chunk.getX() * 16 + 7, 22, chunk.getZ() * 16 + 8).setType(Material.AIR);
                break;
            case ICE_PLAINS:
                // Add some snow
                for (int x = 1; x < 15; x++) {
                    for (int z = 1; z < 15; z++) {
                        world.getHighestBlockAt(chunk.getX() * 16 + x, chunk.getZ() * 16 + z).setType(Material.SNOW);
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
                if (random.nextInt(10) == 1) {
                    generateChest(chunk, random);
                } else {
                    generateRandomOre(chunk, random);
                }
        }
    }


    public void generateRandomOre(Chunk chunk, Random random) {
        Material material = WEIGHTED_ORES.ceilingEntry(random.nextInt(MAX_ORE_KEY)).getValue();
        generateOre(chunk, material);
    }

    public void generateOre(Chunk chunk, Material ore) {
        int y = 17;
        for (int x = 7; x < 9; x++) {
            for (int z = 7; z < 9; z++) {
                chunk.getBlock(x, y, z).setType(ore);
            }
        }
    }

    public void generateChest(Chunk chunk, Random random) {

    }

    public static void generateTree(Chunk chunk, TreeType type) {
        chunk.getWorld().generateTree(new Location(chunk.getWorld(), chunk.getX() * 16 + 7, 23, chunk.getZ() * 16 + 7), type);
    }
}
