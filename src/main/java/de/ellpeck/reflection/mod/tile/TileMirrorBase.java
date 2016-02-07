package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileMirrorBase extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        return LightNetworkTier.BASE_TIER;
    }

    @Override
    public boolean canBeInNetworkWith(TileLightComponent component){
        return this.getTier() == component.getTier();
    }

    @Override
    public int getMaxConnections(){
        return 2;
    }

    @Override
    public int getLightUsage(){
        return 0;
    }

    @Override
    public int getLightGeneration(){
        return 0;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 10;
    }

    public String toString(){
        return this.getPos().toString();
    }
}
