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
import de.ellpeck.reflection.mod.tile.TileCoallector;
import de.ellpeck.reflection.mod.tile.TileReflectorBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    //Tier 1
    public static Block blockBasicReflector;
    public static Block blockCoallector;

    public static void preInit(){
        blockBasicReflector = new BlockReflector(Material.anvil, LibNames.BLOCK_REFLECTOR_BASE_NAME, true, TileReflectorBase.class, LibNames.TILE_REFLECTOR_BASE_NAME);
        blockCoallector = new BlockCoallector(Material.anvil, LibNames.BLOCK_COALLECTOR_BASE_NAME, true, TileCoallector.class, LibNames.TILE_COALLECTOR_BASE_NAME);
    }

}
