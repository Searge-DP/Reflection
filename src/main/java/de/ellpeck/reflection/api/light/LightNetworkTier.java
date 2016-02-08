package de.ellpeck.reflection.api.light;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class LightNetworkTier{

    public static final LightNetworkTier BASE_TIER = new LightNetworkTier(EntitySheep.func_175513_a(EnumDyeColor.GREEN));

    private float[] colors;

    public LightNetworkTier(float[] colors){
        this.colors = colors;
    }

    public float[] getColors(){
        return this.colors;
    }
}
