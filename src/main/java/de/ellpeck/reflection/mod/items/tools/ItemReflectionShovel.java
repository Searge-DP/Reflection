/*
 * This file ("ItemReflectionShovel.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items.tools;

import de.ellpeck.reflection.mod.Reflection;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.util.ItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemReflectionShovel extends ItemSpade{

    public ItemReflectionShovel(String name, boolean addTab, Item.ToolMaterial material){
        super(material);
        ItemUtil.registerItem(this, name, addTab);

        this.registerRendering(name);
    }

    public void registerRendering(String name){
        Reflection.proxy.addRenderRegister(new ItemStack(this), new ResourceLocation(LibMod.MOD_ID, name));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
        return Items.iron_hoe.onItemUse(stack, player, world, pos, side, hitX, hitY, hitZ);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        ItemUtil.rechargeItemFromLight(stack, entity);
    }

}
