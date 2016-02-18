/*
 * This file ("BlockSparkle.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSparkle extends BlockBase{

    public BlockSparkle(){
        super(Material.circuits, LibNames.BLOCK_SPARKLE, false);
        this.setLightLevel(1F);

        float f = 1F/16F;
        this.setBlockBounds(6*f, 6*f, 6*f, 1F-6*f, 1F-6*f, 1F-6*f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state){
        return null;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean isFullCube(){
        return false;
    }

    @Override
    public int quantityDropped(Random random){
        return 0;
    }
}
