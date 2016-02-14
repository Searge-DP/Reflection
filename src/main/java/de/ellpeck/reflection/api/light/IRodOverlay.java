/*
 * This file ("IRodOverlay.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Implement this on TileEntities or Blocks which should display
 * something when looked at while holding a Rod of the Lights.
 */
public interface IRodOverlay{

    /**
     * Displays the overlay
     */
    @SideOnly(Side.CLIENT)
    void displayOverlay(ItemStack heldStack, ScaledResolution resolution, BlockPos hitPos, World world);

}
