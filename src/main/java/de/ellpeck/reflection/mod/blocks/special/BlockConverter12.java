/*
 * This file ("BlockConverter12.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.special;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import net.minecraft.block.material.Material;

public class BlockConverter12 extends BlockLightComponentBase{

    public BlockConverter12(Material material, String name, boolean addTab, Class<? extends TileLightComponentBase> tileClass, String tileName){
        super(material, name, ReflectionAPI.TIER_SPECIAL, addTab, tileClass, tileName);
    }
}
