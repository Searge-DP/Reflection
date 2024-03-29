/*
 * This file ("BlockFireCollector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier2;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.tier2.TileFireGen;

public class BlockFireGen extends BlockLightComponentBase{

    public BlockFireGen(){
        super(LibNames.BLOCK_FIRE_GEN, ReflectionAPI.TIER_2, true, TileFireGen.class, LibNames.TILE_FIRE_GEN, true);
    }

}
