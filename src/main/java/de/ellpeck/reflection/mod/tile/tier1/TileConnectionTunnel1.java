/*
 * This file ("TileConnectionTunnelBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier1;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;

public class TileConnectionTunnel1 extends TileLightComponentBase{

    @Override
    public int getMaxConnections(){
        return 5;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 8;
    }

    @Override
    public boolean canBeInNetworkWith(ILightComponent component){
        return component.getTier() == ReflectionAPI.TIER_1 && !(component instanceof TileConnectionTunnel1);
    }
}
