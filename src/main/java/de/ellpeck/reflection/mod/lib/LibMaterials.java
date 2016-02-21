/*
 * This file ("LibMaterials.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class LibMaterials{

    public static final Item.ToolMaterial TOOL_MATERIAL_LIGHT = EnumHelper.addToolMaterial(LibMod.MOD_ID.toUpperCase()+"LIGHT", 2, 280, 6.5F, 2.5F, 14);
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LIGHT = EnumHelper.addArmorMaterial(LibMod.MOD_ID.toUpperCase()+"LIGHT", LibNames.BASE_REGISTRY+"armorLight", 17, new int[]{3, 7, 6, 2}, 10);

}
