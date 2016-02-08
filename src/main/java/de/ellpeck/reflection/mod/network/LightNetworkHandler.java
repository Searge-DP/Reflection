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

public class LightNetworkHandler{

    public static LightNetworkHandler instance;

    public Map<Integer, Set<LightNetwork>> allNetworks = new ConcurrentHashMap<Integer, Set<LightNetwork>>();

    public Set<LightNetwork> getNetworksForDimension(int dimension){
        if(this.allNetworks.get(dimension) == null){
            this.allNetworks.put(dimension, new ConcurrentSet<LightNetwork>());
        }
        return this.allNetworks.get(dimension);
    }

    public void addNetwork(LightNetwork network, int dimension){
        Set<LightNetwork> networks = this.getNetworksForDimension(dimension);
        networks.add(network);

        WorldData.makeDirty();
    }

    public void removeNetwork(LightNetwork network, int dimension){
        Set<LightNetwork> networks = this.getNetworksForDimension(dimension);
        networks.remove(network);

        WorldData.makeDirty();
    }

    public Set<ConnectionPair> getConnectionsForComponent(BlockPos component, int dimension){
        Set<ConnectionPair> connections = new ConcurrentSet<ConnectionPair>();

        LightNetwork network = this.getNetworkForComponent(component, dimension);
        if(network != null){
            for(ConnectionPair pair : network.connections){
                if(pair.contains(component)){
                    connections.add(pair);
                }
            }
        }
        return connections;
    }

    public LightNetwork getNetworkForComponent(BlockPos component, int dimension){
        Set<LightNetwork> networks = this.getNetworksForDimension(dimension);

        for(LightNetwork network : networks){
            for(ConnectionPair pair : network.connections){
                if(pair.contains(component)){
                    return network;
                }
            }
        }
        return null;
    }

    public void removeConnections(BlockPos component, World world){
        LightNetwork oldNetwork = this.getNetworkForComponent(component, world.provider.getDimensionId());
        if(oldNetwork != null){
            this.removeNetwork(oldNetwork, world.provider.getDimensionId());

            for(ConnectionPair pair : oldNetwork.connections){
                if(!pair.contains(component)){
                    this.addConnection(pair.first, pair.second, world, false);
                }
            }

            for(Map.Entry<BlockPos, Integer> entry : oldNetwork.lightGenAndUsage.entrySet()){
                if(!entry.getKey().equals(component)){
                    LightNetwork newNetwork = this.getNetworkForComponent(entry.getKey(), world.provider.getDimensionId());
                    if(newNetwork != null){
                        newNetwork.lightGenAndUsage.put(entry.getKey(), entry.getValue());
                    }
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
            int dimension = world.provider.getDimensionId();
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
                    else if(Math.sqrt(first.distanceSq(second)) > Math.min(firstComp.getMaxDistanceFromComponent(), secondComp.getMaxDistanceFromComponent())){
                        return false;
                    }
                    else{
                        Set<ConnectionPair> firstConnections = this.getConnectionsForComponent(first, dimension);
                        Set<ConnectionPair> secondConnections = this.getConnectionsForComponent(second, dimension);
                        if((firstConnections != null && firstConnections.size() >= firstComp.getMaxConnections()) || (secondConnections != null && secondConnections.size() >= secondComp.getMaxConnections())){
                            return false;
                        }
                    }
                }
            }

            LightNetwork firstNet = this.getNetworkForComponent(first, dimension);
            LightNetwork secondNet = this.getNetworkForComponent(second, dimension);

            if(firstNet == null && secondNet == null){
                LightNetwork net = new LightNetwork();
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
                this.mergeNetworks(firstNet, secondNet, dimension);
                firstNet.connections.add(new ConnectionPair(first, second));
            }
        }
        WorldData.makeDirty();

        return true;
    }


    private void mergeNetworks(LightNetwork first, LightNetwork second, int dimension){
        first.connections.addAll(second.connections);
        first.lightGenAndUsage.putAll(second.lightGenAndUsage);

        this.removeNetwork(second, dimension);

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
            WorldUtil.writeBlockPosToNBT(secondCompound, this.second);
            compound.setTag(TAG_SECOND, secondCompound);
        }

        public static ConnectionPair readFromNBT(NBTTagCompound compound){
            BlockPos firstPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_FIRST));
            BlockPos secondPos = WorldUtil.readBlockPosFromNBT(compound.getCompoundTag(TAG_SECOND));
            return new ConnectionPair(firstPos, secondPos);
        }

    }
}
