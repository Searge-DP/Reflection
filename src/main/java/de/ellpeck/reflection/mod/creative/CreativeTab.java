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
        return InitItems.itemLightConnector;
    }

    @Override
    public void displayAllReleventItems(List<ItemStack> list){
        this.list = list;

        //Special
        this.addBlock(InitBlocks.blockConverter12);
        this.addBlock(InitBlocks.blockConverter23);

        //Tier 1
        this.addBlock(InitBlocks.blockReflector1);
        this.addBlock(InitBlocks.blockCoallector);
        this.addBlock(InitBlocks.blockConnectionTunnel1);

        //Tier 2
        this.addBlock(InitBlocks.blockCharger);
        this.addBlock(InitBlocks.blockReflector2);

        //Tier 3
        this.addBlock(InitBlocks.blockAdvancedCharger);

        this.addBlock(InitBlocks.blockLightanium);
        this.addBlock(InitBlocks.blockOreLightanium);
        this.addBlock(InitBlocks.blockGlassShards);
        this.addBlock(InitBlocks.blockSparkle);

        //Items
        this.addItem(InitItems.itemLightConnector);
        this.addItem(InitItems.itemLightBatteryBase);
        this.addItem(InitItems.itemLightBatteryAdvanced);

        this.addItem(InitItems.itemLightaniumIngot);
        this.addItem(InitItems.itemLightaniumDust);
        this.addItem(InitItems.itemLightaniumNugget);

        this.addItem(InitItems.itemLightAxe);
        this.addItem(InitItems.itemLightPickaxe);
        this.addItem(InitItems.itemLightShovel);
        this.addItem(InitItems.itemLightSword);
        this.addItem(InitItems.itemLightShears);

        this.addItem(InitItems.itemLightHelmet);
        this.addItem(InitItems.itemLightChestplate);
        this.addItem(InitItems.itemLightPants);
        this.addItem(InitItems.itemLightBoots);
    }

    private void addBlock(Block block){
        block.getSubBlocks(Item.getItemFromBlock(block), this, this.list);
    }

    private void addItem(Item item){
        item.getSubItems(item, this, this.list);
    }
}
