/*
 * This file ("TileConverter12.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.special;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.api.light.TileLightComponent;

public class TileConverter12 extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        return ReflectionAPI.TIER_SPECIAL;
    }

    @Override
    public boolean canBeInNetworkWith(ILightComponent component){
        return component.getTier() == ReflectionAPI.TIER_1 || component.getTier() == ReflectionAPI.TIER_2;
    }

    @Override
    public int getMaxConnections(){
        return 2;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 10;
    }
}
