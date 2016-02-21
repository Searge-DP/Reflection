/*
 * This file ("ItemReflectionArmor.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items.armor;

import de.ellpeck.reflection.mod.Reflection;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.util.ItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemReflectionArmor extends ItemArmor{

    public ItemReflectionArmor(String name, boolean addTab, ArmorMaterial material, int armorType){
        super(material, 0, armorType);

        ItemUtil.registerItem(this, name, addTab);

        this.registerRendering(name);
    }

    public void registerRendering(String name){
        Reflection.proxy.addToRenderRegistry(new ItemStack(this), new ResourceLocation(LibMod.MOD_ID, name));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        ItemUtil.rechargeItemFromLight(stack, entity);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
        ItemUtil.rechargeItemFromLight(stack, player);
    }
}
