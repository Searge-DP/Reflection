/*
 * This file ("LightNetworkHandler.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.network;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.api.internal.ILightNetworkHandler;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.mod.util.WorldUtil;
import de.ellpeck.reflection.mod.world.WorldData;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LightNetworkHandler implements ILightNetworkHandler{

    private static final String TAG_CONNECTIONS = "Connections";

    public Map<Integer, Set<ILightNetwork>> allNetworks = new ConcurrentHashMap<Integer, Set<ILightNetwork>>();

    public Set<ILightNetwork> getNetworksForDimension(int dimension){
        if(this.allNetworks.get(dimension) == null){
            this.allNetworks.put(dimension, new ConcurrentSet<ILightNetwork>());
        }
        return this.allNetworks.get(dimension);
    }

    public void addNetwork(ILightNetwork network, int dimension){
        Set<ILightNetwork> networks = this.getNetworksForDimension(dimension);
        networks.add(network);

        WorldData.makeDirty();
    }

    public void removeNetwork(ILightNetwork network, int dimension){
        Set<ILightNetwork> networks = this.getNetworksForDimension(dimension);
        networks.remove(network);

        WorldData.makeDirty();
    }

    @Override
    public Set<IConnectionPair> getConnectionsForComponent(BlockPos component, int dimension){
        Set<IConnectionPair> connections = new ConcurrentSet<IConnectionPair>();

        ILightNetwork network = this.getNetworkForComponent(component, dimension);
        if(network != null){
            for(IConnectionPair pair : network.getConnections()){
                if(pair.contains(component)){
                    connections.add(pair);
                }
            }
        }
        return connections;
    }

    @Override
    public ILightNetwork getNetworkForComponent(BlockPos component, int dimension){
        Set<ILightNetwork> networks = this.getNetworksForDimension(dimension);

        for(ILightNetwork network : networks){
            for(IConnectionPair pair : network.getConnections()){
                if(pair.contains(component)){
                    return network;
                }
            }
        }
        return null;
    }

    @Override
    public void removeConnections(BlockPos component, World world){
        ILightNetwork oldNetwork = this.getNetworkForComponent(component, world.provider.getDimensionId());
        if(oldNetwork != null){
            this.removeNetwork(oldNetwork, world.provider.getDimensionId());

            for(IConnectionPair pair : oldNetwork.getConnections()){
                if(!pair.contains(component)){
                    this.addConnection(pair.getFirst(), pair.getSecond(), world, false);
                }
            }

            for(Map.Entry<BlockPos, Integer> entry : oldNetwork.getLightGenAndUsageMap().entrySet()){
                if(!entry.getKey().equals(component)){
                    ILightNetwork newNetwork = this.getNetworkForComponent(entry.getKey(), world.provider.getDimensionId());
                    if(newNetwork != null){
                        newNetwork.getLightGenAndUsageMap().put(entry.getKey(), entry.getValue());
                    }
                }
            }

            WorldData.makeDirty();
        }
    }

    @Override
    public String addConnection(BlockPos first, BlockPos second, World world, boolean validate){
        if(first == null || second == null){
            return "connectToNothing";
        }
        else if(first.equals(second)){
            return "connectToSame";
        }
        else{
            int dimension = world.provider.getDimensionId();
            if(validate){
                TileEntity firstTile = world.getTileEntity(first);
                TileEntity secondTile = world.getTileEntity(second);
                if(!(firstTile instanceof ILightComponent) || !(secondTile instanceof ILightComponent)){
                    return "connectToNotLightComponent";
                }
                else{
                    ILightComponent firstComp = (ILightComponent)firstTile;
                    ILightComponent secondComp = (ILightComponent)secondTile;

                    if(!(firstComp.canBeInNetworkWith(secondComp) || secondComp.canBeInNetworkWith(firstComp))){
                        return "connectNetworkError";
                    }
                    else if(Math.sqrt(first.distanceSq(second)) > Math.min(firstComp.getMaxDistanceFromComponent(), secondComp.getMaxDistanceFromComponent())){
                        return "connectTooFarAway";
                    }
                    else{
                        Set<IConnectionPair> firstConnections = this.getConnectionsForComponent(first, dimension);
                        Set<IConnectionPair> secondConnections = this.getConnectionsForComponent(second, dimension);
                        if((firstConnections != null && firstConnections.size() >= firstComp.getMaxConnections()) || (secondConnections != null && secondConnections.size() >= secondComp.getMaxConnections())){
                            return "connectEnoughConnections";
                        }
                    }
                }
            }

            ILightNetwork firstNet = this.getNetworkForComponent(first, dimension);
            ILightNetwork secondNet = this.getNetworkForComponent(second, dimension);

            if(firstNet == null && secondNet == null){
                ILightNetwork net = new LightNetwork();
                net.getConnections().add(new ConnectionPair(first, second));
                this.addNetwork(net, dimension);
            }
            else if(firstNet == secondNet){
                return "connectSameNetwork";
            }
            else if(firstNet == null){
                secondNet.getConnections().add(new ConnectionPair(first, second));
            }
            else if(secondNet == null){
                firstNet.getConnections().add(new ConnectionPair(first, second));
            }
            else{
                this.mergeNetworks(firstNet, secondNet, dimension);
                firstNet.getConnections().add(new ConnectionPair(first, second));
            }
        }
        WorldData.makeDirty();

        return null;
    }

    @Override
    public Map<Integer, Set<ILightNetwork>> getAllNetworks(){
        return this.allNetworks;
    }


    private void mergeNetworks(ILightNetwork first, ILightNetwork second, int dimension){
        first.getConnections().addAll(second.getConnections());
        first.getLightGenAndUsageMap().putAll(second.getLightGenAndUsageMap());

        this.removeNetwork(second, dimension);

        WorldData.makeDirty();
    }

    @Override
    public void writeConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound){
        NBTTagList list = new NBTTagList();

        Set<IConnectionPair> connections = ReflectionAPI.theLightNetworkHandler.getConnectionsForComponent(tile.getPosition(), tile.getTheWorld().provider.getDimensionId());
        if(connections != null && !connections.isEmpty()){
            for(IConnectionPair pair : connections){
                NBTTagCompound pairCompound = new NBTTagCompound();
                pair.writeToNBT(pairCompound);
                list.appendTag(pairCompound);
            }
        }

        compound.setTag(TAG_CONNECTIONS, list);
    }

    @Override
    public void readConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound){
        if(compound.hasKey(TAG_CONNECTIONS)){
            NBTTagList list = compound.getTagList(TAG_CONNECTIONS, 10);
            if(list.hasNoTags()){
                ReflectionAPI.theLightNetworkHandler.removeConnections(tile.getPosition(), tile.getTheWorld());
            }
            else{
                Set<IConnectionPair> connections = ReflectionAPI.theLightNetworkHandler.getConnectionsForComponent(tile.getPosition(), tile.getTheWorld().provider.getDimensionId());
                for(int i = 0; i < list.tagCount(); i++){
                    LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                    if(!connections.contains(pair)){
                        ReflectionAPI.theLightNetworkHandler.addConnection(pair.first, pair.second, tile.getTheWorld(), false);
                    }
                }
            }
        }
    }

    public static class ConnectionPair implements IConnectionPair{

        private static final String TAG_FIRST = "FirstConnection";
        private static final String TAG_SECOND = "SecondConnection";
        public BlockPos first;
        public BlockPos second;

        public ConnectionPair(BlockPos first, BlockPos second){
            this.first = first;
            this.second = second;
        }

        public static ConnectionPair readFromNBT(NBTTagCompound compound){
            BlockPos firstPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_FIRST));
            BlockPos secondPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_SECOND));
            return new ConnectionPair(firstPos, secondPos);
        }

        public String toString(){
            return this.first+" - "+this.second;
        }

        @Override
        public boolean contains(BlockPos component){
            return component.equals(this.first) || component.equals(this.second);
        }

        @Override
        public void writeToNBT(NBTTagCompound compound){
            NBTTagCompound firstCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(firstCompound, this.first);
            compound.setTag(TAG_FIRST, firstCompound);

            NBTTagCompound secondCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(secondCompound, this.second);
            compound.setTag(TAG_SECOND, secondCompound);
        }

        @Override
        public BlockPos getFirst(){
            return this.first;
        }

        @Override
        public BlockPos getSecond(){
            return this.second;
        }
    }
}
