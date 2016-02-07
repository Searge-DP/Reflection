package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.mod.gui.GameOverlayEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);

        MinecraftForge.EVENT_BUS.register(new GameOverlayEvents());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }
}
