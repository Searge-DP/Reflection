package de.ellpeck.reflection.mod.tile.render;

import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import de.ellpeck.reflection.mod.tile.TileLightComponent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class LightComponentSpecialRenderer extends TileEntitySpecialRenderer<TileLightComponent>{

    private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

    @Override
    public void renderTileEntityAt(TileLightComponent te, double x, double y, double z, float partialTicks, int destroyStage){
        Set<LightNetworkHandler.ConnectionPair> connections = LightNetworkHandler.instance.getConnectionsForComponent(te.getPos(), te.getWorld().provider.getDimensionId());
        if(connections != null){
            this.bindTexture(beaconBeam);
            Tessellator tessy = Tessellator.getInstance();
            WorldRenderer renderer = tessy.getWorldRenderer();

            float[] colors = te.getTier().getColors();
            float r = colors[0];
            float g = colors[1];
            float b = colors[2];

            for(LightNetworkHandler.ConnectionPair pair : connections){
                if(te.getPos().equals(pair.first)){

                    double secondX = pair.second.getX()-x;
                    double secondY = pair.second.getY()-y;
                    double secondZ = pair.second.getZ()-z;

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
