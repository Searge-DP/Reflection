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
import de.ellpeck.reflection.mod.blocks.tier1.BlockCoallector;
import de.ellpeck.reflection.mod.blocks.tier1.BlockConnectionTunnel1;
import de.ellpeck.reflection.mod.blocks.tier1.BlockReflector1;
import de.ellpeck.reflection.mod.blocks.tier2.BlockCharger;
import de.ellpeck.reflection.mod.blocks.tier2.BlockReflector2;
import de.ellpeck.reflection.mod.blocks.tier3.BlockAdvancedCharger;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.lib.LibNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks{

    //Special
    public static Block blockConverter12;
    public static Block blockConverter23;

    //Tier 1
    public static Block blockReflector1;
    public static Block blockCoallector;
    public static Block blockConnectionTunnel1;

    //Tier 2
    public static Block blockCharger;
    public static Block blockReflector2;

    //Tier 3
    public static Block blockAdvancedCharger;

    public static Block blockSparkle;
    public static Block blockLightanium;
    public static Block blockOreLightanium;
    public static Block blockGlassShards;

    public static void preInit(){
        int blockSize = Block.blockRegistry.getKeys().size();

        //Special
        blockConverter12 = new BlockConverter12();
        blockConverter23 = new BlockConverter23();

        //Tier 1
        blockReflector1 = new BlockReflector1();
        blockCoallector = new BlockCoallector();
        blockConnectionTunnel1 = new BlockConnectionTunnel1();

        //Tier 2
        blockCharger = new BlockCharger();
        blockReflector2 = new BlockReflector2();

        //Tier 3
        blockAdvancedCharger = new BlockAdvancedCharger();

        blockSparkle = new BlockSparkle();
        blockLightanium = new BlockBase(Material.iron, LibNames.BLOCK_LIGHTANIUM, true).setHardness(3.0F).setStepSound(Block.soundTypeStone);
        blockOreLightanium = new BlockBase(Material.rock, LibNames.BLOCK_ORE_LIGHTANIUM, true).setHardness(3.0F).setStepSound(Block.soundTypeStone);
        blockGlassShards = new BlockGlassShards();

        LibMod.LOGGER.info(String.format("%s has registered %s Blocks!", LibMod.MOD_NAME, Block.blockRegistry.getKeys().size()-blockSize));
    }

}
