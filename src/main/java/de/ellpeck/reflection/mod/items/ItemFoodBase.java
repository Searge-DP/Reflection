/*
 * This file ("ItemFoodBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.Reflection;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.util.ItemUtil;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemFoodBase extends ItemFood{

    public ItemFoodBase(String name, boolean addTab, int healAmount, float saturation, boolean isWolfFood){
        super(healAmount, saturation, isWolfFood);

        ItemUtil.registerItem(this, name, addTab);

        this.registerRendering(name);
    }

    public void registerRendering(String name){
        Reflection.proxy.addToRenderRegistry(new ItemStack(this), new ResourceLocation(LibMod.MOD_ID, name));
    }
}
