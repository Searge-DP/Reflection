package de.ellpeck.reflection.mod.lib;

import de.ellpeck.reflection.api.ReflectionAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibMod{

    public static final String MOD_ID = "reflection";
    public static final String MOD_NAME = ReflectionAPI.MOD_NAME;

    public static final String VERSION = "@VERSION@";

    private static final String PROXY_BASE = "de.ellpeck.reflection.mod.proxy.";
    public static final String PROXY_CLIENT = PROXY_BASE+"ClientProxy";
    public static final String PROXY_SERVER = PROXY_BASE+"CommonProxy";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

}
