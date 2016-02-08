package de.ellpeck.reflection.api.internal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public interface IConnectionPair{

    boolean contains(BlockPos component);

    BlockPos getFirst();

    BlockPos getSecond();

    void writeToNBT(NBTTagCompound compound);
}
