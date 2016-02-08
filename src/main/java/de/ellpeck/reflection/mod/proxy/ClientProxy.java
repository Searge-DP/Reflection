package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.api.light.TileLightComponent;
import de.ellpeck.reflection.api.light.render.LightComponentSpecialRenderer;
import de.ellpeck.reflection.mod.tile.TileMirrorBase;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

        ClientRegistry.bindTileEntitySpecialRenderer(TileMirrorBase.class, new LightComponentSpecialRenderer());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }
}
