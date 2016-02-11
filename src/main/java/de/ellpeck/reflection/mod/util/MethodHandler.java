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

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.IConnectionPair;
import de.ellpeck.reflection.api.internal.IMethodHandler;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public class MethodHandler implements IMethodHandler{

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
                ReflectionAPI.theLightNetworkHandler.removeConnections(tile.getPosition(), tile.getTheWorld());
            }
            else{
                Set<IConnectionPair> connections = WorldUtil.getAllConnectionsForTile(tile);
                for(int i = 0; i < list.tagCount(); i++){
                    LightNetworkHandler.ConnectionPair pair = LightNetworkHandler.ConnectionPair.readFromNBT(list.getCompoundTagAt(i));
                    if(!connections.contains(pair)){
                        ReflectionAPI.theLightNetworkHandler.addConnection(pair.first, pair.second, tile.getTheWorld(), false);
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderLightForConnections(ILightComponent component){
        Set<IConnectionPair> connections = WorldUtil.getAllConnectionsForTile(component);
        if(connections != null){

            Tessellator tessy = Tessellator.getInstance();
            WorldRenderer renderer = tessy.getWorldRenderer();

            float[] colors = component.getTier().getColors();
            float r = colors[0];
            float g = colors[1];
            float b = colors[2];

            for(IConnectionPair pair : connections){
                if(component.getPosition().equals(pair.getFirst())){
                    //TODO Fix this because I don't get it whatsoever
                    //If anyone wants to do this: The connection should be rendered between
                    //pair.getFirst() and pair.getSecond(), everything below this could be deleted
                    //if it's nonsense. Which it probably is. Everything above this should stay and
                    //can be used. And yes, if you rename Tessy, I will be mad.
                    //So what this is basically supposed to do:
                    //Render a light beam between the two components. I don't care if they rotate or not,
                    //they just have to be shiny and render in the color that's specified above.
                    //Also please make it so that I know how to change the size (how big they are). Thanks.
                }
            }
        }
    }

}
