package de.ellpeck.reflection.api.light;

import de.ellpeck.reflection.api.ReflectionAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileLightComponent extends TileEntityBase{

    public abstract LightNetworkTier getTier();

    public abstract boolean canBeInNetworkWith(TileLightComponent component);

    public abstract int getMaxConnections();

    public abstract int getMaxDistanceFromComponent();

    @Override
    public void invalidate(){
        ReflectionAPI.theLightNetworkHandler.removeConnections(this.getPos(), this.worldObj);
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            ReflectionAPI.theLightNetworkHandler.readConnectionInfoNBT(this, compound);
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            ReflectionAPI.theLightNetworkHandler.writeConnectionInfoNBT(this, compound);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
        return INFINITE_EXTENT_AABB;
    }
}
