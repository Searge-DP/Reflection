package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("unused")
public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        InitBlocks.preInit();
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
