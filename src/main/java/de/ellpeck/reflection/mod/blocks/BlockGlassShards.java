/*
 * This file ("BlockGlassShards.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.TileGlassShards;
import net.minecraft.block.material.Material;

import java.util.Random;

public class BlockGlassShards extends BlockContainerBase{

    public BlockGlassShards(){
        super(Material.circuits, LibNames.BLOCK_GLASS_SHARDS, true, TileGlassShards.class, LibNames.TILE_GLASS_SHARDS);

        this.setBlockBounds(0F, 0F, 0F, 1F, 1F/16F, 1F);
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean isFullCube(){
        return false;
    }

    @Override
    public int quantityDropped(Random random){
        return 0;
    }
}
