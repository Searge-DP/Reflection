/*
 * This file ("ILightComponent.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * A light component
 * Implement this on a TileEntity that that generates or uses light and/or can be connected to a light network
 *
 * For a pre-made implementation, see TileLightComponent.
 *
 * IT IS VERY IMPORTANT THAT YOU REMOVE THE CONNECTIONS OF THIS COMPONENT IN THE TILE's invalidate() METHOD!!!
 * (Also see TileLightComponent for more precise information)
 */
public interface ILightComponent{

    /**
     * The tier of the component
     */
    LightNetworkTier getTier();

    /**
     * Checks if this component can be in a network with another component.
     * Note that this is called for both components and if at least one of them
     * returns true, the connection will be made.
     *
     * So it isn't certain that, if you return true here, the connection won't happen.
     *
     * @param component The component to connect to
     */
    boolean canBeInNetworkWith(ILightComponent component);

    /**
     * @return The maximum amount of connections this component can have in a network
     */
    int getMaxConnections();

    /**
     * Returns the maximum distance between this component and another one
     * Note that the smaller of the max distances of two components will always be picked
     */
    int getMaxDistanceFromComponent();

    /**
     * Returns the position of this component.
     * When implementing, just return the tile's getPos().
     */
    BlockPos getPosition();

    /**
     * Returns the world of this component
     * When implementing, just return the tile's getWorld().
     */
    World getTheWorld();

}
