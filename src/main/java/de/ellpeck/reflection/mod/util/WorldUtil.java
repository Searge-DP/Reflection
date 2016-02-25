/*
 * This file ("WorldUtil.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.util;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.api.light.ILightComponent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public class WorldUtil{

    private static final String TAG_X = "X";
    private static final String TAG_Y = "Y";
    private static final String TAG_Z = "Z";

    public static void writeBlockPosToNBT(NBTTagCompound compound, BlockPos pos){
        compound.setInteger(TAG_X, pos.getX());
        compound.setInteger(TAG_Y, pos.getY());
        compound.setInteger(TAG_Z, pos.getZ());
    }

    public static BlockPos readBlockPosFromNBT(NBTTagCompound compound){
        int x = compound.getInteger(TAG_X);
        int y = compound.getInteger(TAG_Y);
        int z = compound.getInteger(TAG_Z);

        return new BlockPos(x, y, z);
    }

    public static boolean hasBlockPosData(NBTTagCompound compound){
        return compound.hasKey(TAG_X) && compound.hasKey(TAG_Y) && compound.hasKey(TAG_Z);
    }

    public static ILightNetwork getNetworkForTile(ILightComponent tile){
        return ReflectionAPI.getLightNetworkHandler().getNetworkForComponent(tile.getPosition(), tile.getTheWorld().provider.getDimensionId());
    }

    public static Set<IConnectionPair> getAllConnectionsForTile(ILightComponent tile){
        return ReflectionAPI.getLightNetworkHandler().getConnectionsForComponent(tile.getPosition(), tile.getTheWorld().provider.getDimensionId());
    }

    public static int getSkylight(TileEntity tile){
        if(tile.getWorld().isDaytime()){
            return tile.getWorld().getLightFor(EnumSkyBlock.SKY, tile.getPos());
        }
        else{
            return 0;
        }
    }

    public static long totalTime(World world){
        return world.getTotalWorldTime();
    }

    public static boolean areBlocksInRelativePlaces(World world, BlockPos start, Map<BlockPos, IBlockState> blocks){
        return replaceBlocksInRelativePlaces(world, start, blocks, null);
    }

    public static boolean replaceBlocksInRelativePlaces(World world, BlockPos start, Map<BlockPos, IBlockState> originals, Map<IBlockState, IBlockState> replacements){
        for(Map.Entry<BlockPos, IBlockState> entry : originals.entrySet()){
            BlockPos relative = getPosFromRelative(start, entry.getKey());
            IBlockState toBeReplaced = entry.getValue();

            if(world.getBlockState(relative).equals(toBeReplaced)){
                if(replacements != null && !replacements.isEmpty()){

                    IBlockState replacement = replacements.get(toBeReplaced);
                    if(replacement != null){
                        world.setBlockState(relative, replacement);
                        world.playAuxSFX(2001, relative, Block.getStateId(replacement));
                    }
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    public static BlockPos getPosFromRelative(BlockPos start, BlockPos relative){
        return getPosFromRelative(start, relative.getX(), relative.getY(), relative.getZ());
    }

    public static BlockPos getPosFromRelative(BlockPos start, int x, int y, int z){
        return new BlockPos(start.getX()+x, start.getY()+y, start.getZ()+z);
    }
}
