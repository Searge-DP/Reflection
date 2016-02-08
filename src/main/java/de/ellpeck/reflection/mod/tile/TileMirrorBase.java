package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.api.light.TileLightComponent;
import de.ellpeck.reflection.api.light.LightNetworkTier;

public class TileMirrorBase extends TileLightComponent{

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
        return 10;
    }

    public String toString(){
        return this.getPos().toString();
    }
}
