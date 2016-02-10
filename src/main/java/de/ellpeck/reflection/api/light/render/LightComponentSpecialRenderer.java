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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

/**
 * A TileLightComponent's TESR.
 * This renders the light beams between two connections in a network.
 * Be sure to use ClientRegistry.bindTileEntitySpecialRenderer() for every component
 * that should render the light beam.
 * If you want a tile to have an extra TESR, then override this class and call
 * super in renderTileEntityAt().
 */
@SideOnly(Side.CLIENT)
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
                        //TODO Fix this because I don't get it whatsoever
                        //If anyone wants to do this: The connection should be rendered between
                        //pair.getFirst() and pair.getSecond(), everything below this could be deleted
                        //if it's nonsense. Which it probably is. Everything above this should stay and
                        //can be used. And yes, if you rename Tessy, I will be mad.
                        //So what this is basically supposed to do:
                        //Render a light beam between the two components. I don't care if they rotate or not,
                        //they just have to be shiny and render in the color that's specified above.
                        //Also please make it so that I know how to change the size (how big they are). Thanks.

                        double secondX = pair.getSecond().getX()-x;
                        double secondY = pair.getSecond().getY()-y;
                        double secondZ = pair.getSecond().getZ()-z;

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
