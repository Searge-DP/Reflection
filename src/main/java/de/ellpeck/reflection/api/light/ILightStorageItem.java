/*
 * This file ("ILightStorageItem.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A light storage item
 * <p>
 * Implement this on any item which should be able to store light
 */
public interface ILightStorageItem{

    /**
     * @return The current amount of light this stack has
     */
    int getLight(ItemStack stack);

    /**
     * @return The maximum amount of light this stack can have
     */
    int getMaxLight(ItemStack stack);

    /**
     * Return true if this stack's light amount should contribute to the
     * bar rendered at the top of the screen
     */
    @SideOnly(Side.CLIENT)
    boolean displayTopBar(ItemStack stack);

    /**
     * Tries to extract a given amount of light
     *
     * @param actuallyDo When set to false, the extract will be simulated
     * @return If the extraction worked
     */
    boolean extractLight(ItemStack stack, int amount, boolean actuallyDo);

    /**
     * Tries to insert a given amount of light
     *
     * @param actuallyDo When set to false, the insert will be simulated
     * @return If the insertion worked
     */
    boolean insertLight(ItemStack stack, int amount, boolean actuallyDo);

    /**
     * Sets the amount of light in the stack to the specified amount.
     * There shouldn't be any checks done, this is merely for debug and initialization.
     */
    void setLight(ItemStack stack, int amount);
}
