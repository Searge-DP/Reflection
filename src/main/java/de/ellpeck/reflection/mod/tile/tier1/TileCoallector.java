/*
 * This file ("TileCoallector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier1;

import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.api.light.IRodOverlay;
import de.ellpeck.reflection.mod.misc.VanillaPacketHandler;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import de.ellpeck.reflection.mod.util.ClientUtil;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileCoallector extends TileLightComponentBase implements ITickable, IRodOverlay{

    private static final String TAG_BURN_TIME = "BurnTime";
    private static final String TAG_MAX_BURN_TIME = "MaxBurnTime";

    private boolean hadEnoughLight;
    public int burnTime;
    private int maxBurnTime;

    private int lastBurnTime;
    private int lastMaxBurnTime;
    private boolean hadNetwork;

    @Override
    public int getMaxConnections(){
        return 1;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 8;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.burnTime = compound.getInteger(TAG_BURN_TIME);
        this.maxBurnTime = compound.getInteger(TAG_MAX_BURN_TIME);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger(TAG_BURN_TIME, this.burnTime);
        compound.setInteger(TAG_MAX_BURN_TIME, this.maxBurnTime);
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            boolean burnTimeWatcher = this.burnTime > 0;
            if(this.burnTime <= 0){
                if(WorldUtil.totalTime(this.worldObj)%100 == 0){
                    int range = 3;
                    List<EntityItem> itemsAround = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.fromBounds(this.pos.getX()-range, this.pos.getY()-range, this.pos.getZ()-range, this.pos.getX()+range, this.pos.getY()+range, this.pos.getZ()+range));
                    for(EntityItem item : itemsAround){
                        if(item != null && !item.isDead){
                            ItemStack entityStack = item.getEntityItem();
                            if(entityStack != null && entityStack.stackSize > 0){
                                int stackBurnTime = TileEntityFurnace.getItemBurnTime(entityStack);
                                if(stackBurnTime > 0){
                                    this.burnTime = stackBurnTime;
                                    this.maxBurnTime = stackBurnTime;

                                    entityStack.stackSize--;
                                    if(entityStack.stackSize <= 0){
                                        Item entityItem = entityStack.getItem();
                                        if(entityItem != null && entityItem.hasContainerItem(entityStack)){
                                            item.setEntityItemStack(entityItem.getContainerItem(entityStack));
                                        }
                                        else{
                                            item.setDead();
                                        }
                                    }

                                    if(this.worldObj instanceof WorldServer){
                                        WorldServer worldServer = (WorldServer)this.worldObj;
                                        worldServer.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, item.posX, item.posY, item.posZ, 30, 0, 0, 0, 0.05);
                                    }

                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else{
                this.burnTime--;
            }

            boolean hasEnoughLight = WorldUtil.getSkylight(this) >= 10;

            ILightNetwork network = WorldUtil.getNetworkForTile(this);
            boolean hasNetwork = network != null;

            if(this.hadNetwork != hasNetwork || this.hadEnoughLight != hasEnoughLight || burnTimeWatcher != this.burnTime > 0){
                this.hadEnoughLight = hasEnoughLight;
                this.hadNetwork = hasNetwork;

                if(hasNetwork){
                    if(this.burnTime > 0 && hasEnoughLight){
                        network.addLightGen(this, 10);
                    }
                    else{
                        network.removeLightGen(this);
                    }
                }
            }

            if((this.burnTime != this.lastBurnTime || this.maxBurnTime != this.lastMaxBurnTime) && WorldUtil.totalTime(this.worldObj)%10 == 0){
                this.lastBurnTime = this.burnTime;
                this.lastMaxBurnTime = this.maxBurnTime;
                VanillaPacketHandler.sendTilePacketToAllAround(this);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayOverlay(ItemStack heldStack, ScaledResolution resolution, BlockPos hitPos, World world){
        ClientUtil.mc().renderEngine.bindTexture(ClientUtil.MISC_GRAPHICS);
        if(this.burnTime > 0 && this.maxBurnTime > 0){
            int scale = this.burnTime*13/this.maxBurnTime;
            ClientUtil.drawTexturedModalRect(resolution.getScaledWidth()/2+5, resolution.getScaledHeight()/2+5+12-scale, 0, 13, 12-scale, 13, scale+1);
        }
    }
}
