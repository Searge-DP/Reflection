package de.ellpeck.reflection.mod.network;

import de.ellpeck.reflection.mod.tile.TileLightComponent;
import de.ellpeck.reflection.mod.util.WorldUtil;
import de.ellpeck.reflection.mod.world.WorldData;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LightNetwork{

    public static LightNetwork instance;

    public Map<Integer, Set<Network>> allNetworks = new ConcurrentHashMap<Integer, Set<Network>>();

    public Set<Network> getNetworksForDimension(int dimension){
        if(this.allNetworks.get(dimension) == null){
            this.allNetworks.put(dimension, new ConcurrentSet<Network>());
        }
        return this.allNetworks.get(dimension);
    }

    public void addNetwork(Network network, int dimension){
        Set<Network> networks = this.getNetworksForDimension(dimension);
        networks.add(network);

        WorldData.makeDirty();
    }

    public void removeNetwork(Network network, int dimension){
        Set<Network> networks = this.getNetworksForDimension(dimension);
        networks.remove(network);

        WorldData.makeDirty();
    }

    public Set<Network> getNetworkListForNetwork(Network network){
        for(Set<Network> networks : this.allNetworks.values()){
            if(networks.contains(network)){
                return networks;
            }
        }
        return null;
    }

    public Set<ConnectionPair> getConnectionsForComponent(BlockPos component, int dimension){
        Set<ConnectionPair> connections = new ConcurrentSet<ConnectionPair>();

        Network network = this.getNetworkForComponent(component, dimension);
        if(network != null){
            for(ConnectionPair pair : network.connections){
                if(pair.contains(component)){
                    connections.add(pair);
                }
            }
        }
        return connections;
    }

    public Network getNetworkForComponent(BlockPos component, int dimension){
        Set<Network> networks = this.getNetworksForDimension(dimension);

        for(Network network : networks){
            for(ConnectionPair pair : network.connections){
                if(pair.contains(component)){
                    return network;
                }
            }
        }
        return null;
    }

    public void removeConnections(BlockPos component, World world){
        Network network = this.getNetworkForComponent(component, world.provider.getDimensionId());
        if(network != null){
            this.getNetworkListForNetwork(network).remove(network);

            for(ConnectionPair pair : network.connections){
                if(!pair.contains(component)){
                    this.addConnection(pair.first, pair.second, world, false);
                }
            }
            WorldData.makeDirty();
        }
    }

    public boolean addConnection(BlockPos first, BlockPos second, World world, boolean validate){
        if(first == null || second == null){
            return false;
        }
        else if(first == second){
            return false;
        }
        else{
            if(validate){
                TileEntity firstTile = world.getTileEntity(first);
                TileEntity secondTile = world.getTileEntity(second);
                if(!(firstTile instanceof TileLightComponent) || !(secondTile instanceof TileLightComponent)){
                    return false;
                }
                else{
                    TileLightComponent firstComp = (TileLightComponent)firstTile;
                    TileLightComponent secondComp = (TileLightComponent)secondTile;
                    if(!(firstComp.canBeInNetworkWith(secondComp) || secondComp.canBeInNetworkWith(firstComp))){
                        return false;
                    }
                }
            }

            int dimension = world.provider.getDimensionId();
            Network firstNet = this.getNetworkForComponent(first, dimension);
            Network secondNet = this.getNetworkForComponent(second, dimension);

            if(firstNet == null && secondNet == null){
                Network net = new Network();
                net.connections.add(new ConnectionPair(first, second));
                this.addNetwork(net, dimension);
            }
            else if(firstNet == secondNet){
                return false;
            }
            else if(firstNet == null){
                secondNet.connections.add(new ConnectionPair(first, second));
            }
            else if(secondNet == null){
                firstNet.connections.add(new ConnectionPair(first, second));
            }
            else{
                this.mergeNetworks(firstNet, secondNet);
                firstNet.connections.add(new ConnectionPair(first, second));
            }
        }
        WorldData.makeDirty();

        return true;
    }


    private void mergeNetworks(Network first, Network second){
        for(ConnectionPair pair : second.connections){
            first.connections.add(pair);
        }
        this.getNetworkListForNetwork(second).remove(second);

        WorldData.makeDirty();
    }

    public static class ConnectionPair{

        public BlockPos first;
        public BlockPos second;

        private static final String TAG_FIRST = "FirstConnection";
        private static final String TAG_SECOND = "SecondConnection";

        public ConnectionPair(BlockPos first, BlockPos second){
            this.first = first;
            this.second = second;
        }

        public String toString(){
            return this.first+" - "+this.second;
        }

        public boolean contains(BlockPos component){
            return component.equals(this.first) || component.equals(this.second);
        }

        public void writeToNBT(NBTTagCompound compound){
            NBTTagCompound firstCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(firstCompound, this.first);
            compound.setTag(TAG_FIRST, firstCompound);

            NBTTagCompound secondCompound = new NBTTagCompound();
            WorldUtil.writeBlockPosToNBT(firstCompound, this.second);
            compound.setTag(TAG_SECOND, firstCompound);
        }

        public static ConnectionPair readFromNBT(NBTTagCompound compound){
            BlockPos firstPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_FIRST));
            BlockPos secondPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_SECOND));
            return new ConnectionPair(firstPos, secondPos);
        }

    }

    public static class Network{

        public Set<ConnectionPair> connections;

        public Network(){
            this.connections = new ConcurrentSet<ConnectionPair>();
        }

        public String toString(){
            return this.connections.toString();
        }

    }
}
