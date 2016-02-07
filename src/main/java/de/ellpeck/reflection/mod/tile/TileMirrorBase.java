package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import de.ellpeck.reflection.mod.network.LightNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ITickable;

public class TileMirrorBase extends TileLightComponent implements ITickable{

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

    public String toString(){
        return this.getPos().toString();
    }

    @Override
    public void update(){
        if(!worldObj.isRemote && this.worldObj.getTotalWorldTime()%40 == 0){
            System.out.println(LightNetwork.instance.allNetworks);
            System.out.println();
        }
    }
}
