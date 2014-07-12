package com.tenjava.entries.evilmidget38.t3;

import com.google.common.collect.ImmutableList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MySuperCoolChunkGenerator extends ChunkGenerator {
    private final Map<ChunkCoord, GenerationDelegate> structures = new HashMap<ChunkCoord, GenerationDelegate>();
    private final Biome[] BIOMES = Biome.values();
    private final String world;
    private final String id;
    private boolean firstchunk = true;
    private int spawnX;
    private int spawnZ;
    public MySuperCoolChunkGenerator(String world, String id) {
        this.world = world;
        this.id = id;
    }
    @SuppressWarnings("deprecation")
    @Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        if (firstchunk) {
            firstchunk = false;
            spawnX = x;
            spawnZ = z;
        }
        ChunkBuilder chunkBuilder = new ChunkBuilder(world, random, biomes);
        GenerationDelegate delegate = getDelegate(random);
        delegate.generate(chunkBuilder, x, z);
        return chunkBuilder.build();
    }

    public GenerationDelegate getDelegate(Random random) {
        if (random.nextInt(200) == 1) {
            return new FloatingDiamondDelegate();
        } else {
            Biome randomBiome = BIOMES[random.nextInt(BIOMES.length)];
            return new PyramidGenerationDelegate(randomBiome, getMaterial(randomBiome));
        }
    }
    public Material getMaterial(Biome biome) {
        switch (biome) {
            case HELL:
                return Material.NETHERRACK;
            case BEACH:
                return Material.SAND;
            case ICE_MOUNTAINS:
                return Material.SNOW_BLOCK;
            case DEEP_OCEAN:
                return Material.STATIONARY_WATER;
            default:
                return Material.GRASS;
        }
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
