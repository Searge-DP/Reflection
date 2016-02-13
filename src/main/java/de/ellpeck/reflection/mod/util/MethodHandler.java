/*
 * This file ("MethodHandler.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.util;

import com.sun.javafx.geom.Vec3d;
import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.internal.ILightNetworkHandler;
import de.ellpeck.reflection.api.internal.IMethodHandler;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Set;

public class MethodHandler implements IMethodHandler{

    private final ILightNetworkHandler lightNetworkHandler;

    public MethodHandler(){
        this.lightNetworkHandler = new LightNetworkHandler();
    }

    private static final String TAG_CONNECTIONS = "Connections";

    private static double BEAM_SIZE = 4D/16D;
    private static float BEAM_ALPHA = 0.5F;
    private static int BEAM_ROTATIONTIME = 3000;    // Time (in ms) for a full rotaion

    @Override
    public void writeConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound){
        NBTTagList list = new NBTTagList();

        Set<IConnectionPair> connections = WorldUtil.getAllConnectionsForTile(tile);
        if(connections != null && !connections.isEmpty()){
            for(IConnectionPair pair : connections){
                NBTTagCompound pairCompound = new NBTTagCompound();
                pair.writeToNBT(pairCompound);
                list.appendTag(pairCompound);
            }
        }

        compound.setTag(TAG_CONNECTIONS, list);
    }

    @Override
    public void readConnectionInfoNBT(ILightComponent tile, NBTTagCompound compound){
        if(compound.hasKey(TAG_CONNECTIONS)){
            NBTTagList list = compound.getTagList(TAG_CONNECTIONS, 10);
            if(list.hasNoTags()){
                ReflectionAPI.getLightNetworkHandler().removeConnections(tile.getPosition(), tile.getTheWorld());
            }
            else{
                Set<IConnectionPair> connections = WorldUtil.getAllConnectionsForTile(tile);
                for(int i = 0; i < list.tagCount(); i++){
                    LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                    if(!connections.contains(pair)){
                        ReflectionAPI.getLightNetworkHandler().addConnection(pair.first, pair.second, tile.getTheWorld(), false);
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderLightForConnections(ILightComponent component, double x, double y, double z){
        Set<IConnectionPair> connections = WorldUtil.getAllConnectionsForTile(component);
        if(connections != null){

            Tessellator tessy = Tessellator.getInstance();
            WorldRenderer render = tessy.getWorldRenderer();

            GlStateManager.color(1,1,1,1);

            float[] colors = component.getTier().getColors();
            float r = colors[0];
            float g = colors[1];
            float b = colors[2];

            Vec3d vpos1 = new Vec3d(component.getPosition().getX()+0.5, component.getPosition().getY()+0.5, component.getPosition().getZ()+0.5);

            float rot =  360F *((float)(System.currentTimeMillis() % BEAM_ROTATIONTIME) / (float) BEAM_ROTATIONTIME);

            for(IConnectionPair pair : connections){
                if(component.getPosition().equals(pair.getFirst())){
                    BlockPos pos2 = pair.getSecond();
                    Vec3d vpos2 = new Vec3d(pos2.getX()+0.5, pos2.getY()+0.5, pos2.getZ()+0.5); //Position vector to the second block

                    float r2 = r;
                    float g2 = g;
                    float b2 = b;

                    TileEntity tileEntity = component.getTheWorld().getTileEntity(pos2);
                    if(tileEntity instanceof ILightComponent){
                        float[] colors2 = ((ILightComponent) tileEntity).getTier().getColors();
                        r2 = colors2[0];
                        g2 = colors2[1];
                        b2 = colors2[2];
                    }


                    Vec3d v1 = new Vec3d(vpos2);
                    v1.sub(vpos1);

                    double pitch = Math.atan2(v1.y, Math.sqrt(v1.x * v1.x + v1.z + v1.z));
                    double yaw =  Math.atan2(-v1.z, v1.x);

                    double beamlen = v1.length(); // length of the beam (distance)
                    double width = BEAM_SIZE/2;

                    GlStateManager.pushMatrix();

                    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
                    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
                    GlStateManager.disableLighting();
                    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    //GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);

                    GlStateManager.translate(x+0.5,y+0.5,z+0.5);
                    GlStateManager.rotate((float)(180*yaw/Math.PI),0,1,0);
                    GlStateManager.rotate((float)(180*pitch/Math.PI),0,0,1);
                    GlStateManager.rotate(rot,1,0,0);

                    render.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
                    render.pos(beamlen, width, width).color(r2,g2,b2,BEAM_ALPHA).endVertex();
                    render.pos(0, width, width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(0, -width, width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(beamlen, -width, width).color(r2,g2,b2,BEAM_ALPHA).endVertex();

                    render.pos(beamlen, -width, -width).color(r2,g2,b2,BEAM_ALPHA).endVertex();
                    render.pos(0, -width, -width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(0, width, -width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(beamlen, width, -width).color(r2,g2,b2,BEAM_ALPHA).endVertex();

                    render.pos(beamlen, width, -width).color(r2,g2,b2,BEAM_ALPHA).endVertex();
                    render.pos(0, width, -width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(0, width, width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(beamlen, width, width).color(r2,g2,b2,BEAM_ALPHA).endVertex();

                    render.pos(beamlen, -width, width).color(r2,g2,b2,BEAM_ALPHA).endVertex();
                    render.pos(0, -width, width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(0, -width, -width).color(r,g,b,BEAM_ALPHA).endVertex();
                    render.pos(beamlen, -width, -width).color(r2,g2,b2,BEAM_ALPHA).endVertex();
                    tessy.draw();

                    GlStateManager.disableBlend();
                    GlStateManager.enableTexture2D();

                    GlStateManager.enableLighting();
                    GlStateManager.enableTexture2D();
                    GlStateManager.popMatrix();
                }
            }
        }
    }

    @Override
    public ILightNetworkHandler getLightNetworkHandler(){
        return this.lightNetworkHandler;
    }

}
