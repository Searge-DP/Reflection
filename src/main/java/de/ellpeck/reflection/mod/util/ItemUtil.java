/*
 * This file ("RegistryUtil.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.util;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.creative.CreativeTab;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemUtil{

    public static void registerBlock(Block block, String name, boolean addTab){
        block.setUnlocalizedName(LibNames.BASE_TRANSLATOR+name);

        block.setRegistryName(LibMod.MOD_ID, name);
        GameRegistry.registerBlock(block);

        block.setCreativeTab(addTab ? CreativeTab.instance : null);
    }

    public static void registerItem(Item item, String name, boolean addTab){
        item.setUnlocalizedName(LibNames.BASE_TRANSLATOR+name);

        item.setRegistryName(LibMod.MOD_ID, name);
        GameRegistry.registerItem(item);

        item.setCreativeTab(addTab ? CreativeTab.instance : null);
    }

    public static void rechargeItemFromLight(ItemStack stack, Entity entity){
        if(!entity.worldObj.isRemote && entity instanceof EntityPlayer){
            if(stack.getItemDamage() > 0){
                if(entity.worldObj.getTotalWorldTime()%40 == 0){
                    EntityPlayer player = ((EntityPlayer)entity);
                    int toExtract = 5;

                    int amountDrained = ReflectionAPI.theMethodHandler.extractLightFromInventory(player, toExtract, false);
                    if(amountDrained >= toExtract){
                        ReflectionAPI.theMethodHandler.extractLightFromInventory(player, amountDrained, true);
                        stack.setItemDamage(stack.getItemDamage()-1);
                    }
                }
            }
        }
    }
}
