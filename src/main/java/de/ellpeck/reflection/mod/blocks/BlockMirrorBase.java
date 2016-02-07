package de.ellpeck.reflection.mod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BlockMirrorBase extends BlockContainerBase{

    public BlockMirrorBase(Material material, String name, boolean addTab, Class<? extends TileEntity> tileClass, String tileName){
        super(material, name, addTab, tileClass, tileName);
    }

}
