package de.ellpeck.reflection.api.light;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.TileEntityBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A TileEntity that generates or uses light and/or can be connected to a light network
 */
public abstract class TileLightComponent extends TileEntityBase{

    /**
     * The tier of the component
     */
    public abstract LightNetworkTier getTier();

    /**
     * Checks if this component can be in a network with another component.
     * Note that this is called for both components and if at least one of them
     * returns true, the connection will be made.
     *
     * So it isn't certain that, if you return true here, the connection won't happen.
     *
     * @param component The component to connect to
     */
    public abstract boolean canBeInNetworkWith(TileLightComponent component);

    /**
     * @return The maximum amount of connections this component can have in a network
     */
    public abstract int getMaxConnections();

    /**
     * Returns the maximum distance between this component and another one
     * Note that the smaller of the max distances of two components will always be picked
     */
    public abstract int getMaxDistanceFromComponent();

    @Override
    public void invalidate(){
        //This removes the tile from a network
        //Be sure to use super when overriding this method
        ReflectionAPI.theLightNetworkHandler.removeConnections(this.getPos(), this.worldObj);
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
        if(sync){
            ReflectionAPI.theLightNetworkHandler.readConnectionInfoNBT(this, compound);
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        //When syncing, the client will be notified of the network this component is in
        //Be sure to use super when overriding this method
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
        return INFINITE_EXTENT_AABB;
    }
}
