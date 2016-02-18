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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSparkle extends BlockBase{

    public BlockSparkle(){
        super(Material.circuits, LibNames.BLOCK_SPARKLE, false);
        this.setTickRandomly(true);
        this.setLightLevel(1F);

        float f = 1F/16F;
        this.setBlockBounds(4*f, 4*f, 4*f, 1F-4*f, 1F-4*f, 1F-4*f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
        double x = pos.getX()+0.5D;
        double y = pos.getY()+0.5D;
        double z = pos.getZ()+0.5D;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
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
}
