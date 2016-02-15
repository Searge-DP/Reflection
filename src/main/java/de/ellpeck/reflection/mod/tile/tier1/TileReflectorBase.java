/*
 * This file ("TileReflectorBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier1;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.api.light.TileLightComponent;

public class TileReflectorBase extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        return ReflectionAPI.TIER_1;
    }

    @Override
    public int getMaxConnections(){
        return 2;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 5;
    }

    public String toString(){
        return this.getPos().toString();
    }
}
