package de.ellpeck.reflection.mod.network;

import de.ellpeck.reflection.mod.tile.TileLightComponent;
import de.ellpeck.reflection.mod.util.WorldUtil;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class LightNetwork{

    private static final String TAG_NETWORK = "Network";

    public Set<LightNetworkHandler.ConnectionPair> connections;

    public LightNetwork(){
        this.connections = new ConcurrentSet<LightNetworkHandler.ConnectionPair>();
    }

    public String toString(){
        return this.connections.toString();
    }

    public int getTotalLight(World world, boolean used){
        int light = 0;
        for(LightNetworkHandler.ConnectionPair pair : this.connections){
            light+=this.getTotalLightOf(pair.first, world, used);
            light+=this.getTotalLightOf(pair.second, world, used);
        }
        return light;
    }

    public int getLeftoverLight(World world){
        return this.getTotalLight(world, false)-this.getTotalLight(world, true);
    }

    private int getTotalLightOf(BlockPos pos, World world, boolean used){
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof TileLightComponent){
            if(used){
                return ((TileLightComponent)tile).getUsedLight();
            }
            else{
                return ((TileLightComponent)tile).getGeneratedLight();
            }
        }
        else{
            return 0;
        }
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList aNetwork = new NBTTagList();
        for(LightNetworkHandler.ConnectionPair pair : this.connections){
            NBTTagCompound pairCompound = new NBTTagCompound();
            pair.writeToNBT(pairCompound);
            aNetwork.appendTag(pairCompound);
        }
        compound.setTag(TAG_NETWORK, aNetwork);
    }

    public static LightNetwork readFromNBT(NBTTagCompound compound){
        LightNetwork newNetwork = new LightNetwork();

        NBTTagList aNetwork = compound.getTagList(TAG_NETWORK, 10);
        for(int network = 0; network < aNetwork.tagCount(); network++){
            NBTTagCompound pairCompound = aNetwork.getCompoundTagAt(network);
            LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(pairCompound);
            newNetwork.connections.add(pair);
        }

        return newNetwork;
    }
}
