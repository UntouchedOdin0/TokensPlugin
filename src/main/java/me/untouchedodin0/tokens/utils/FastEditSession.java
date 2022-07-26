package me.untouchedodin0.tokens.utils;

import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.IBlockData;
import net.minecraft.server.v1_13_R2.World;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftMagicNumbers;

import java.util.HashMap;

public class FastEditSession implements EditSession {

    private final org.bukkit.World bukkitWorld;
    private final World world;
    private final HashMap<BlockPosition, IBlockData> modified = new HashMap<>();

    public FastEditSession(org.bukkit.World bukkitWorld, World world) {
        this.bukkitWorld = bukkitWorld;
        this.world = world;
    }

    @Override
    public void setBlock(int x, int y, int z, Material material) {
        modified.put(new BlockPosition(x, y, z), CraftMagicNumbers.getBlock(material).getBlockData());
    }

    @Override
    public Material getBlock(int x, int y, int z) {

        return Material.AIR;
    }

    @Override
    public void update() {

    }
}
