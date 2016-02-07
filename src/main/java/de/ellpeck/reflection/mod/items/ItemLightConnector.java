package de.ellpeck.reflection.mod.items;

import de.ellpeck.reflection.mod.tile.TileLightComponent;
import de.ellpeck.reflection.mod.network.LightNetworkHandler;
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
        if(tile instanceof TileLightComponent){
            if(!world.isRemote){
                if(this.hasStoredPosition(stack)){
                    TileLightComponent second = this.getPosition(stack, world);
                    stack.setTagCompound(new NBTTagCompound());
                    if(second != null){
                        if(LightNetworkHandler.instance.addConnection(posHit, second.getPos(), world, true)){
                            VanillaPacketHandler.sendTilePacketToAllAround(tile);
                            VanillaPacketHandler.sendTilePacketToAllAround(second);

                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    this.storePosition(stack, (TileLightComponent)tile);
                }
            }
            return true;
        }
        return false;
    }

    private void storePosition(ItemStack stack, TileLightComponent component){
        if(stack.getTagCompound() == null){
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound compound = stack.getTagCompound();
        WorldUtil.writeBlockPosToNBT(compound, component.getPos());
    }

    private TileLightComponent getPosition(ItemStack stack, World world){
        if(stack.getTagCompound() != null){
            BlockPos pos = WorldUtil.readBlockPosFromNBT(stack.getTagCompound());
            TileEntity tile = world.getTileEntity(pos);
            if(tile instanceof TileLightComponent){
                return (TileLightComponent)tile;
            }
        }
        return null;
    }

    private boolean hasStoredPosition(ItemStack stack){
        return stack.getTagCompound() != null && WorldUtil.hasBlockPosData(stack.getTagCompound());
    }
}
