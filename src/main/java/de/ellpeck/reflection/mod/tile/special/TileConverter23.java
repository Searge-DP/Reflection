/*
 * This file ("TileConverter23.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;

public class TileConverter23 extends TileLightComponentBase{

    @Override
    public boolean canBeInNetworkWith(ILightComponent component){
        return component.getTier() == ReflectionAPI.TIER_2 || component.getTier() == ReflectionAPI.TIER_3;
    }

    @Override
    public int getMaxConnections(){
        return 2;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 12;
    }
}
