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
import de.ellpeck.reflection.mod.blocks.special.BlockConverter23;
import de.ellpeck.reflection.mod.blocks.special.BlockConverter34;
import de.ellpeck.reflection.mod.blocks.tier1.BlockCoallector;
import de.ellpeck.reflection.mod.blocks.tier1.BlockConnectionTunnel1;
import de.ellpeck.reflection.mod.blocks.tier1.BlockReflector1;
import de.ellpeck.reflection.mod.blocks.tier2.BlockCharger;
import de.ellpeck.reflection.mod.blocks.tier2.BlockFireGen;
import de.ellpeck.reflection.mod.blocks.tier2.BlockPassiveMobGen;
import de.ellpeck.reflection.mod.blocks.tier2.BlockReflector2;
import de.ellpeck.reflection.mod.blocks.tier3.BlockAdvancedCharger;
import de.ellpeck.reflection.mod.blocks.tier3.BlockReflector3;
import de.ellpeck.reflection.mod.blocks.tier4.BlockRFConverter;
import de.ellpeck.reflection.mod.blocks.tier4.BlockReflector4;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import de.ellpeck.reflection.mod.tile.TileGlassShards;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    //Special
    public static Block blockConverter12;
    public static Block blockConverter23;
    public static Block blockConverter34;

    //Tier 1
    public static Block blockReflector1;
    public static Block blockCoallector;
    public static Block blockConnectionTunnel1;

    //Tier 2
    public static Block blockCharger;
    public static Block blockReflector2;
    public static Block blockPassiveMobGen;
    public static Block blockFireGen;

    //Tier 3
    public static Block blockAdvancedCharger;
    public static Block blockReflector3;

    //Tier 4
    public static Block blockRFConverter;
    public static Block blockReflector4;

    public static Block blockSparkle;
    public static Block blockGlassShards;

    public static Block blockLightanium;
    public static Block blockOreLightanium;

    public static Block blockDarkness;
    public static Block blockOreDarkness;

    public static void preInit(){
        int blockSize = Block.blockRegistry.getKeys().size();

        //Special
        blockConverter12 = new BlockConverter12();
        blockConverter23 = new BlockConverter23();
        blockConverter34 = new BlockConverter34();

        //Tier 1
        blockReflector1 = new BlockReflector1();
        blockCoallector = new BlockCoallector();
        blockConnectionTunnel1 = new BlockConnectionTunnel1();

        //Tier 2
        blockCharger = new BlockCharger();
        blockReflector2 = new BlockReflector2();
        blockPassiveMobGen = new BlockPassiveMobGen();
        blockFireGen = new BlockFireGen();

        //Tier 3
        blockAdvancedCharger = new BlockAdvancedCharger();
        blockReflector3 = new BlockReflector3();

        //Tier 4
        blockRFConverter = new BlockRFConverter();
        blockReflector4 = new BlockReflector4();

        blockSparkle = new BlockSparkle();
        blockGlassShards = new BlockGlassShards();

        blockLightanium = new BlockBase(Material.iron, LibNames.BLOCK_LIGHTANIUM, true, 3.0F, Block.soundTypeMetal, LibNames.HARVEST_TOOL_PICKAXE, 1);
        blockOreLightanium = new BlockBase(Material.rock, LibNames.BLOCK_ORE_LIGHTANIUM, true, 3.0F, BlockSparkle.soundTypeStone, LibNames.HARVEST_TOOL_PICKAXE, 1);

        blockDarkness = new BlockBase(Material.iron, LibNames.BLOCK_DARKNESS, true, 4.0F, Block.soundTypeMetal, LibNames.HARVEST_TOOL_PICKAXE, 2);
        blockOreDarkness = new BlockBase(Material.iron, LibNames.BLOCK_ORE_DARKNESS, true, 4.0F, Block.soundTypeStone, LibNames.HARVEST_TOOL_PICKAXE, 2);

        LibMod.LOGGER.info(String.format("%s has registered %s Blocks!", LibMod.MOD_NAME, Block.blockRegistry.getKeys().size()-blockSize));
    }

    public static void postInit(){
        TileGlassShards.postInit();
    }
}
