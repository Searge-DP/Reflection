/*
 * This file ("ClientUtil.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.util;

import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientUtil{

    public static final ResourceLocation MISC_GRAPHICS = new ResourceLocation(LibMod.MOD_ID, "textures/gui/guiMisc.png");

    public static Minecraft mc(){
        return Minecraft.getMinecraft();
    }

    public static long totalTime(){
        return mc().theWorld.getTotalWorldTime();
    }

    public static void drawTexturedModalRect(int x, int y, double z, int textureX, int textureY, int width, int height){
        float f = 0.00390625F;
        Tessellator tessy = Tessellator.getInstance();
        WorldRenderer renderer = tessy.getWorldRenderer();
        renderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderer.pos((double)(x), (double)(y+height), z).tex((double)((float)(textureX)*f), (double)((float)(textureY+height)*f)).endVertex();
        renderer.pos((double)(x+width), (double)(y+height), z).tex((double)((float)(textureX+width)*f), (double)((float)(textureY+height)*f)).endVertex();
        renderer.pos((double)(x+width), (double)(y), z).tex((double)((float)(textureX+width)*f), (double)((float)(textureY)*f)).endVertex();
        renderer.pos((double)(x), (double)(y), z).tex((double)((float)(textureX)*f), (double)((float)(textureY)*f)).endVertex();
        tessy.draw();
    }

    public static float[] getWheelColor(float pos){
        if(pos < 85){
            return new float[]{pos*3, 255-pos*3, 0};
        }
        else if(pos < 170){
            pos -= 85;
            return new float[]{255-pos*3, 0, pos*3};
        }
        else{
            pos -= 170;
            return new float[]{0, pos*3, 255-pos*3};
        }
    }
}
