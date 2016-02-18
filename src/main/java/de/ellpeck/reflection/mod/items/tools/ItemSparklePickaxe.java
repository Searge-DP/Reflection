/*
 * This file ("ItemSparklePickaxe.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items.tools;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSparklePickaxe extends ItemReflectionPickaxe{

    public ItemSparklePickaxe(String name, boolean addTab, ToolMaterial material){
        super(name, addTab, material);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
        IBlockState state = world.getBlockState(pos);
        if(state != null && state.getBlock() != null){
            if(!state.getBlock().isReplaceable(world, pos)){
                pos = pos.offset(side);
            }

            Block block = InitBlocks.blockSparkle;
            if(world.canBlockBePlaced(block, pos, false, side, null, stack)){
                int toExtract = 100;
                int amountDrained = ReflectionAPI.theMethodHandler.extractLightFromInventory(player, toExtract, false);
                if(amountDrained >= toExtract){
                    if(!world.isRemote){
                        ReflectionAPI.theMethodHandler.extractLightFromInventory(player, amountDrained, true);
                        if(world.setBlockState(pos, InitBlocks.blockSparkle.getDefaultState(), 3)){
                            world.playSoundEffect(pos.getX()+0.5, pos.getY()+0.5F, pos.getZ()+0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume()+1.0F)/2.0F, block.stepSound.getFrequency()*0.8F);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
