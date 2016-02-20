/*
 * This file ("InitCrafting.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.crafting;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.items.InitItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class InitCrafting{

    public static void init(){
        //Materials
        addShapeless(new ItemStack(InitItems.itemLightaniumIngot, 9), new ItemStack(InitBlocks.blockLightanium));
        addShaped(new ItemStack(InitBlocks.blockLightanium), "XXX", "XXX", "XXX", 'X', new ItemStack(InitItems.itemLightaniumIngot));

        addShapeless(new ItemStack(InitItems.itemLightaniumNugget, 9), new ItemStack(InitItems.itemLightaniumIngot));
        addShaped(new ItemStack(InitItems.itemLightaniumIngot), "XXX", "XXX", "XXX", 'X', new ItemStack(InitItems.itemLightaniumNugget));

        addFurnace(new ItemStack(InitItems.itemLightaniumIngot), new ItemStack(InitBlocks.blockOreLightanium), 1.0F);

        //Tools
        addTools(InitItems.itemLightPickaxe, InitItems.itemLightAxe, InitItems.itemLightShovel, InitItems.itemLightSword, new ItemStack(Items.iron_ingot), new ItemStack(InitItems.itemLightaniumIngot));
        addShaped(new ItemStack(InitItems.itemLightConnector), "  M", " H ", "H  ", 'H', new ItemStack(Items.iron_ingot), 'M', new ItemStack(InitItems.itemLightaniumIngot));

        addShaped(new ItemStack(InitItems.itemLightBatteryBase), "GLG", "LLL", "GLG", 'G', new ItemStack(Blocks.glass), 'L', new ItemStack(InitItems.itemLightaniumIngot));
        addShaped(new ItemStack(InitItems.itemLightBatteryAdvanced), "LGL", "GBG", "LGL", 'G', new ItemStack(Blocks.gold_block), 'L', new ItemStack(InitBlocks.blockLightanium), 'B', new ItemStack(InitItems.itemLightBatteryBase));

        //Reflectors
        addShaped(new ItemStack(InitBlocks.blockReflector1), " G ", "GLG", "SSS", 'G', new ItemStack(Blocks.glass), 'L', new ItemStack(InitItems.itemLightaniumIngot), 'S', new ItemStack(Blocks.stone_slab));
        addShapeless(new ItemStack(InitBlocks.blockReflector2), new ItemStack(InitBlocks.blockReflector1), new ItemStack(Blocks.gold_block));

        //Converters
        addShapeless(new ItemStack(InitBlocks.blockConverter12), new ItemStack(InitBlocks.blockReflector1), new ItemStack(InitBlocks.blockReflector2));
        addShapeless(new ItemStack(InitBlocks.blockConverter23), new ItemStack(InitBlocks.blockReflector2), new ItemStack(Blocks.gold_block));

        //Machines
        addShaped(new ItemStack(InitBlocks.blockCoallector), "NNN", "NRN", "LLL", 'N', new ItemStack(InitItems.itemLightaniumNugget), 'R', new ItemStack(InitBlocks.blockReflector1), 'L', new ItemStack(InitItems.itemLightaniumIngot));
        addShapeless(new ItemStack(InitBlocks.blockConnectionTunnel1), new ItemStack(InitBlocks.blockReflector1), new ItemStack(InitBlocks.blockReflector1), new ItemStack(InitItems.itemLightaniumIngot));
        addShaped(new ItemStack(InitBlocks.blockCharger), "NBN", "NRN", "LLL", 'B', new ItemStack(InitItems.itemLightBatteryBase), 'R', new ItemStack(InitBlocks.blockReflector1), 'N', new ItemStack(InitItems.itemLightaniumNugget), 'L', new ItemStack(InitItems.itemLightaniumIngot));
        addShaped(new ItemStack(InitBlocks.blockAdvancedCharger), "NBN", "NRN", "LLL", 'B', new ItemStack(InitItems.itemLightBatteryAdvanced), 'R', new ItemStack(InitBlocks.blockReflector1), 'N', new ItemStack(InitItems.itemLightaniumNugget), 'L', new ItemStack(InitItems.itemLightaniumIngot));
    }

    private static void addTools(Item pick, Item axe, Item shovel, Item sword, ItemStack handle, ItemStack material){
        addShaped(new ItemStack(pick), "MMM", " H ", " H ", 'M', material, 'H', handle);
        addShaped(new ItemStack(axe), "MM ", "MH ", " H ", 'M', material, 'H', handle);
        addShaped(new ItemStack(shovel), "M", "H", "H", 'M', material, 'H', handle);
        addShaped(new ItemStack(sword), "M", "M", "H", 'M', material, 'H', handle);
    }

    private static void addShaped(ItemStack result, Object... inputs){
        GameRegistry.addRecipe(new ShapedOreRecipe(result, inputs));
    }

    private static void addShapeless(ItemStack result, Object... inputs){
        GameRegistry.addRecipe(new ShapelessOreRecipe(result, inputs));
    }

    private static void addFurnace(ItemStack result, ItemStack input, float xp){
        FurnaceRecipes.instance().addSmeltingRecipe(input, result, xp);
    }

}
