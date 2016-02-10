/*
 * This file ("IMethodHandler.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.internal;

import de.ellpeck.reflection.api.light.ILightComponent;
import net.minecraft.nbt.NBTTagCompound;

/**
 * This is the method handler for accessing methods from the mod
 * from within the API.
 * Use it from ReflectionAPI.
 * <p>
 * Do not implement this directly.
 */
public interface IMethodHandler{

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

}
