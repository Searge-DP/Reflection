/*
 * This file ("BlockReflector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier1;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.tier1.TileReflectorBase;
import net.minecraft.block.material.Material;

public class BlockReflectorBase extends BlockLightComponentBase{

    public BlockReflectorBase(){
        super(Material.anvil, LibNames.BLOCK_REFLECTOR, ReflectionAPI.TIER_1, true, TileReflectorBase.class, LibNames.TILE_REFLECTOR);

        float f = 1F/16F;
        this.setBlockBounds(3*f, 0F, 3*f, 1F-3*f, 1F-3*f, 1F-3*f);
    }

}
