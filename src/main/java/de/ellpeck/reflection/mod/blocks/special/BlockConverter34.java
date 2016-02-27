/*
 * This file ("BlockConverter34.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.tile.special.TileConverter34;

public class BlockConverter34 extends BlockLightComponentBase{

    public BlockConverter34(){
        super(LibNames.BLOCK_CONVERTER_3_4, ReflectionAPI.TIER_SPECIAL, true, TileConverter34.class, LibNames.TILE_CONVERTER_3_4, true);
    }
}
