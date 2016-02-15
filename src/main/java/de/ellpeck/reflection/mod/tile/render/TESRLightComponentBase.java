/*
 * This file ("TESRReflectorBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.render;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.TileLightComponent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRLightComponentBase extends TileEntitySpecialRenderer<TileLightComponent>{

    @Override
    public void renderTileEntityAt(TileLightComponent te, double x, double y, double z, float partialTicks, int destroyStage){
        ReflectionAPI.theMethodHandler.renderLightForConnections(te, x, y, z);
    }
}
