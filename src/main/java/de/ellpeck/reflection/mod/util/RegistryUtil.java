package de.ellpeck.reflection.mod.util;

import de.ellpeck.reflection.mod.creative.CreativeTab;
import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryUtil{

    public static void registerBlock(Block block, String name, boolean addTab){
        block.setUnlocalizedName(LibMod.MOD_ID+"."+name);

        block.setRegistryName(LibMod.MOD_ID, name);
        GameRegistry.registerBlock(block);

        block.setCreativeTab(addTab ? CreativeTab.instance : null);
    }

    public static void registerItem(Item item, String name, boolean addTab){
        item.setUnlocalizedName(LibMod.MOD_ID+"."+name);

        item.setRegistryName(LibMod.MOD_ID, name);
        GameRegistry.registerItem(item);

        item.setCreativeTab(addTab ? CreativeTab.instance : null);
    }

}
