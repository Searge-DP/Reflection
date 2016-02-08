package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import de.ellpeck.reflection.mod.world.WorldEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        ReflectionAPI.theLightNetworkHandler = new LightNetworkHandler();

        InitBlocks.preInit();
        InitItems.preInit();
    }

    public void init(FMLInitializationEvent event){

        MinecraftForge.EVENT_BUS.register(new WorldEvents());
    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
