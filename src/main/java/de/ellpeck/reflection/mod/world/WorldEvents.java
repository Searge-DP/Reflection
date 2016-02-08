/*
 * This file ("WorldEvents.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.world;

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
