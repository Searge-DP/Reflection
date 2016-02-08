package de.ellpeck.reflection.api.internal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.Map;
import java.util.Set;

public interface ILightNetwork{

    void addLightUser(TileEntity tile, int amount);

    void addLightGen(TileEntity tile, int amount);

    void removeLightUser(TileEntity tile);

    void removeLightGen(TileEntity tile);

    int getTotalLightUsed();

    int getTotalLightUsedExcluded(TileEntity tile);

    int getTotalLightGenerated();

    int getTotalLightGeneratedExcluded(TileEntity tile);

    int getTotalLight();

    int getTotalLightExcluded(TileEntity tile);

    Set<IConnectionPair> getConnections();

    Map<BlockPos, Integer> getLightGenAndUsageMap();

    void writeToNBT(NBTTagCompound compound);

}
