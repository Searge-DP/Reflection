package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block{

    public BlockBase(Material material, String name, boolean addTab){
        super(material);

        RegistryUtil.registerBlock(this, name, addTab);
    }

}
