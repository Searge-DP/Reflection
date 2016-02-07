package de.ellpeck.reflection.mod.world;

import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.network.LightNetwork;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

import java.util.Map;
import java.util.Set;

public class WorldData extends WorldSavedData{

    public static final String DATA_TAG = LibMod.MOD_NAME+"WorldData";
    private static WorldData instance;

    private static final String TAG_DIMENSION = "Dim";
    private static final String TAG_NETWORKS = "Networks";
    private static final String TAG_LIGHT_NETWORK = "LightNetwork";
    private static final String TAG_NETWORK = "Network";
    private static final String TAG_LIGHT_AMOUNT = "LightAmount";

    public WorldData(String name){
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        NBTTagList lightNetworkList = compound.getTagList(TAG_LIGHT_NETWORK, 10);
        for(int lightNetwork = 0; lightNetwork < lightNetworkList.tagCount(); lightNetwork++){
            Set<LightNetwork> networksForDim = new ConcurrentSet<LightNetwork>();

            NBTTagCompound dimCompound = lightNetworkList.getCompoundTagAt(lightNetwork);
            int dimension = dimCompound.getInteger(TAG_DIMENSION);

            NBTTagList allNetworks = dimCompound.getTagList(TAG_NETWORKS, 10);
            for(int networks = 0; networks < allNetworks.tagCount(); networks++){

                LightNetwork newNetwork = new LightNetwork();
                NBTTagCompound networkCompound = allNetworks.getCompoundTagAt(networks);
                NBTTagList aNetwork = networkCompound.getTagList(TAG_NETWORK, 10);

                for(int network = 0; network < aNetwork.tagCount(); network++){
                    NBTTagCompound pairCompound = aNetwork.getCompoundTagAt(network);
                    LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(pairCompound);
                    newNetwork.connections.add(pair);
                }
                networksForDim.add(newNetwork);
            }
            LightNetworkHandler.instance.allNetworks.put(dimension, networksForDim);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        NBTTagList lightNetworkList = new NBTTagList();
        for(Map.Entry<Integer, Set<LightNetwork>> networks : LightNetworkHandler.instance.allNetworks.entrySet()){
            NBTTagCompound dimCompound = new NBTTagCompound();
            dimCompound.setInteger(TAG_DIMENSION, networks.getKey());

            NBTTagList allNetworks = new NBTTagList();
            for(LightNetwork network : networks.getValue()){

                NBTTagCompound networkCompound = new NBTTagCompound();
                NBTTagList aNetwork = new NBTTagList();

                for(LightNetworkHandler.ConnectionPair pair : network.connections){
                    NBTTagCompound pairCompound = new NBTTagCompound();
                    pair.writeToNBT(pairCompound);
                    aNetwork.appendTag(pairCompound);
                }
                networkCompound.setTag(TAG_NETWORK, aNetwork);
                allNetworks.appendTag(networkCompound);
            }
            dimCompound.setTag(TAG_NETWORKS, allNetworks);
            lightNetworkList.appendTag(dimCompound);
        }
        compound.setTag(TAG_LIGHT_NETWORK, lightNetworkList);
    }

    public static void clearOldData(){
        if(!LightNetworkHandler.instance.allNetworks.isEmpty()){
            LibMod.LOGGER.info("Clearing LightNetwork Data from other worlds...");
            LightNetworkHandler.instance.allNetworks.clear();
        }
    }

    public static void makeDirty(){
        if(instance != null){
            instance.markDirty();
        }
    }

    public static void init(MinecraftServer server){
        if(server != null){
            World world = server.getEntityWorld();
            if(!world.isRemote){
                clearOldData();
                LibMod.LOGGER.info("Loading WorldData!");

                WorldData savedData = (WorldData)world.loadItemData(WorldData.class, DATA_TAG);

                if(savedData == null){
                    LibMod.LOGGER.info("No WorldData found, creating...");

                    savedData = new WorldData(DATA_TAG);
                    world.setItemData(DATA_TAG, savedData);
                }
                else{
                    LibMod.LOGGER.info("WorldData successfully received!");
                }

                instance = savedData;
            }
        }
    }

}
