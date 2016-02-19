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

import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class InitOreDictionary{

    public static void preInit(){
        register(InitItems.itemLightaniumIngot, LibNames.ORE_LIGHTANIUM_INGOT);
        register(InitItems.itemLightaniumNugget, LibNames.ORE_LIGHTANIUM_NUGGET);
        register(InitItems.itemLightaniumDust, LibNames.ORE_LIGHTANIUM_DUST);
    }

    private static void register(Item item, String name){
        register(new ItemStack(item), name);
    }

    private static void register(ItemStack stack, String name){
        OreDictionary.registerOre(name, stack);
    }
}
