package de.ellpeck.reflection.mod.world;

import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEvents{

    @SubscribeEvent
    public void onUnload(WorldEvent.Unload event){
        WorldData.makeDirty();
    }

    @SubscribeEvent
    public void onSave(WorldEvent.Save event){
        WorldData.makeDirty();
    }

}
