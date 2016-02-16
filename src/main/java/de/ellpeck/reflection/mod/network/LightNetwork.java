/*
 * This file ("LightNetwork.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.network;

import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.mod.util.WorldUtil;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LightNetwork implements ILightNetwork{

    private static final String TAG_NETWORK = "Network";
    private static final String TAG_LIGHT = "Light";
    private static final String TAG_AMOUNT = "Amount";

    public Set<IConnectionPair> connections;
    public Map<BlockPos, Integer> lightGenAndUsage;

    public LightNetwork(){
        this.connections = new ConcurrentSet<IConnectionPair>();
        this.lightGenAndUsage = new HashMap<BlockPos, Integer>();
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

    @Override
    public void addLightUser(TileEntity tile, int amount){
        this.lightGenAndUsage.put(tile.getPos(), -amount);
    }

    @Override
    public void addLightGen(TileEntity tile, int amount){
        this.lightGenAndUsage.put(tile.getPos(), amount);
    }

    @Override
    public void removeLightUser(TileEntity tile){
        this.lightGenAndUsage.remove(tile.getPos());
    }

    @Override
    public void removeLightGen(TileEntity tile){
        this.lightGenAndUsage.remove(tile.getPos());
    }

    @Override
    public int getTotalLightUsed(){
        return this.getTotalLightUsedExcluded(null);
    }

    @Override
    public int getTotalLightUsedExcluded(TileEntity tile){
        int used = 0;
        for(Map.Entry<BlockPos, Integer> entry : this.lightGenAndUsage.entrySet()){
            if((tile == null || !entry.getKey().equals(tile.getPos())) && entry.getValue() < 0){
                used -= entry.getValue();
            }
        }
        return used;
    }

    @Override
    public int getTotalLightGenerated(){
        return this.getTotalLightGeneratedExcluded(null);
    }

    @Override
    public int getTotalLightGeneratedExcluded(TileEntity tile){
        int generated = 0;
        for(Map.Entry<BlockPos, Integer> entry : this.lightGenAndUsage.entrySet()){
            if((tile == null || !entry.getKey().equals(tile.getPos())) && entry.getValue() > 0){
                generated += entry.getValue();
            }
        }
        return generated;
    }

    @Override
    public int getTotalLight(){
        return this.getTotalLightExcluded(null);
    }

    @Override
    public int getTotalLightExcluded(TileEntity tile){
        return this.getTotalLightGeneratedExcluded(tile)-this.getTotalLightUsedExcluded(tile);
    }

    @Override
    public Set<IConnectionPair> getConnections(){
        return this.connections;
    }

    @Override
    public Map<BlockPos, Integer> getLightGenAndUsageMap(){
        return this.lightGenAndUsage;
    }

    public String toString(){
        return this.connections.toString();
    }

    @Override
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
        for(IConnectionPair pair : this.connections){
            NBTTagCompound pairCompound = new NBTTagCompound();
            pair.writeToNBT(pairCompound);
            aNetwork.appendTag(pairCompound);
        }
        compound.setTag(TAG_NETWORK, aNetwork);
    }
}
