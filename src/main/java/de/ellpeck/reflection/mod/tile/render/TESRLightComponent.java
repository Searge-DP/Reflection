package de.ellpeck.reflection.mod.tile.render;

import de.ellpeck.reflection.mod.misc.LightNetworkTier;
import de.ellpeck.reflection.mod.network.LightNetwork;
import de.ellpeck.reflection.mod.tile.TileLightComponent;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class TESRLightComponent extends TileEntitySpecialRenderer<TileLightComponent>{

    private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

    @Override
    public void renderTileEntityAt(TileLightComponent te, double x, double y, double z, float partialTicks, int destroyStage){
        Set<LightNetwork.ConnectionPair> connections = LightNetwork.instance.getConnectionsForComponent(te.getPos(), te.getWorld().provider.getDimensionId());
        if(connections != null){
            Tessellator tessy = Tessellator.getInstance();
            WorldRenderer renderer = tessy.getWorldRenderer();

            this.bindTexture(beaconBeam);

            for(LightNetwork.ConnectionPair pair : connections){
                if(te.getPos().equals(pair.first)){
                    LightNetworkTier theTier = te.getTier();
                    float[] colors = theTier.getColors();
                    float r = colors[0];
                    float g = colors[1];
                    float b = colors[2];
                }
            }
        }
    }
}
