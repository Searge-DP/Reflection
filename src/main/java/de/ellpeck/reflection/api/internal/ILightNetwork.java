package de.ellpeck.reflection.api.internal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.Map;
import java.util.Set;

public interface ILightNetwork{

    /**
     * Adds a light user to the network.
     * Only call this when a tile changes from not using light to using light.
     */
    void addLightUser(TileEntity tile, int amount);

    /**
     * Adds a light generator to the network.
     * Only call this when a tile changes from not generating light to generating light.
     */
    void addLightGen(TileEntity tile, int amount);

    /**
     * Removes a light user from the network.
     * Only call this when a tile changes from using light to not using light.
     */
    void removeLightUser(TileEntity tile);

    /**
     * Removes a light generator from the network.
     * Only call this when a tile changes from generating light to not generating light.
     */
    void removeLightGen(TileEntity tile);

    /**
     * Gets the total amount of light used in the network.
     */
    int getTotalLightUsed();

    /**
     * Gets the total amount of light used in the network minus the given tile.
     */
    int getTotalLightUsedExcluded(TileEntity tile);

    /**
     * Gets the total amount of light generated in the network.
     */
    int getTotalLightGenerated();

    /**
     * Gets the total amount of light generated in the network minus the given tile.
     */
    int getTotalLightGeneratedExcluded(TileEntity tile);

    /**
     * Gets the total amount of light in the network.
     *
     * This returns the amount of light generated minus the amount of light used.
     */
    int getTotalLight();

    /**
     * Gets the total amount of light in the network minus the given tile.
     *
     * This returns the amount of light generated minus the given tile minus the amount of light used minus the given tile.
     */
    int getTotalLightExcluded(TileEntity tile);

    /**
     * Gets all of the connections in the network
     */
    Set<IConnectionPair> getConnections();

    /**
     * Gets a map of all light users and generators.
     *
     * Light users have a negative value, light generators have a positive value.
     */
    Map<BlockPos, Integer> getLightGenAndUsageMap();

    /**
     * Writes this network to NBT.
     * (For internal use only, you'll never have to call this.)
     */
    void writeToNBT(NBTTagCompound compound);

}
