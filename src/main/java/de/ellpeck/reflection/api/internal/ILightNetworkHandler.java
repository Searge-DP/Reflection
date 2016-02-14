/*
 * This file ("ILightNetworkHandler.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.internal;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

/**
 * An internal class for a LightNetworkHandler.
 * Use it from ReflectionAPI.
 * <p>
 * Do not implement this directly.
 */
public interface ILightNetworkHandler{

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
     * <p>
     * This is different from getNetworkForComponent() as it only returns the
     * connections the component is directly part of.
     * This means that in a network with connections like this: component1-component2-component3,
     * when calling this method from component1, will only return component1-component2.
     */
    Set<IConnectionPair> getConnectionsForComponent(BlockPos component, int dimension);

    /**
     * Adds a connection between two components
     * <p>
     * Will add them to a new network if they're not in one,
     * merge the networks if they are in separate ones etc.
     *
     * @return An unlocalized text that points out what exactly went wrong. Null if the connection worked.
     */
    String addConnection(BlockPos first, BlockPos second, World world, boolean validate);

    /**
     * Gets every network the current world (or the last world that was opened) has
     */
    Map<Integer, Set<ILightNetwork>> getAllNetworks();
}
