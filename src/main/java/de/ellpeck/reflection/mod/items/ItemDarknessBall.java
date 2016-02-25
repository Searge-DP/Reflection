/*
 * This file ("ItemDarknessBall.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ItemDarknessBall extends ItemBase{

    private static Map<BlockPos, IBlockState> multiblock = new HashMap<BlockPos, IBlockState>();

    public static void init(){
        multiblock.put(new BlockPos(0, 0, 0), Blocks.diamond_block.getDefaultState());

        multiblock.put(new BlockPos(-2, 0, 0), InitBlocks.blockLightanium.getDefaultState());
        multiblock.put(new BlockPos(2, 0, 0), InitBlocks.blockLightanium.getDefaultState());
        multiblock.put(new BlockPos(0, 0, -2), InitBlocks.blockLightanium.getDefaultState());
        multiblock.put(new BlockPos(0, 0, 2), InitBlocks.blockLightanium.getDefaultState());

        multiblock.put(new BlockPos(-3, 0, -3), InitBlocks.blockOreLightanium.getDefaultState());
        multiblock.put(new BlockPos(3, 0, -3), InitBlocks.blockOreLightanium.getDefaultState());
        multiblock.put(new BlockPos(3, 0, 3), InitBlocks.blockOreLightanium.getDefaultState());
        multiblock.put(new BlockPos(-3, 0, 3), InitBlocks.blockOreLightanium.getDefaultState());
    }

    public ItemDarknessBall(){
        super(LibNames.ITEM_DARKNESS_BALL, true);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos posHit, EnumFacing side, float hitX, float hitY, float hitZ){
        if(WorldUtil.areBlocksInRelativePlaces(world, posHit, multiblock)){
            if(!world.isRemote){

            }
            return true;
        }
        return false;
    }
}
