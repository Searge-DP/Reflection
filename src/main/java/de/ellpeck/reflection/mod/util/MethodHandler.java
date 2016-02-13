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
import de.ellpeck.reflection.api.light.ILightStorageItem;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Set;

public class MethodHandler implements IMethodHandler{

    private final ILightNetworkHandler lightNetworkHandler;

    @SideOnly(Side.CLIENT)
    private static final ResourceLocation GRADIENT = new ResourceLocation(LibMod.MOD_ID, "textures/gradient.png");

    public MethodHandler(){
        this.lightNetworkHandler = new LightNetworkHandler();
    }

    private static final String TAG_CONNECTIONS = "Connections";

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
            for(IConnectionPair pair : connections){
                if(component.getPosition().equals(pair.getFirst())){
                    BlockPos firstPos = pair.getFirst();
                    BlockPos secondPos = pair.getSecond();

                    TileEntity tile = component.getTheWorld().getTileEntity(secondPos);
                    if(tile instanceof ILightComponent){
                        this.renderLightBetweenTwoPoints(firstPos.getX()+0.5, firstPos.getY()+0.5, firstPos.getZ()+0.5, secondPos.getX()+0.5, secondPos.getY()+0.5, secondPos.getZ()+0.5, 120, 0.5F, 0.1, component.getTier().getColors(), ((ILightComponent)tile).getTier().getColors());
                    }
                }
            }
        }
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    @SideOnly(Side.CLIENT)
    public void renderLightBetweenTwoPoints(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ, int rotationTime, float alpha, double beamWidth, float[] firstColor, float[] secondColor){
        Tessellator tessy = Tessellator.getInstance();
        WorldRenderer render = tessy.getWorldRenderer();
        World world = ClientUtil.mc().theWorld;

        GlStateManager.disableFog();

        float r = secondColor[0];
        float g = secondColor[1];
        float b = secondColor[2];

        float r2 = firstColor[0];
        float g2 = firstColor[1];
        float b2 = firstColor[2];

        Vec3d vec1 = new Vec3d(firstX, firstY, firstZ);
        Vec3d vec2 = new Vec3d(secondX, secondY, secondZ);
        Vec3d combinedVec = new Vec3d(vec2);
        combinedVec.sub(vec1);

        double rot = rotationTime > 0 ? (360F*(((float)world.getTotalWorldTime()%(float)rotationTime)/(float)rotationTime)) : 0;
        double pitch = Math.atan2(combinedVec.y, Math.sqrt(combinedVec.x * combinedVec.x + combinedVec.z * combinedVec.z));
        double yaw =  Math.atan2(-combinedVec.z, combinedVec.x);

        double length = combinedVec.length();

        GlStateManager.pushMatrix();

        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.depthMask(false);
        GlStateManager.translate(firstX-TileEntityRendererDispatcher.staticPlayerX, firstY-TileEntityRendererDispatcher.staticPlayerY, firstZ-TileEntityRendererDispatcher.staticPlayerZ);
        GlStateManager.rotate((float)(180*yaw/Math.PI), 0, 1, 0);
        GlStateManager.rotate((float)(180*pitch/Math.PI), 0, 0, 1);
        GlStateManager.rotate((float)rot, 1, 0, 0);

        render.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        ClientUtil.mc().renderEngine.bindTexture(GRADIENT);

        render.pos(length, -beamWidth, beamWidth).tex(0,0).color(r,g,b,alpha).endVertex();
        render.pos(length, beamWidth, beamWidth).tex(0,1).color(r,g,b,alpha).endVertex();
        render.pos(0, beamWidth, beamWidth).tex(1,1).color(r,g,b,alpha).endVertex();
        render.pos(0, -beamWidth, beamWidth).tex(1,0).color(r,g,b,alpha).endVertex();

        render.pos(length, -beamWidth, beamWidth).tex(1,0).color(r2,g2,b2,alpha).endVertex();
        render.pos(length, beamWidth, beamWidth).tex(1,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, beamWidth, beamWidth).tex(0,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, -beamWidth, beamWidth).tex(0,0).color(r2,g2,b2,alpha).endVertex();

        render.pos(length, beamWidth, -beamWidth).tex(0,0).color(r,g,b,alpha).endVertex();
        render.pos(length, -beamWidth, -beamWidth).tex(0,1).color(r,g,b,alpha).endVertex();
        render.pos(0, -beamWidth, -beamWidth).tex(1,1).color(r,g,b,alpha).endVertex();
        render.pos(0, beamWidth, -beamWidth).tex(1,0).color(r,g,b,alpha).endVertex();

        render.pos(length, beamWidth, -beamWidth).tex(1,0).color(r2,g2,b2,alpha).endVertex();
        render.pos(length, -beamWidth, -beamWidth).tex(1,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, -beamWidth, -beamWidth).tex(0,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, beamWidth, -beamWidth).tex(0,0).color(r2,g2,b2,alpha).endVertex();

        render.pos(length, beamWidth, beamWidth).tex(0,0).color(r,g,b,alpha).endVertex();
        render.pos(length, beamWidth, -beamWidth).tex(0,1).color(r,g,b,alpha).endVertex();
        render.pos(0, beamWidth, -beamWidth).tex(1,1).color(r,g,b,alpha).endVertex();
        render.pos(0, beamWidth, beamWidth).tex(1,0).color(r,g,b,alpha).endVertex();

        render.pos(length, beamWidth, beamWidth).tex(1,0).color(r2,g2,b2,alpha).endVertex();
        render.pos(length, beamWidth, -beamWidth).tex(1,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, beamWidth, -beamWidth).tex(0,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, beamWidth, beamWidth).tex(0,0).color(r2,g2,b2,alpha).endVertex();

        render.pos(length, -beamWidth, -beamWidth).tex(0,0).color(r,g,b,alpha).endVertex();
        render.pos(length, -beamWidth, beamWidth).tex(0,1).color(r,g,b,alpha).endVertex();
        render.pos(0, -beamWidth, beamWidth).tex(1,1).color(r,g,b,alpha).endVertex();
        render.pos(0, -beamWidth, -beamWidth).tex(1,0).color(r,g,b,alpha).endVertex();

        render.pos(length, -beamWidth, -beamWidth).tex(1,0).color(r2,g2,b2,alpha).endVertex();
        render.pos(length, -beamWidth, beamWidth).tex(1,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, -beamWidth, beamWidth).tex(0,1).color(r2,g2,b2,alpha).endVertex();
        render.pos(0, -beamWidth, -beamWidth).tex(0,0).color(r2,g2,b2,alpha).endVertex();
        tessy.draw();

        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();

        GlStateManager.enableFog();
    }

    @Override
    public ILightNetworkHandler getLightNetworkHandler(){
        return this.lightNetworkHandler;
    }

    @Override
    public int insertLightIntoInventory(InventoryPlayer inventory, int amount, boolean actuallyDo){
        int inserted = 0;
        for(int i = 0; i < inventory.getSizeInventory(); i++){
            ItemStack stack = inventory.getStackInSlot(i);
            if(stack != null){
                Item item = stack.getItem();
                if(item instanceof ILightStorageItem){
                    inserted += ((ILightStorageItem)item).insertLight(stack, amount-inserted, actuallyDo);
                }
            }
        }
        return inserted;
    }

    @Override
    public int extractLightFromInventory(InventoryPlayer inventory, int amount, boolean actuallyDo){
        int extracted = 0;
        for(int i = 0; i < inventory.getSizeInventory(); i++){
            ItemStack stack = inventory.getStackInSlot(i);
            if(stack != null){
                Item item = stack.getItem();
                if(item instanceof ILightStorageItem){
                    extracted += ((ILightStorageItem)item).extractLight(stack, amount-extracted, actuallyDo);
                }
            }
        }
        return extracted;
    }

}
