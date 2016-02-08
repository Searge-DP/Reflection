/*
 * This file ("InitBlocks.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.TileMirrorBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    public static Block blockMirror;

    public static void preInit(){
        blockMirror = new BlockMirrorBase(Material.anvil, LibNames.BLOCK_MIRROR_NAME, true, TileMirrorBase.class, LibNames.TILE_MIRROR_NAME);
    }

}
