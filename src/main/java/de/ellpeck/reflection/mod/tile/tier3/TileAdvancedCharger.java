/*
 * This file ("TileAdvancedCharger.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier3;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.mod.tile.tier2.TileCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileAdvancedCharger extends TileCharger{

    @Override
    protected int getUse(){
        return 100;
    }

    @Override
    protected boolean tryCharge(){
        int range = 8;
        List<EntityPlayer> playersAround = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.fromBounds(this.pos.getX()-range, this.pos.getY()-range, this.pos.getZ()-range, this.pos.getX()+range, this.pos.getY()+range, this.pos.getZ()+range));

        for(EntityPlayer player : playersAround){
            if(player != null && !player.isDead && player.inventory != null){
                if(ReflectionAPI.theMethodHandler.insertLightIntoInventory(player.inventory, 5, true) > 0){
                    return true;
                }
            }
        }
        return false;
    }

}
