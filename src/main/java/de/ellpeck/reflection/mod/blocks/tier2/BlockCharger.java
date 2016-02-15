/*
 * This file ("BlockCharger.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier2;

import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BlockCharger extends BlockLightComponentBase{

    public BlockCharger(Material material, String name, boolean addTab, Class<? extends TileEntity> tileClass, String tileName){
        super(material, name, addTab, tileClass, tileName);
    }

}
