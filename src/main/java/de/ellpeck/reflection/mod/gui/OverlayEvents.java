/*
 * This file ("HudEvents.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.gui;

import de.ellpeck.reflection.api.light.ILightStorageItem;
import de.ellpeck.reflection.api.light.ILightTierDisplay;
import de.ellpeck.reflection.api.light.IRodOverlay;
import de.ellpeck.reflection.mod.items.ItemLightConnector;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.util.ClientUtil;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OverlayEvents{

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event){
        ItemStack stack = event.itemStack;
        if(stack != null){
            Item item = stack.getItem();
            if(item != null){
                Block block = Block.getBlockFromItem(item);
                if(block instanceof ILightTierDisplay){
                    event.toolTip.add(EnumChatFormatting.GOLD+StatCollector.translateToLocal(((ILightTierDisplay)block).getTier().getUnlocalizedName()));
                }
                if(item instanceof ILightTierDisplay){
                    event.toolTip.add(EnumChatFormatting.GOLD+StatCollector.translateToLocal(((ILightTierDisplay)item).getTier().getUnlocalizedName()));
                }
            }
        }
    }

    @SubscribeEvent
    public void onGameOverlay(RenderGameOverlayEvent.Post event){
        if(event.type == RenderGameOverlayEvent.ElementType.ALL){
            Minecraft mc = ClientUtil.mc();
            Profiler profiler = mc.mcProfiler;
            EntityPlayer player = mc.thePlayer;
            ScaledResolution res = event.resolution;
            World world = mc.theWorld;
            MovingObjectPosition posHit = mc.objectMouseOver;

            profiler.startSection(LibMod.MOD_NAME+"HUD");
            profiler.startSection("LightOverlay");

            int lightAmount = 0;
            int maxLightAmount = 0;

            for(int i = 0; i < player.inventory.getSizeInventory(); i++){
                ItemStack stack = player.inventory.getStackInSlot(i);
                if(stack != null){
                    Item item = stack.getItem();
                    if(item instanceof ILightStorageItem){
                        ILightStorageItem storage = (ILightStorageItem)item;
                        if(storage.displayTopBar(stack)){
                            lightAmount += storage.getLight(stack);
                            maxLightAmount += storage.getMaxLight(stack);
                        }
                    }
                }
            }

            if(lightAmount > 0 && maxLightAmount > 0 && lightAmount <= maxLightAmount){
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();

                float[] color = ClientUtil.getWheelColor(WorldUtil.totalTime(world)%256);
                GlStateManager.color(color[0]/255, color[1]/255, color[2]/255);

                mc.renderEngine.bindTexture(ClientUtil.MISC_GRAPHICS);
                int graphicLength = 12;

                int totalPixels = lightAmount*res.getScaledWidth()/maxLightAmount;
                int fullGraphics = totalPixels/graphicLength;
                int extraPixels = totalPixels%graphicLength;

                for(int i = 0; i < fullGraphics; i++){
                    ClientUtil.drawTexturedModalRect(i*graphicLength, 0, 0, 0, 0, 12, 8);
                }
                ClientUtil.drawTexturedModalRect(fullGraphics*graphicLength, 0, 0, 0, 0, extraPixels, 8);

                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            profiler.endSection();
            profiler.startSection("RodOverlay");

            if(posHit != null){
                ItemStack heldStack = player.getHeldItem();
                if(heldStack != null && heldStack.getItem() instanceof ItemLightConnector){
                    BlockPos hitPos = posHit.getBlockPos();
                    if(hitPos != null){
                        IBlockState stateHit = world.getBlockState(hitPos);
                        if(stateHit != null){
                            Block blockHit = stateHit.getBlock();
                            if(blockHit instanceof IRodOverlay){
                                ((IRodOverlay)blockHit).displayOverlay(heldStack, res, hitPos, world);
                            }
                        }

                        TileEntity tileHit = world.getTileEntity(hitPos);
                        if(tileHit instanceof IRodOverlay){
                            GlStateManager.pushMatrix();
                            GlStateManager.color(1, 1, 1);
                            ((IRodOverlay)tileHit).displayOverlay(heldStack, res, hitPos, world);
                            GlStateManager.popMatrix();
                        }
                    }
                }
            }

            profiler.endSection();
            profiler.endSection();
        }
    }
}
