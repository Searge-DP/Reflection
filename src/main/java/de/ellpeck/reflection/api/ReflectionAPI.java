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

public class ReflectionAPI{

    public static final String VERSION = "2";

    public static final String MOD_NAME = "Reflection";
    public static final String API_NAME = MOD_NAME+"API";

    /**
     * The light network handler
     * (Connections get added when joining a world or while in a world,
     * old connections are cleared when loading/joining a different world)
     *
     * Gets initialized during the FMLPreInitializationEvent in Reflection's CommonProxy
     */
    public static ILightNetworkHandler theLightNetworkHandler;
}
