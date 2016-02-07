package de.ellpeck.reflection.mod.util;

import de.ellpeck.reflection.mod.creative.CreativeTab;
import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryUtil{

    public static void registerBlock(Block block, String name, boolean addTab){
        block.setUnlocalizedName(LibMod.MOD_ID+"."+name);

        block.setRegistryName(LibMod.MOD_ID, name);
        GameRegistry.registerBlock(block);

        if(addTab){
            block.setCreativeTab(CreativeTab.instance);
        }
    }

}
