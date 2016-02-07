package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.TileMirrorBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    public static Block blockMirror;

    public static void preInit(){
        blockMirror = new BlockMirrorBase(Material.anvil, LibNames.BLOCK_MIRROR_NAME, true, TileMirrorBase.class, LibNames.TILE_MIRROR_NAME);
    }

}
