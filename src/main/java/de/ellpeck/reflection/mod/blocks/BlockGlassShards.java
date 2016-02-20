/*
 * This file ("BlockGlassShards.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.misc.DamageSources;
import de.ellpeck.reflection.mod.tile.TileGlassShards;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockGlassShards extends BlockContainerBase{

    public BlockGlassShards(){
        super(Material.glass, LibNames.BLOCK_GLASS_SHARDS, true, TileGlassShards.class, LibNames.TILE_GLASS_SHARDS);
        this.setStepSound(soundTypeGlass);
        this.setHardness(2.0F);

        this.setBlockBounds(0F, 0F, 0F, 1F, 1F/16F, 1F);
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
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
        entity.attackEntityFrom(DamageSources.DAMAGE_GLASS_SHARDS, 1.0F);
    }

    @Override
    public int quantityDropped(Random random){
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer(){
        return EnumWorldBlockLayer.CUTOUT;
    }
}
