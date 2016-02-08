package de.ellpeck.reflection.mod.network;

import de.ellpeck.reflection.mod.util.WorldUtil;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LightNetwork{

    private static final String TAG_NETWORK = "Network";
    private static final String TAG_LIGHT = "Light";
    private static final String TAG_AMOUNT = "Amount";

    public Set<LightNetworkHandler.ConnectionPair> connections;
    public Map<BlockPos, Integer> lightGenAndUsage;

    public LightNetwork(){
        this.connections = new ConcurrentSet<LightNetworkHandler.ConnectionPair>();
        this.lightGenAndUsage = new HashMap<BlockPos, Integer>();
    }

    public void addLightUser(TileEntity tile, int amount){
        this.lightGenAndUsage.put(tile.getPos(), -amount);
    }

    public void addLightGen(TileEntity tile, int amount){
        this.lightGenAndUsage.put(tile.getPos(), amount);
    }

    public void removeLightUser(TileEntity tile){
        this.lightGenAndUsage.remove(tile.getPos());
    }

    public void removeLightGen(TileEntity tile){
        this.lightGenAndUsage.remove(tile.getPos());
    }

    public int getTotalLightUsed(){
        int used = 0;
        for(int light : this.lightGenAndUsage.values()){
            if(light < 0){
                used+=light;
            }
        }
        return used;
    }

    public int getTotalLightGenerated(){
        int generated = 0;
        for(int light : this.lightGenAndUsage.values()){
            if(light > 0){
                generated+=light;
            }
        }
        return generated;
    }

    public int getTotalLightLeft(){
        int left = 0;
        for(int light : this.lightGenAndUsage.values()){
            left+=light;
        }
        return left;
    }

    public String toString(){
        return this.connections.toString();
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList light = new NBTTagList();
        for(Map.Entry<BlockPos, Integer> entry : this.lightGenAndUsage.entrySet()){
            NBTTagCompound lightCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(lightCompound, entry.getKey());
            lightCompound.setInteger(TAG_AMOUNT, entry.getValue());
            light.appendTag(lightCompound);
        }
        compound.setTag(TAG_LIGHT, light);

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

        NBTTagList light = compound.getTagList(TAG_LIGHT, 10);
        for(int i = 0; i < light.tagCount(); i++){
            NBTTagCompound lightCompound = light.getCompoundTagAt(i);
            BlockPos pos = WorldUtil.readBlockPosFromNBT(lightCompound);
            int amount = lightCompound.getInteger(TAG_AMOUNT);
            newNetwork.lightGenAndUsage.put(pos, amount);
        }

        NBTTagList aNetwork = compound.getTagList(TAG_NETWORK, 10);
        for(int i = 0; i < aNetwork.tagCount(); i++){
            NBTTagCompound pairCompound = aNetwork.getCompoundTagAt(i);
            LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(pairCompound);
            newNetwork.connections.add(pair);
        }

        return newNetwork;
    }
}
