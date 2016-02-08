package de.ellpeck.reflection.api;

import de.ellpeck.reflection.api.internal.ILightNetworkHandler;

public class ReflectionAPI{

    public static final String VERSION = "1";

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
