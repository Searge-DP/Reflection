/*
 * This file ("LibMod.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.lib;

import de.ellpeck.reflection.api.ReflectionAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class LibMod{

    public static final String MOD_ID = "reflection";
    public static final String MOD_NAME = ReflectionAPI.MOD_NAME;

    public static final String VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    private static final String PROXY_BASE = "de.ellpeck.reflection.mod.proxy.";
    public static final String PROXY_CLIENT = PROXY_BASE+"ClientProxy";
    public static final String PROXY_SERVER = PROXY_BASE+"CommonProxy";

    public static final Random RANDOM = new Random();

}
