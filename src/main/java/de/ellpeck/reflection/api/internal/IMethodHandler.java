/*
 * This file ("IMethodHandler.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.internal;

import de.ellpeck.reflection.api.light.ILightComponent;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This is the method handler for accessing methods from the mod
 * from within the API.
 * Use it from ReflectionAPI.
 * <p>
 * Do not implement this directly.
 */
public interface IMethodHandler{

    /**
     * Writes info about a connection to NBT.
     * Used by TileLightComponent.
     */
    void writeConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound);

    /**
     * Reads info about a connection from NBT.
     * Used by TileLightComponent.
     */
    void readConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound);

    /**
     * Renders all of the lightbeams for the connections of this component
     * Can be called in the TileEntitySpecialRenderer of a component
     * <p>
     * No additional tests have to be done as only the first part of a
     * ConnectionPair will be rendered by default, meaning no connection
     * will be rendered twice
     */
    @SideOnly(Side.CLIENT)
    void renderLightForConnections(ILightComponent component, double x, double y, double z);

    /**
     * Gets the light network handler
     * (Connections get added when joining a world or while in a world,
     * old connections are cleared when loading/joining a different world)
     */
    ILightNetworkHandler getLightNetworkHandler();

    /**
     * Inserts a given amount of light into any light containers the player has in their inventory
     * @return The amount that was added
     */
    int insertLightIntoInventory(InventoryPlayer inventory, int amount, boolean actuallyDo);

    /**
     * Extracts a given amount of light from any light containers the player has in their inventory
     * @return The amount that was extracted
     */
    int extractLightFromInventory(InventoryPlayer inventory, int amount, boolean actuallyDo);
}
