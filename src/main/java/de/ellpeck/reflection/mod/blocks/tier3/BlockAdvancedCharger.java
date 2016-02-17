/*
 * This file ("BlockAdvancedCharger.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier3;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.blocks.tier2.BlockCharger;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.tier3.TileAdvancedCharger;
import net.minecraft.block.material.Material;

public class BlockAdvancedCharger extends BlockCharger{

    public BlockAdvancedCharger(){
        super(Material.anvil, LibNames.BLOCK_CHARGER_ADVANCED, ReflectionAPI.TIER_3, true, TileAdvancedCharger.class, LibNames.TILE_CHARGER_ADVANCED);
    }
}
