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

import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
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

    @SubscribeEvent
    public void onBlockDropped(BlockEvent.HarvestDropsEvent event){
        if(!event.world.isRemote && event.state != null){
            if(event.harvester != null && !event.isSilkTouching){
                Block block = event.state.getBlock();
                if(block instanceof BlockGlass || block instanceof BlockStainedGlass){
                    ItemStack stack = event.harvester.getCurrentEquippedItem();
                    if(stack != null && stack.getItem() == Items.flint){
                        event.drops.add(new ItemStack(InitItems.itemGlassShards, LibMod.RANDOM.nextInt(3)+1));
                    }
                }
            }
        }
    }
}
