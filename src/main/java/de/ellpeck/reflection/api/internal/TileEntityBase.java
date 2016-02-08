/*
 * This file ("TileEntityBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.internal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * An internal class for a basic TileEntity.
 * Do not extend this directly.
 *
 * Note how this disallows TileLightComponent from using readFromNBT and writeToNBT
 * directly. When writing/reading to/from NBT, use writeNBT and readNBT.
 */
public class TileEntityBase extends TileEntity{

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound compound = new NBTTagCompound();
        this.writeNBT(compound, true);
        return new S35PacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        this.readNBT(pkt.getNbtCompound(), true);
    }

    @Override
    public final void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.readNBT(compound, false);
    }

    @Override
    public final void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        this.writeNBT(compound, false);
    }

    /**
     * Writes data to NBT.
     * @param sync true for getDescriptionPacket(), false for writeToNBT()
     */
    public void writeNBT(NBTTagCompound compound, boolean sync){

    }

    /**
     * Reads data from NBT.
     * @param sync true for onDataPacket(), false for readFromNBT()
     */
    public void readNBT(NBTTagCompound compound, boolean sync){

    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
        return !newSate.getBlock().isAssociatedBlock(oldState.getBlock());
    }
}
