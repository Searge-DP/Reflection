package de.ellpeck.reflection.mod.network;

import io.netty.util.internal.ConcurrentSet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LightNetwork{

    public Set<LightNetworkHandler.ConnectionPair> connections;

    public Map<BlockPos, Integer> generators;
    public Map<BlockPos, Integer> consumers;

    public LightNetwork(){
        this.connections = new ConcurrentSet<LightNetworkHandler.ConnectionPair>();
        this.generators = new HashMap<BlockPos, Integer>();
        this.consumers = new HashMap<BlockPos, Integer>();
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

}
