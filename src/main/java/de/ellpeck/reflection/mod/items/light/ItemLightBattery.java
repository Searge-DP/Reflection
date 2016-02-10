/*
 * This file ("ItemLightBattery.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items.light;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLightBattery extends ItemLightStorageBase{

    public ItemLightBattery(String name, boolean addTab, int maxLight){
        super(name, addTab, maxLight);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean displayTopBar(ItemStack stack){
        return true;
    }
}
