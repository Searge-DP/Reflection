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
import de.ellpeck.reflection.api.light.LightNetworkTier;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class ReflectionAPI{

    /**
     * The tier for every light component which isn't part of the tier system itself (like converters)
     */
    public static final LightNetworkTier TIER_SPECIAL = new LightNetworkTier(0, EntitySheep.func_175513_a(EnumDyeColor.ORANGE));

    //All of the "normal" light network tiers
    public static final LightNetworkTier TIER_1 = new LightNetworkTier(1, EntitySheep.func_175513_a(EnumDyeColor.LIGHT_BLUE));
    public static final LightNetworkTier TIER_2 = new LightNetworkTier(2, EntitySheep.func_175513_a(EnumDyeColor.GREEN));

    /**
     * The method handler
     * <p>
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
