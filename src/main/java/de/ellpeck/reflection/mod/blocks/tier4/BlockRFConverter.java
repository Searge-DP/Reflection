/*
 * This file ("BlockRFConverter.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier4;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.tier4.TileRFConverter;

public class BlockRFConverter extends BlockLightComponentBase{

    public BlockRFConverter(){
        super(LibNames.BLOCK_RF_CONVERTER, ReflectionAPI.TIER_4, true, TileRFConverter.class, LibNames.TILE_RF_CONVERTER, true);
    }
}
