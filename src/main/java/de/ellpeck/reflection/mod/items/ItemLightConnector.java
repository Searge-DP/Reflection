/*
 * This file ("ItemLightConnector.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.api.ReflectionAPI;
import de.ellpeck.reflection.api.light.ILightComponent;
import de.ellpeck.reflection.mod.util.VanillaPacketHandler;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLightConnector extends ItemBase{

    public ItemLightConnector(String name, boolean addTab){
        super(name, addTab);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos posHit, EnumFacing side, float hitX, float hitY, float hitZ){
        TileEntity tile = world.getTileEntity(posHit);
        if(tile instanceof ILightComponent){
            if(!world.isRemote){
                if(this.hasStoredPosition(stack)){
                    ILightComponent second = this.getPosition(stack, world);
                    stack.setTagCompound(new NBTTagCompound());
                    if(second != null){
                        if(ReflectionAPI.theLightNetworkHandler.addConnection(posHit, second.getPosition(), world, true)){
                            VanillaPacketHandler.sendTilePacketToAllAround(tile);
                            if(second instanceof TileEntity){
                                System.out.println("This works!");
                                VanillaPacketHandler.sendTilePacketToAllAround((TileEntity)second);
                            }

                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    this.storePosition(stack, (ILightComponent)tile);
                }
            }
            return true;
        }
        return false;
    }

    private void storePosition(ItemStack stack, ILightComponent component){
        if(stack.getTagCompound() == null){
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound compound = stack.getTagCompound();
        WorldUtil.writeBlockPosToNBT(compound, component.getPosition());
    }

    private ILightComponent getPosition(ItemStack stack, World world){
        if(stack.getTagCompound() != null){
            BlockPos pos = WorldUtil.readBlockPosFromNBT(stack.getTagCompound());
            TileEntity tile = world.getTileEntity(pos);
            if(tile instanceof ILightComponent){
                return (ILightComponent)tile;
            }
        }
        return null;
    }

    private boolean hasStoredPosition(ItemStack stack){
        return stack.getTagCompound() != null && WorldUtil.hasBlockPosData(stack.getTagCompound());
    }
}
