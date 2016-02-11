/*
 * This file ("LightNetworkTier.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A light network tier
 * <p>
 * New tiers can be added and don't have to be registered
 */
public class LightNetworkTier{

    private float[] colors;
    private int tierInt;

    public LightNetworkTier(int tierInt, float[] colors){
        this.colors = colors;
        this.tierInt = tierInt;
    }

    /**
     * Gets the tier as an integer value
     * The higher the value, the more advanced the tier
     * This is used by some components to see if they can connect
     */
    public int getTierInt(){
        return this.tierInt;
    }

    /**
     * The colors of the tier's light beams
     * the array should be filled with 3 floats: red, green, blue
     */
    @SideOnly(Side.CLIENT)
    public float[] getColors(){
        return this.colors;
    }
}
