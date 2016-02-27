/*
 * This file ("BlockReflector4.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.tile.tier4.TileReflector4;

public class BlockReflector4 extends BlockLightComponentBase{

    public BlockReflector4(){
        super(LibNames.BLOCK_REFLECTOR_4, ReflectionAPI.TIER_4, true, TileReflector4.class, LibNames.TILE_REFLECTOR_4, true);

        float f = 1F/16F;
        this.setBlockBounds(3*f, 0F, 3*f, 1F-3*f, 1F-3*f, 1F-3*f);
    }
}
