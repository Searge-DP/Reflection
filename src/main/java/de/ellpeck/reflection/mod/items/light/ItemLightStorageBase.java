/*
 * This file ("LightStorageItemBase.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.items.light;

import de.ellpeck.reflection.api.light.ILightStorageItem;
import de.ellpeck.reflection.mod.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemLightStorageBase extends ItemBase implements ILightStorageItem{

    private static final String TAG_LIGHT = "Light";
    private int maxLight;
    private boolean staysForever;

    public ItemLightStorageBase(String name, boolean addTab, int maxLight, boolean staysForever){
        super(name, addTab);
        this.maxLight = maxLight;
        this.setMaxStackSize(1);
        this.staysForever = staysForever;
    }

    @Override
    public int getEntityLifespan(ItemStack stack, World world){
        return this.staysForever ? Integer.MAX_VALUE : super.getEntityLifespan(stack, world);
    }

    @Override
    public int getLight(ItemStack stack){
        NBTTagCompound compound = stack.getTagCompound();
        if(compound != null){
            return compound.getInteger(TAG_LIGHT);
        }
        else{
            return 0;
        }
    }

    @Override
    public int getMaxLight(ItemStack stack){
        return this.maxLight;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean displayTopBar(ItemStack stack){
        return false;
    }

    @Override
    public int extractLight(ItemStack stack, int amount, boolean actuallyDo){
        int light = this.getLight(stack);
        if(light > 0){
            int extracted = Math.min(light, amount);
            if(actuallyDo){
                this.setLight(stack, light-extracted);
            }

            return extracted;
        }
        else{
            return 0;
        }
    }

    @Override
    public int insertLight(ItemStack stack, int amount, boolean actuallyDo){
        int light = this.getLight(stack);
        int maxLight = this.getMaxLight(stack);
        if(light < maxLight){

            int inserted = Math.min(amount, maxLight-light);
            if(actuallyDo){
                this.setLight(stack, light+inserted);
            }

            return inserted;
        }
        else{
            return 0;
        }
    }

    @Override
    public void setLight(ItemStack stack, int amount){
        if(stack.getTagCompound() == null){
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger(TAG_LIGHT, amount);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list){
        ItemStack empty = new ItemStack(item);
        this.setLight(empty, 0);
        list.add(empty);

        ItemStack full = new ItemStack(item);
        this.setLight(full, this.getMaxLight(full));
        list.add(full);
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack){
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack){
        double maxAmount = this.getMaxLight(stack);
        double energyDif = maxAmount-this.getLight(stack);
        return energyDif/maxAmount;
    }
}
