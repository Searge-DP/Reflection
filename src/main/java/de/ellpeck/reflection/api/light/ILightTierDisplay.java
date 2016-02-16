/*
 * This file ("ILightTierDisplay.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

/**
 * Implement this on any Item or Block that should
 * display its tier in the tooltip
 */
public interface ILightTierDisplay{

    LightNetworkTier getTier();

}
