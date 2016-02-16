/*
 * This file ("BlockCoallector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier1;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import net.minecraft.block.material.Material;

public class BlockCoallector extends BlockLightComponentBase{

    public BlockCoallector(Material material, String name, boolean addTab, Class<? extends TileLightComponentBase> tileClass, String tileName){
        super(material, name, ReflectionAPI.TIER_1, addTab, tileClass, tileName);
    }
}
