/*
 * This file ("InitItems.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.items.light.ItemLightBattery;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionAxe;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionPickaxe;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionShovel;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionSword;
import de.ellpeck.reflection.mod.lib.LibMaterials;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.item.Item;

public class InitItems{

    public static Item itemLightConnector;
    public static Item itemLightBatteryBase;

    public static Item itemLightAxe;
    public static Item itemLightPickaxe;
    public static Item itemLightShovel;
    public static Item itemLightSword;

    public static Item itemGlassShards;

    public static void preInit(){
        itemLightConnector = new ItemLightConnector();
        itemLightBatteryBase = new ItemLightBattery();

        itemLightAxe = new ItemReflectionAxe(LibNames.ITEM_LIGHT_AXE, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightPickaxe = new ItemReflectionPickaxe(LibNames.ITEM_LIGHT_PICKAXE, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightShovel = new ItemReflectionShovel(LibNames.ITEM_LIGHT_SHOVEL, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightSword = new ItemReflectionSword(LibNames.ITEM_LIGHT_SWORD, true, LibMaterials.TOOL_MATERIAL_LIGHT);

        itemGlassShards = new ItemBase(LibNames.ITEM_GLASS_SHARDS, true);
    }

}
