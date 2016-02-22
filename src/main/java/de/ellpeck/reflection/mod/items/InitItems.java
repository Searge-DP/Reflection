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

import de.ellpeck.reflection.mod.items.armor.ItemReflectionArmor;
import de.ellpeck.reflection.mod.items.light.ItemLightBattery;
import de.ellpeck.reflection.mod.items.light.ItemLightBatteryAdvanced;
import de.ellpeck.reflection.mod.items.tools.*;
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
    public static Item itemLightShears;

    public static Item itemLightHelmet;
    public static Item itemLightChestplate;
    public static Item itemLightPants;
    public static Item itemLightBoots;

    public static Item itemMochiWhite;
    public static Item itemMochiGreen;
    public static Item itemMochiRed;

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
        itemLightShears = new ItemReflectionShears(LibNames.ITEM_LIGHT_SHEARS, true, 300);

        itemLightHelmet = new ItemReflectionArmor(LibNames.ITEM_LIGHT_HELMET, true, LibMaterials.ARMOR_MATERIAL_LIGHT, 0);
        itemLightChestplate = new ItemReflectionArmor(LibNames.ITEM_LIGHT_CHESTPLATE, true, LibMaterials.ARMOR_MATERIAL_LIGHT, 1);
        itemLightPants = new ItemReflectionArmor(LibNames.ITEM_LIGHT_PANTS, true, LibMaterials.ARMOR_MATERIAL_LIGHT, 2);
        itemLightBoots = new ItemReflectionArmor(LibNames.ITEM_LIGHT_BOOTS, true, LibMaterials.ARMOR_MATERIAL_LIGHT, 3);

        itemMochiWhite = new ItemFoodBase(LibNames.ITEM_MOCHI_WHITE, true, 12, 1F, false).setPotionEffect(1, 180, 1, 1.0F).setAlwaysEdible();
        itemMochiGreen = new ItemFoodBase(LibNames.ITEM_MOCHI_GREEN, true, 12, 1F, false).setPotionEffect(14, 180, 0, 1.0F).setAlwaysEdible();
        itemMochiRed = new ItemFoodBase(LibNames.ITEM_MOCHI_RED, true, 12, 1F, false).setPotionEffect(21, 300, 1, 1.0F).setAlwaysEdible();

        LibMod.LOGGER.info(String.format("%s has registered %s Items!", LibMod.MOD_NAME, Item.itemRegistry.getKeys().size()-itemSize));
    }

}
