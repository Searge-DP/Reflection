package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public abstract class TileLightComponent extends TileEntityBase{

    private static final String TAG_CONNECTIONS = "Connections";

    public abstract LightNetworkTier getTier();

    public abstract boolean canBeInNetworkWith(TileLightComponent component);

    public abstract int getMaxConnections();

    public abstract int getMaxDistanceFromComponent();

    public abstract int getGeneratedLight();

    public abstract int getUsedLight();

    @Override
    public void invalidate(){
        LightNetworkHandler.instance.removeConnections(this.getPos(), this.worldObj);
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            LightNetworkHandler.instance.removeConnections(this.pos, this.worldObj);

            if(compound != null){
                NBTTagList list = compound.getTagList(TAG_CONNECTIONS, 10);
                for(int i = 0; i < list.tagCount(); i++){
                    LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                    LightNetworkHandler.instance.addConnection(pair.first, pair.second, this.worldObj, false);
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            Set<LightNetworkHandler.ConnectionPair> connections = LightNetworkHandler.instance.getConnectionsForComponent(this.getPos(), this.worldObj.provider.getDimensionId());

            if(connections != null){
                NBTTagList list = new NBTTagList();
                for(LightNetworkHandler.ConnectionPair pair : connections){
                    NBTTagCompound tag = new NBTTagCompound();
                    pair.writeToNBT(tag);
                    list.appendTag(tag);
                }
                compound.setTag(TAG_CONNECTIONS, list);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
        return INFINITE_EXTENT_AABB;
    }
}
