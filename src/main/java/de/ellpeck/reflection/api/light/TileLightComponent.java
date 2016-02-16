/*
 * This file ("TileLightComponent.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

import de.ellpeck.reflection.api.ReflectionAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A TileEntity that generates or uses light and/or can be connected to a light network
 *
 * This is an example implementation, if you need to extend another class or can't use
 * this directly for any other reason, implement ILightComponent directly and invoke
 * all of the methods that are described as VERY IMPORTANT in here.
 */
public abstract class TileLightComponent extends TileEntity implements ILightComponent{

    @Override
    public void invalidate(){
        //This removes the tile from a network
        //Be sure to use super when overriding this method
        //
        //IT IS VERY IMPORTANT THAT YOU ALWAYS CALL THIS IN invalidate()!!!
        ReflectionAPI.getLightNetworkHandler().removeConnections(this.getPos(), this.worldObj);
    }

    /**
     * This is a workaround for the packet being sent in getDescriptionPacket() not giving back the
     * NBT that you gave it making it impossible to properly override getDescriptionPacket().
     *
     * If you still want the syncing to happen, but override the NBT being sent in
     * getDescriptionPacket(), just override this method instead.
     */
    public NBTTagCompound getDescriptionPacketCompound(){
        NBTTagCompound compound = new NBTTagCompound();

        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
        //
        //This is not necessary, but it is used for properly rendering the light beams in the TESR
        ReflectionAPI.theMethodHandler.writeConnectionInfoNBT(this, compound);

        return compound;
    }

    @Override
    public Packet getDescriptionPacket(){
        return new S35PacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), this.getDescriptionPacketCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
        //
        //This is not necessary, but it is used for properly rendering the light beams in the TESR
        ReflectionAPI.theMethodHandler.readConnectionInfoNBT(this, pkt.getNbtCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
        //This gets an infinite render bounding box, so that this component will call its TESR's render
        //method even when not looking at it. This is done so that the light beam renders even when the
        //block is not directly in sight.
        //
        //This is not necessary, but it is used for properly rendering the light beams in the TESR
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public BlockPos getPosition(){
        return this.getPos();
    }

    @Override
    public World getTheWorld(){
        return this.getWorld();
    }

    @Override
    public boolean canBeInNetworkWith(ILightComponent component){
        return this.getTier() == component.getTier();
    }
}
