/*
 * This file ("CreativeTab.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.creative;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.items.InitItems;
import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CreativeTab extends CreativeTabs{

    public static CreativeTab instance = new CreativeTab();
    private List<ItemStack> list;

    public CreativeTab(){
        super(LibMod.MOD_ID);
    }

    @Override
    public Item getTabIconItem(){
        return Item.getItemFromBlock(InitBlocks.blockBasicReflector);
    }

    @Override
    public void displayAllReleventItems(List<ItemStack> list){
        this.list = list;

        //Special
        this.addBlock(InitBlocks.blockConverter12);

        //Tier 1
        this.addBlock(InitBlocks.blockBasicReflector);
        this.addBlock(InitBlocks.blockCoallector);

        //Items
        this.addItem(InitItems.itemLightConnector);
        this.addItem(InitItems.itemLightBatteryBase);
    }

    private void addBlock(Block block){
        block.getSubBlocks(Item.getItemFromBlock(block), this, this.list);
    }

    private void addItem(Item item){
        item.getSubItems(item, this, this.list);
    }
}
