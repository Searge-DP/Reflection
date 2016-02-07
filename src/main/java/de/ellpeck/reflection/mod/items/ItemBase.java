package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.util.RegistryUtil;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemBase extends Item{

    public ItemBase(String name, boolean addTab){
        RegistryUtil.registerItem(this, name, addTab);
    }

}
