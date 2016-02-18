/*
 * This file ("ClientProxy.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.mod.gui.OverlayEvents;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import de.ellpeck.reflection.mod.tile.render.TESRLightComponentBase;
import de.ellpeck.reflection.mod.tile.special.TileConverter12;
import de.ellpeck.reflection.mod.tile.tier1.TileCoallector;
import de.ellpeck.reflection.mod.tile.tier1.TileConnectionTunnelBase;
import de.ellpeck.reflection.mod.tile.tier1.TileReflectorBase;
import de.ellpeck.reflection.mod.tile.tier2.TileCharger;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy{

    private static final String INVENTORY = "inventory";

    private static Map<ItemStack, ResourceLocation> renderRegistry = new HashMap<ItemStack, ResourceLocation>();

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);

        for(Map.Entry<ItemStack, ResourceLocation> entry : renderRegistry.entrySet()){
            ModelLoader.setCustomModelResourceLocation(entry.getKey().getItem(), entry.getKey().getItemDamage(), new ModelResourceLocation(entry.getValue(), INVENTORY));
        }
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);

        MinecraftForge.EVENT_BUS.register(new OverlayEvents());

        this.registerBeamRenderer(TileConverter12.class);
        this.registerBeamRenderer(TileCoallector.class);
        this.registerBeamRenderer(TileReflectorBase.class);
        this.registerBeamRenderer(TileCharger.class);
        this.registerBeamRenderer(TileConnectionTunnelBase.class);
    }

    private void registerBeamRenderer(Class<? extends TileLightComponentBase> tile){
        ClientRegistry.bindTileEntitySpecialRenderer(tile, new TESRLightComponentBase());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public void addToRenderRegistry(ItemStack stack, ResourceLocation location){
        super.addToRenderRegistry(stack, location);

        renderRegistry.put(stack, location);
    }
}
