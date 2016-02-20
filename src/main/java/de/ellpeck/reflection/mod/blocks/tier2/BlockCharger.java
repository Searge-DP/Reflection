/*
 * This file ("BlockCharger.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks.tier2;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.mod.blocks.BlockLightComponentBase;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import de.ellpeck.reflection.mod.tile.tier2.TileCharger;

public class BlockCharger extends BlockLightComponentBase{

    public BlockCharger(){
        this(LibNames.BLOCK_CHARGER, ReflectionAPI.TIER_2, true, TileCharger.class, LibNames.TILE_CHARGER);
    }

    public BlockCharger(String name, LightNetworkTier tier, boolean addTab, Class<? extends TileLightComponentBase> tileClass, String tileName){
        super(name, tier, addTab, tileClass, tileName);

        float f = 1F/16F;
        this.setBlockBounds(f, 0F, f, 1F-f, 1F-5*f, 1F-f);
    }
}
