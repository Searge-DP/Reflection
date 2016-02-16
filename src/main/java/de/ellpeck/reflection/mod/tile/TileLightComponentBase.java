/*
 * This file ("TileLightComponentBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.ILightTierDisplay;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.api.light.TileLightComponent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class TileLightComponentBase extends TileLightComponent{

    @Override
    public LightNetworkTier getTier(){
        Block block = this.getBlockType();
        if(block instanceof ILightTierDisplay){
            return ((ILightTierDisplay)block).getTier();
        }
        else{
            return ReflectionAPI.TIER_SPECIAL;
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
        return !newSate.getBlock().isAssociatedBlock(oldState.getBlock());
    }
}
