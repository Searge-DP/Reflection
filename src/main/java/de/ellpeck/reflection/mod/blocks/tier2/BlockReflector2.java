/*
 * This file ("BlockReflector2.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.tile.tier2.TileReflector2;
import net.minecraft.block.material.Material;

public class BlockReflector2 extends BlockLightComponentBase{

    public BlockReflector2(){
        super(Material.anvil, LibNames.BLOCK_REFLECTOR_2, ReflectionAPI.TIER_2, true, TileReflector2.class, LibNames.TILE_REFLECTOR_2);

        float f = 1F/16F;
        this.setBlockBounds(3*f, 0F, 3*f, 1F-3*f, 1F-3*f, 1F-3*f);
    }
}
