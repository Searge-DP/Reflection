/*
 * This file ("WorldData.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.world;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.network.LightNetwork;
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
    private static final String TAG_DIMENSION = "Dim";
    private static final String TAG_NETWORKS = "Networks";
    private static final String TAG_LIGHT_NETWORK = "LightNetwork";
    private static WorldData instance;

    public WorldData(String name){
        super(name);
    }

    public static void clearOldData(){
        if(!ReflectionAPI.getLightNetworkHandler().getAllNetworks().isEmpty()){
            LibMod.LOGGER.info("Clearing LightNetwork Data from other worlds...");
            ReflectionAPI.getLightNetworkHandler().getAllNetworks().clear();
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

    @Override
    public void readFromNBT(NBTTagCompound compound){
        NBTTagList lightNetworkList = compound.getTagList(TAG_LIGHT_NETWORK, 10);
        for(int lightNetwork = 0; lightNetwork < lightNetworkList.tagCount(); lightNetwork++){
            Set<ILightNetwork> networksForDim = new ConcurrentSet<ILightNetwork>();

            NBTTagCompound dimCompound = lightNetworkList.getCompoundTagAt(lightNetwork);
            int dimension = dimCompound.getInteger(TAG_DIMENSION);

            NBTTagList allNetworks = dimCompound.getTagList(TAG_NETWORKS, 10);
            for(int networks = 0; networks < allNetworks.tagCount(); networks++){
                NBTTagCompound networkCompound = allNetworks.getCompoundTagAt(networks);
                ILightNetwork newNetwork = LightNetwork.readFromNBT(networkCompound);
                networksForDim.add(newNetwork);
            }
            ReflectionAPI.getLightNetworkHandler().getAllNetworks().put(dimension, networksForDim);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        NBTTagList lightNetworkList = new NBTTagList();
        for(Map.Entry<Integer, Set<ILightNetwork>> networks : ReflectionAPI.getLightNetworkHandler().getAllNetworks().entrySet()){
            NBTTagCompound dimCompound = new NBTTagCompound();
            dimCompound.setInteger(TAG_DIMENSION, networks.getKey());

            NBTTagList allNetworks = new NBTTagList();
            for(ILightNetwork network : networks.getValue()){
                NBTTagCompound networkCompound = new NBTTagCompound();
                LightNetwork.writeToNBT(networkCompound, network);
                allNetworks.appendTag(networkCompound);
            }
            dimCompound.setTag(TAG_NETWORKS, allNetworks);
            lightNetworkList.appendTag(dimCompound);
        }
        compound.setTag(TAG_LIGHT_NETWORK, lightNetworkList);
    }

}
