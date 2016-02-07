package de.ellpeck.reflection.mod.tile.render;

import de.ellpeck.reflection.mod.tile.TileLightComponent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TESRLightComponent extends TileEntitySpecialRenderer<TileLightComponent>{

    @Override
    public void renderTileEntityAt(TileLightComponent te, double x, double y, double z, float partialTicks, int destroyStage){
        System.out.println("PEnisscasd");
    }
}
