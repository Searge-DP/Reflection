/*
 * This file ("TileGlassShards.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile;

import de.ellpeck.reflection.mod.blocks.InitBlocks;
import de.ellpeck.reflection.mod.lib.LibMod;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class TileGlassShards extends TileEntity implements ITickable{

    private static final String TAG_CONVERSION_TIME = "ConversionTime";
    private int conversionTime;

    private static Map<String, Block> conversions = new HashMap<String, Block>();

    public static void postInit(){
        conversions.put("oreIron", InitBlocks.blockOreLightanium);
        conversions.put("blockIron", InitBlocks.blockLightanium);
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            int light = WorldUtil.getSkylight(this);
            if(light >= 10){
                if(this.conversionTime > 0){
                    this.conversionTime--;
                    if(this.conversionTime <= 0){
                        BlockPos posBelow = this.pos.offset(EnumFacing.DOWN);
                        IBlockState stateBelow = this.worldObj.getBlockState(posBelow);
                        if(stateBelow != null){
                            Block blockBelow = stateBelow.getBlock();
                            if(blockBelow != null){
                                Block newBlockBelow = this.getConversion(blockBelow);
                                if(newBlockBelow != null){
                                    IBlockState newStateBelow = newBlockBelow.getDefaultState();
                                    this.worldObj.setBlockState(posBelow, newStateBelow);
                                    this.worldObj.playAuxSFX(2001, posBelow, Block.getStateId(newStateBelow));
                                }
                            }
                        }
                    }
                }
                else{
                    this.conversionTime = LibMod.RANDOM.nextInt(500)+500;
                }
            }
            else{
                this.conversionTime = 0;
            }
        }
    }

    private Block getConversion(Block block){
        int[] oreIDs = OreDictionary.getOreIDs(new ItemStack(block));
        if(oreIDs != null){
            for(int id : oreIDs){
                String oreName = OreDictionary.getOreName(id);
                if(oreName != null && !oreName.isEmpty()){
                    if(conversions.containsKey(oreName)){
                        return conversions.get(oreName);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.conversionTime = compound.getInteger(TAG_CONVERSION_TIME);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger(TAG_CONVERSION_TIME, this.conversionTime);
    }
}
