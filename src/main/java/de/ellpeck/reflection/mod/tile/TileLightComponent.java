package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import de.ellpeck.reflection.mod.network.LightNetwork;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ITickable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public abstract class TileLightComponent extends TileEntityBase{

    private static final String TAG_CONNECTIONS = "Connections";

    public abstract LightNetworkTier getTier();

    public abstract boolean canBeInNetworkWith(TileLightComponent component);

    public abstract int getMaxConnections();

    public abstract int getMaxDistanceFromComponent();

    @Override
    public void invalidate(){
        LightNetworkHandler.instance.removeConnections(this.getPos(), this.worldObj);
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            if(compound.hasKey(TAG_CONNECTIONS)){
                NBTTagList list = compound.getTagList(TAG_CONNECTIONS, 10);
                if(list.hasNoTags()){
                    LightNetworkHandler.instance.removeConnections(this.getPos(), this.worldObj);
                }
                else{
                    Set<LightNetworkHandler.ConnectionPair> connections = LightNetworkHandler.instance.getConnectionsForComponent(this.getPos(), this.getWorld().provider.getDimensionId());
                    for(int i = 0; i < list.tagCount(); i++){
                        LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                        if(!connections.contains(pair)){
                            LightNetworkHandler.instance.addConnection(pair.first, pair.second, this.worldObj, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        if(sync){
            NBTTagList list = new NBTTagList();

            Set<LightNetworkHandler.ConnectionPair> connections = LightNetworkHandler.instance.getConnectionsForComponent(this.getPos(), this.getWorld().provider.getDimensionId());
            if(connections != null && !connections.isEmpty()){
                for(LightNetworkHandler.ConnectionPair pair : connections){
                    NBTTagCompound pairCompound = new NBTTagCompound();
                    pair.writeToNBT(pairCompound);
                    list.appendTag(pairCompound);
                }
            }

            compound.setTag(TAG_CONNECTIONS, list);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
        return INFINITE_EXTENT_AABB;
    }
}
