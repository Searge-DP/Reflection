/*
 * This file ("IConnectionPair.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.internal;

import net.minecraft.util.BlockPos;

/**
 * An internal class for a ConnectionPair.
 * Do not implement this directly.
 */
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

}
