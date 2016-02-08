package de.ellpeck.reflection.api.internal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public interface IConnectionPair{

    /**
     * Returns if this connection contains the given component
     */
    boolean contains(BlockPos component);

    /**
     * Gets the second component of the connection
     */
    BlockPos getFirst();

    /**
     * Gets the first component of the connection
     */
    BlockPos getSecond();

    /**
     * Writes this connection to NBT.
     * (For internal use only, you'll never have to call this.)
     */
    void writeToNBT(NBTTagCompound compound);
}
