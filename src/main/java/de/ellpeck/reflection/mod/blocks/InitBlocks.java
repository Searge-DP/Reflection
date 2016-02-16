/*
 * This file ("InitBlocks.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.blocks.special.BlockConverter12;
import de.ellpeck.reflection.mod.blocks.tier1.BlockCoallector;
import de.ellpeck.reflection.mod.blocks.tier1.BlockConnectionTunnelBase;
import de.ellpeck.reflection.mod.blocks.tier1.BlockReflectorBase;
import de.ellpeck.reflection.mod.blocks.tier2.BlockCharger;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.special.TileConverter12;
import de.ellpeck.reflection.mod.tile.tier1.TileCoallector;
import de.ellpeck.reflection.mod.tile.tier1.TileConnectionTunnelBase;
import de.ellpeck.reflection.mod.tile.tier1.TileReflectorBase;
import de.ellpeck.reflection.mod.tile.tier2.TileCharger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    //Special
    public static Block blockConverter12;
    public static Block blockConnectionTunnelBase;

    //Tier 1
    public static Block blockBasicReflector;
    public static Block blockCoallector;

    //Tier 2
    public static Block blockCharger;

    public static void preInit(){
        //Special
        blockConverter12 = new BlockConverter12(Material.anvil, LibNames.BLOCK_CONVERTER12_BASE_NAME, true, TileConverter12.class, LibNames.TILE_CONVERTER12_BASE_NAME);
        blockConnectionTunnelBase = new BlockConnectionTunnelBase(Material.anvil, LibNames.BLOCK_CONNECTION_TUNNEL_BASE_NAME, true, TileConnectionTunnelBase.class, LibNames.TILE_CONNECTION_TUNNEL_BASE_NAME);

        //Tier 1
        blockBasicReflector = new BlockReflectorBase(Material.anvil, LibNames.BLOCK_REFLECTOR_BASE_NAME, true, TileReflectorBase.class, LibNames.TILE_REFLECTOR_BASE_NAME);
        blockCoallector = new BlockCoallector(Material.anvil, LibNames.BLOCK_COALLECTOR_BASE_NAME, true, TileCoallector.class, LibNames.TILE_COALLECTOR_BASE_NAME);

        //Tier 2
        blockCharger = new BlockCharger(Material.anvil, LibNames.BLOCK_CHARGER_BASE_NAME, true, TileCharger.class, LibNames.TILE_CHARGER_BASE_NAME);
    }

}
