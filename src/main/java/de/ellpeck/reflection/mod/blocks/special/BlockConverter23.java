/*
 * This file ("BlockConverter23.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.special.TileConverter23;

public class BlockConverter23 extends BlockLightComponentBase{

    public BlockConverter23(){
        super(LibNames.BLOCK_CONVERTER_2_3, ReflectionAPI.TIER_SPECIAL, true, TileConverter23.class, LibNames.TILE_CONVERTER_2_3, true);
    }
}
