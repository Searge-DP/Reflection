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

import de.ellpeck.reflection.api.light.render.LightComponentSpecialRenderer;
import de.ellpeck.reflection.mod.tile.TileMirrorBase;
import de.ellpeck.reflection.mod.util.ClientUtil;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy{

    private static Map<ItemStack, ResourceLocation> modelLocationsForRegistering = new HashMap<ItemStack, ResourceLocation>();
    private static Map<Item, ResourceLocation[]> modelVariantsForRegistering = new HashMap<Item, ResourceLocation[]>();

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);

        for(Map.Entry<Item, ResourceLocation[]> entry : modelVariantsForRegistering.entrySet()){
            ModelBakery.registerItemVariants(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);

        ClientRegistry.bindTileEntitySpecialRenderer(TileMirrorBase.class, new LightComponentSpecialRenderer());

        for(Map.Entry<ItemStack, ResourceLocation> entry : modelLocationsForRegistering.entrySet()){
            ClientUtil.mc().getRenderItem().getItemModelMesher().register(entry.getKey().getItem(), entry.getKey().getItemDamage(), new ModelResourceLocation(entry.getValue(), "inventory"));
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public void addRenderRegister(ItemStack stack, ResourceLocation location){
        super.addRenderRegister(stack, location);

        modelLocationsForRegistering.put(stack, location);
    }

    @Override
    public void addRenderVariant(Item item, ResourceLocation... location){
        super.addRenderVariant(item, location);

        modelVariantsForRegistering.put(item, location);
    }
}
