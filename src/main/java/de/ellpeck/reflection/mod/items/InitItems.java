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
import de.ellpeck.reflection.mod.items.light.ItemLightBatteryAdvanced;
import de.ellpeck.reflection.mod.items.tools.ItemHoeShovel;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionAxe;
import de.ellpeck.reflection.mod.items.tools.ItemReflectionSword;
import de.ellpeck.reflection.mod.items.tools.ItemSparklePickaxe;
import de.ellpeck.reflection.mod.lib.LibMaterials;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.item.Item;

public class InitItems{

    public static Item itemLightConnector;
    public static Item itemLightBatteryBase;
    public static Item itemLightBatteryAdvanced;

    public static Item itemLightaniumIngot;
    public static Item itemLightaniumNugget;
    public static Item itemLightaniumDust;

    public static Item itemLightAxe;
    public static Item itemLightPickaxe;
    public static Item itemLightShovel;
    public static Item itemLightSword;

    public static void preInit(){
        int itemSize = Item.itemRegistry.getKeys().size();

        itemLightConnector = new ItemLightConnector();
        itemLightBatteryBase = new ItemLightBattery();
        itemLightBatteryAdvanced = new ItemLightBatteryAdvanced();

        itemLightaniumIngot = new ItemBase(LibNames.ITEM_LIGHTANIUM_INGOT, true);
        itemLightaniumNugget = new ItemBase(LibNames.ITEM_LIGHTANIUM_NUGGET, true);
        itemLightaniumDust = new ItemBase(LibNames.ITEM_LIGHTANIUM_DUST, true);

        itemLightAxe = new ItemReflectionAxe(LibNames.ITEM_LIGHT_AXE, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightPickaxe = new ItemSparklePickaxe(LibNames.ITEM_LIGHT_PICKAXE, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightShovel = new ItemHoeShovel(LibNames.ITEM_LIGHT_SHOVEL, true, LibMaterials.TOOL_MATERIAL_LIGHT);
        itemLightSword = new ItemReflectionSword(LibNames.ITEM_LIGHT_SWORD, true, LibMaterials.TOOL_MATERIAL_LIGHT);

        LibMod.LOGGER.info(String.format("%s has registered %s Items!", LibMod.MOD_NAME, Item.itemRegistry.getKeys().size()-itemSize));
    }

}
