/*
 * This file ("TileMirrorBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.api.light.TileLightComponent;

public class TileReflector extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        return LightNetworkTier.BASE_TIER;
    }

    @Override
    public boolean canBeInNetworkWith(ILightComponent component){
        return this.getTier() == component.getTier();
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
