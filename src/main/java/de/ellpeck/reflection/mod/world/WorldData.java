package de.ellpeck.reflection.mod.world;

import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class WorldData extends WorldSavedData{

    public static final String DATA_TAG = LibMod.MOD_NAME+"WorldData";
    private static WorldData instance;

    public WorldData(String name){
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound){

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

                WorldData savedData = (WorldData)world.loadItemData(WorldData.class, WorldData.DATA_TAG);

                if(savedData == null){
                    LibMod.LOGGER.info("No WorldData found, creating...");

                    savedData = new WorldData(WorldData.DATA_TAG);
                    world.setItemData(WorldData.DATA_TAG, savedData);
                }
                else{
                    LibMod.LOGGER.info("WorldData successfully received!");
                }

                WorldData.instance = savedData;
            }
        }
    }

    public static void clearOldData(){

    }

}
