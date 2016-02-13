/*
 * This file ("BlockMirrorBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BlockReflector extends BlockContainerBase{

    public BlockReflector(Material material, String name, boolean addTab, Class<? extends TileEntity> tileClass, String tileName){
        super(material, name, addTab, tileClass, tileName);
        this.setLightOpacity(1);
    }

}
