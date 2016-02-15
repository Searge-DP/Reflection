/*
 * This file ("TileCharger.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier2;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.api.light.ILightStorageItem;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.api.light.TileLightComponent;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ITickable;

import java.util.List;

public class TileCharger extends TileLightComponent implements ITickable{

    private static final String TAG_USES_LIGHT = "UsesLight";
    private boolean usesLightInNetwork;

    @Override
    public LightNetworkTier getTier(){
        return ReflectionAPI.TIER_2;
    }

    @Override
    public int getMaxConnections(){
        return 1;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 10;
    }

    @Override
    public void readNBT(NBTTagCompound compound, boolean sync){
        super.readNBT(compound, sync);
        this.usesLightInNetwork = compound.getBoolean(TAG_USES_LIGHT);
    }

    @Override
    public void writeNBT(NBTTagCompound compound, boolean sync){
        super.writeNBT(compound, sync);
        compound.setBoolean(TAG_USES_LIGHT, this.usesLightInNetwork);
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            int needPerTick = 40;
            int chargePerTick = 3;
            ILightNetwork network = WorldUtil.getNetworkForTile(this);
            if(network != null){
                boolean chargedOnce = false;
                if(network.getTotalLightExcluded(this) >= needPerTick){
                    int range = 3;
                    List<EntityItem> itemsAround = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.fromBounds(this.pos.getX()-range, this.pos.getY()-range, this.pos.getZ()-range, this.pos.getX()+range, this.pos.getY()+range, this.pos.getZ()+range));

                    for(EntityItem item : itemsAround){
                        if(item != null && !item.isDead){
                            ItemStack stack = item.getEntityItem();
                            if(stack != null && stack.stackSize > 0){
                                if(stack.getItem() instanceof ILightStorageItem){
                                    ILightStorageItem lightStorage = (ILightStorageItem)stack.getItem();
                                    if(lightStorage.insertLight(stack, chargePerTick, true) > 0){
                                        chargedOnce = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if(chargedOnce){
                    if(!this.usesLightInNetwork){
                        network.addLightUser(this, needPerTick);
                        this.usesLightInNetwork = true;
                    }
                }
                else{
                    if(this.usesLightInNetwork){
                        network.removeLightUser(this);
                        this.usesLightInNetwork = false;
                    }
                }
            }
            else{
                this.usesLightInNetwork = false;
            }
        }
    }
}
