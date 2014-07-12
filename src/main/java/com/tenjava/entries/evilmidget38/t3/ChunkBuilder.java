package com.tenjava.entries.evilmidget38.t3;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

// Yes, I wrote this.
public class ChunkBuilder {
    private final World world;
    private final Random random;
    private final ChunkGenerator.BiomeGrid grid;
    private final byte[][] chunk;
    public ChunkBuilder(World world, Random random, ChunkGenerator.BiomeGrid grid) {
        this.world = world;
        this.random = random;
        this.grid = grid;
        this.chunk = new byte[world.getMaxHeight() / 16][];
    }

    // This is a slightly modified version of the method found in the javadocs for ChunkGenerator.
    // jkcclemens said I could have it.
    public void setBlock(int x, int y, int z, byte blkid) {
        if (chunk[y >> 4] == null) {
            chunk[y >> 4] = new byte[4096];
        }
        chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }

    // This is a slightly modified version of the method found in the javadocs for ChunkGenerator.
    // jkcclemens said I could have it.
    public byte getBlock(int x, int y, int z) {
        if (chunk[y >> 4] == null) {
            return (byte)0;
        }
        return chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
    }

    public void set(int x1, int y1, int z1, int x2, int y2, int z2, byte blkid) {
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                for (int z = z1; z < z2; z++) {
                    setBlock(x, y, z, blkid);
                }
            }
        }
    }

    public byte[][] build() {
        return chunk;
    }
}
