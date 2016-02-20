/*
 * This file ("BlockCoallector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier1;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.tier1.TileCoallector;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCoallector extends BlockLightComponentBase{

    public BlockCoallector(){
        super(Material.anvil, LibNames.BLOCK_COALLECTOR, ReflectionAPI.TIER_1, true, TileCoallector.class, LibNames.TILE_COALLECTOR);
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand){
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof TileCoallector){
            if(((TileCoallector)tile).burnTime > 0){
                float f = 1F/16F;

                float x = pos.getX()+2*f;
                float y = pos.getY()+1F-2*f;
                float z = pos.getZ()+2*f;
                float randomX = x+LibMod.RANDOM.nextFloat()*12*f;
                float randomZ = z+LibMod.RANDOM.nextFloat()*12*f;

                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, randomX, y, randomZ, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, randomX, y, randomZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
