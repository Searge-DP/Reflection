/*
 * This file ("InitOreDictionary.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.misc;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class InitOreDictionary{

    public static void preInit(){
        int oredictSize = OreDictionary.getOreNames().length;

        register(InitItems.itemLightaniumIngot, LibNames.ORE_LIGHTANIUM_INGOT);
        register(InitItems.itemLightaniumNugget, LibNames.ORE_LIGHTANIUM_NUGGET);
        register(InitItems.itemLightaniumDust, LibNames.ORE_LIGHTANIUM_DUST);
        register(InitBlocks.blockOreLightanium, LibNames.ORE_LIGHTANIUM_ORE);
        register(InitBlocks.blockLightanium, LibNames.ORE_LIGHTANIUM_BLOCK);

        LibMod.LOGGER.info(String.format("%s has registered %s new OreDictionary entries!", LibMod.MOD_NAME, OreDictionary.getOreNames().length-oredictSize));
    }

    private static void register(Item item, String name){
        register(new ItemStack(item), name);
    }

    private static void register(Block block, String name){
        register(new ItemStack(block), name);
    }

    private static void register(ItemStack stack, String name){
        OreDictionary.registerOre(name, stack);
    }
}
