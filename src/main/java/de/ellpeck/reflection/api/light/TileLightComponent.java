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
import de.ellpeck.reflection.api.internal.TileEntityBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A TileEntity that generates or uses light and/or can be connected to a light network
 */
public abstract class TileLightComponent extends TileEntityBase implements ILightComponent{

    @Override
    public void invalidate(){
        //This removes the tile from a network
        //Be sure to use super when overriding this method
        //
        //IT IS VERY IMPORTANT THAT YOU ALWAYS CALL THIS IN invalidate()!!!
        ReflectionAPI.theLightNetworkHandler.removeConnections(this.getPos(), this.worldObj);
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
        //
        //This is not necessary, but it is used for properly rendering the light beams in the TESR
        if(sync){
            ReflectionAPI.theLightNetworkHandler.readConnectionInfoNBT(this, compound);
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
        //
        //This is not necessary, but it is used for properly rendering the light beams in the TESR
        if(sync){
            ReflectionAPI.theLightNetworkHandler.writeConnectionInfoNBT(this, compound);
        }
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
}
