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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

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

}
