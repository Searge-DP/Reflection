/*
 * This file ("CommonProxy.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.proxy;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.tile.TileGlassShards;
import de.ellpeck.reflection.mod.util.InitOreDictionary;
import de.ellpeck.reflection.mod.util.MethodHandler;
import de.ellpeck.reflection.mod.world.WorldEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        ReflectionAPI.theMethodHandler = new MethodHandler();

        InitBlocks.preInit();
        InitItems.preInit();
        InitOreDictionary.preInit();
    }

    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new WorldEvents());
    }

    public void postInit(FMLPostInitializationEvent event){
        TileGlassShards.postInit();
    }

    public void addToRenderRegistry(ItemStack stack, ResourceLocation location){

    }
}
