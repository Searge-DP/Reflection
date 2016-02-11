/*
 * This file ("ReflectionAPI.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api;

import de.ellpeck.reflection.api.internal.ILightNetworkHandler;
import de.ellpeck.reflection.api.internal.IMethodHandler;

public class ReflectionAPI{

    /**
     * The method handler
     *
     * Gets initialized during the FMLPreInitializationEvent in Reflection's CommonProxy
     */
    public static IMethodHandler theMethodHandler;

    /**
     * Gets the light network handler from the method handler
     * (Check there for more detailed information)
     */
    public static ILightNetworkHandler getLightNetworkHandler(){
        return theMethodHandler.getLightNetworkHandler();
    }
}
