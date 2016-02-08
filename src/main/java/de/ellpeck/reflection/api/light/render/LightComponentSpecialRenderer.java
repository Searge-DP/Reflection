/*
 * This file ("LightComponentSpecialRenderer.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.api.light.render;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.api.light.TileLightComponent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

/**
 * A TileLightComponent's TESR.
 * This renders the light beams between two connections in a network.
 * Be sure to use ClientRegistry.bindTileEntitySpecialRenderer for every component
 * that should render the light beam.
 * If you want a tile to have an extra TESR, then override this class and call
 * super in renderTileEntityAt().
 */
public class LightComponentSpecialRenderer extends TileEntitySpecialRenderer<TileEntity>{

    private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage){
        if(te instanceof ILightComponent){
            Set<IConnectionPair> connections = ReflectionAPI.theLightNetworkHandler.getConnectionsForComponent(te.getPos(), te.getWorld().provider.getDimensionId());
            if(connections != null){
                this.bindTexture(beaconBeam);
                Tessellator tessy = Tessellator.getInstance();
                WorldRenderer renderer = tessy.getWorldRenderer();

                float[] colors = ((ILightComponent)te).getTier().getColors();
                float r = colors[0];
                float g = colors[1];
                float b = colors[2];

                for(IConnectionPair pair : connections){
                    if(te.getPos().equals(pair.getFirst())){

                        double secondX = pair.getSecond().getX()-x;
                        double secondY = pair.getSecond().getY()-y;
                        double secondZ = pair.getSecond().getZ()-z;

                        //TODO Fix this because I don't get it whatsoever
                        GlStateManager.translate(x, y, z);
                        renderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                        renderer.pos(0, 0, 0).color(r, g, b, 0.25F).endVertex();
                        renderer.pos(0.5, 0, 0).color(r, g, b, 0.25F).endVertex();
                        renderer.pos(secondX+0.5, secondY, secondZ).color(r, g, b, 0.25F).endVertex();
                        renderer.pos(secondX, secondY, secondZ).color(r, g, b, 0.25F).endVertex();

                        tessy.draw();
                    }
                }
            }
        }
    }
}
