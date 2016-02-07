package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;

public class TileMirrorBase extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        return null;
    }

    @Override
    public boolean canBeInNetworkWith(TileLightComponent component){
        return this.getTier() == component.getTier();
    }

    public String toString(){
        return this.getPos().toString();
    }
}
