package de.ellpeck.reflection.mod.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

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

    public void writeNBT(NBTTagCompound compound, boolean sync){

    }

    public void readNBT(NBTTagCompound compound, boolean sync){

    }
}
