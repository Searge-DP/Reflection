package de.ellpeck.reflection.mod.gui;

import de.ellpeck.reflection.mod.lib.LibMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GameOverlayEvents{

    @SubscribeEvent
    public void drawDebug(RenderGameOverlayEvent.Text event){
        if(Minecraft.getMinecraft().gameSettings.showDebugInfo){
            event.left.add("");

            String prefix = EnumChatFormatting.DARK_GREEN+"["+LibMod.MOD_NAME+"]"+EnumChatFormatting.RESET+" ";
        }
    }

}
