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
    private static final String TAG_AMOUNT = "Amount";
    private static final String TAG_GENERATORS = "Generators";
    private static final String TAG_CONSUMERS = "Consumers";

    public Set<LightNetworkHandler.ConnectionPair> connections;

    public Map<BlockPos, Integer> generators;
    public Map<BlockPos, Integer> consumers;

    public LightNetwork(){
        this.connections = new ConcurrentSet<LightNetworkHandler.ConnectionPair>();
        this.generators = new HashMap<BlockPos, Integer>();
        this.consumers = new HashMap<BlockPos, Integer>();

        this.generators.put(new BlockPos(39, 30, 30), 10);
        this.generators.put(new BlockPos(49, 40, 20), 32);

        this.consumers.put(new BlockPos(237823, 23, 19), 20);
    }

    public int getGenerated(){
        int generated = 0;
        for(int i : this.generators.values()){
            generated+=i;
        }
        return generated;
    }

    public int getConsumed(){
        int consumed = 0;
        for(int i : this.consumers.values()){
            consumed+=i;
        }
        return consumed;
    }

    public int getLightLeft(){
        return this.getGenerated()-this.getConsumed();
    }

    public void addGenerator(TileEntity tile, int generation){
        this.generators.put(tile.getPos(), generation);
    }

    public void addConsumer(TileEntity tile, int generation){
        this.consumers.put(tile.getPos(), generation);
    }

    public void removeGenerator(TileEntity tile){
        this.generators.remove(tile.getPos());
    }

    public void removeConsumer(TileEntity tile){
        this.consumers.remove(tile.getPos());
    }

    public String toString(){
        return this.connections.toString();
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList gens = new NBTTagList();
        for(Map.Entry<BlockPos, Integer> generator : this.generators.entrySet()){
            NBTTagCompound genCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(genCompound, generator.getKey());
            genCompound.setInteger(TAG_AMOUNT, generator.getValue());
            gens.appendTag(genCompound);
        }
        compound.setTag(TAG_GENERATORS, gens);

        NBTTagList cons = new NBTTagList();
        for(Map.Entry<BlockPos, Integer> consumer : this.consumers.entrySet()){
            NBTTagCompound conCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(conCompound, consumer.getKey());
            conCompound.setInteger(TAG_AMOUNT, consumer.getValue());
            cons.appendTag(conCompound);
        }
        compound.setTag(TAG_CONSUMERS, cons);

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

        NBTTagList gens = compound.getTagList(TAG_GENERATORS, 10);
        for(int i = 0; i < gens.tagCount(); i++){
            NBTTagCompound genCompound = gens.getCompoundTagAt(i);
            BlockPos pos = WorldUtil.readBlockPosFromNBT(genCompound);
            int amount = genCompound.getInteger(TAG_AMOUNT);
            newNetwork.generators.put(pos, amount);
        }

        NBTTagList cons = compound.getTagList(TAG_CONSUMERS, 10);
        for(int i = 0; i < cons.tagCount(); i++){
            NBTTagCompound conCompound = cons.getCompoundTagAt(i);
            BlockPos pos = WorldUtil.readBlockPosFromNBT(conCompound);
            int amount = conCompound.getInteger(TAG_AMOUNT);
            newNetwork.consumers.put(pos, amount);
        }

        NBTTagList aNetwork = compound.getTagList(TAG_NETWORK, 10);
        for(int network = 0; network < aNetwork.tagCount(); network++){
            NBTTagCompound pairCompound = aNetwork.getCompoundTagAt(network);
            LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(pairCompound);
            newNetwork.connections.add(pair);
        }

        return newNetwork;
    }
}
