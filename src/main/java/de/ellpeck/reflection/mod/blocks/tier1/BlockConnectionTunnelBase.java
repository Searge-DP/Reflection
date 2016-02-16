/*
 * This file ("BlockConnectionTunnelBase.java") is part of the Reflection Mod for Minecraft.
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
import de.ellpeck.reflection.mod.tile.tier1.TileConnectionTunnelBase;
import net.minecraft.block.material.Material;

public class BlockConnectionTunnelBase extends BlockLightComponentBase{

    public BlockConnectionTunnelBase(){
        super(Material.anvil, LibNames.BLOCK_CONNECTION_TUNNEL_BASE_NAME, ReflectionAPI.TIER_1, true, TileConnectionTunnelBase.class, LibNames.TILE_CONNECTION_TUNNEL_BASE_NAME);
    }
}
