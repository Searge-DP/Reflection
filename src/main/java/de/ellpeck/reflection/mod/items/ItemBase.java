/*
 * This file ("ItemBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.util.RegistryUtil;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemBase extends Item{

    public ItemBase(String name, boolean addTab){
        RegistryUtil.registerItem(this, name, addTab);
    }

}
