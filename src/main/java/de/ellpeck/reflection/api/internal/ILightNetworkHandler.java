package de.ellpeck.reflection.api.internal;

import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.api.light.TileLightComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

/**
 * An internal class for a LightNetworkHandler.
 * Do not implement this directly.
 */
public interface ILightNetworkHandler{

    /**
     * Writes info about a connection to NBT.
     * Used by TileLightComponent.
     */
    void writeConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound);

    /**
     * Reads info about a connection from NBT.
     * Used by TileLightComponent.
     */
    void readConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound);

    /**
     * Removes every connection in a network for a component
     */
    void removeConnections(BlockPos component, World world);

    /**
     * Gets the network a component is in
     */
    ILightNetwork getNetworkForComponent(BlockPos component, int dimension);

    /**
     * Gets all of the connections a component is directly involved in.
     *
     * This is different from getNetworkForComponent() as it only returns the
     * connections the component is directly part of.
     * This means that in a network with connections like this: component1-component2-component3,
     * when calling this method from component1, will only return component1-component2.
     */
    Set<IConnectionPair> getConnectionsForComponent(BlockPos component, int dimension);

    /**
     * Adds a connection between two components
     *
     * Will add them to a new network if they're not in one,
     * merge the networks if they are in separate ones etc.
     *
     * @return If connecting the two components worked
     */
    boolean addConnection(BlockPos first, BlockPos second, World world, boolean validate);

    /**
     * Gets every network the current world (or the last world that was opened) has
     */
    Map<Integer, Set<ILightNetwork>> getAllNetworks();
}
