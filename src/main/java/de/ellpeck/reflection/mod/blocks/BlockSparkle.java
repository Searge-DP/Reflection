/*
 * This file ("BlockSparkle.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.block.material.Material;

public class BlockSparkle extends BlockBase{

    public BlockSparkle(){
        super(Material.circuits, LibNames.BLOCK_SPARKLE, true);
    }
}
