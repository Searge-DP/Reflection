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
import de.ellpeck.reflection.mod.blocks.tier3.BlockAdvancedCharger;
import net.minecraft.block.Block;

public class InitBlocks{

    //Special
    public static Block blockConverter12;
    public static Block blockConnectionTunnelBase;

    //Tier 1
    public static Block blockBasicReflector;
    public static Block blockCoallector;

    //Tier 2
    public static Block blockCharger;

    //Tier 3
    public static Block blockAdvancedCharger;

    public static Block blockSparkle;

    public static void preInit(){
        //Special
        blockConverter12 = new BlockConverter12();
        blockConnectionTunnelBase = new BlockConnectionTunnelBase();

        //Tier 1
        blockBasicReflector = new BlockReflectorBase();
        blockCoallector = new BlockCoallector();

        //Tier 2
        blockCharger = new BlockCharger();

        //Tier 3
        blockAdvancedCharger = new BlockAdvancedCharger();

        blockSparkle = new BlockSparkle();
    }

}
