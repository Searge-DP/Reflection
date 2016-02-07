package de.ellpeck.reflection.mod.creative;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CreativeTab extends CreativeTabs{

    private List<ItemStack> list;

    public static CreativeTab instance = new CreativeTab();

    public CreativeTab(){
        super(LibMod.MOD_ID);
    }

    @Override
    public Item getTabIconItem(){
        return Item.getItemFromBlock(InitBlocks.blockMirror);
    }

    @Override
    public void displayAllReleventItems(List<ItemStack> list){
        this.list = list;

        this.addBlock(InitBlocks.blockMirror);
    }

    private void addBlock(Block block){
        this.addItemStack(new ItemStack(block));
    }

    private void addItem(Item item){
        this.addItemStack(new ItemStack(item));
    }

    private void addItemStack(ItemStack stack){
        this.list.add(stack);
    }
}
