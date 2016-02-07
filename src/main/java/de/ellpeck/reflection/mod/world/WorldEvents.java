package de.ellpeck.reflection.mod.world;

import de.ellpeck.reflection.mod.network.LightNetwork;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEvents{

    @SubscribeEvent
    public void onLoad(WorldEvent.Load event){
        if(LightNetwork.instance == null){
            LightNetwork.instance = new LightNetwork();
        }
    }

    @SubscribeEvent
    public void onUnload(WorldEvent.Unload event){
        WorldData.makeDirty();
    }

    @SubscribeEvent
    public void onSave(WorldEvent.Save event){
        WorldData.makeDirty();
    }

}
