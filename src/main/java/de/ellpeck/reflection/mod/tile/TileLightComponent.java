package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import de.ellpeck.reflection.mod.network.LightNetwork;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public abstract class TileLightComponent extends TileEntityBase{

    private static final String TAG_CONNECTIONS = "Connections";

    @Override
    public void invalidate(){
        LightNetwork.instance.removeConnections(this.getPos(), this.worldObj);
    }

    public abstract LightNetworkTier getTier();

    public abstract boolean canBeInNetworkWith(TileLightComponent component);

    public abstract int getMaxConnections();

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            LightNetwork.instance.removeConnections(this.pos, this.worldObj);

            if(compound != null){
                NBTTagList list = compound.getTagList(TAG_CONNECTIONS, 10);
                for(int i = 0; i < list.tagCount(); i++){
                    LightNetwork.ConnectionPair pair = LightNetwork.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                    LightNetwork.instance.addConnection(pair.first, pair.second, this.worldObj, false);
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            Set<LightNetwork.ConnectionPair> connections = LightNetwork.instance.getConnectionsForComponent(this.getPos(), this.worldObj.provider.getDimensionId());

            if(connections != null){
                NBTTagList list = new NBTTagList();
                for(LightNetwork.ConnectionPair pair : connections){
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
