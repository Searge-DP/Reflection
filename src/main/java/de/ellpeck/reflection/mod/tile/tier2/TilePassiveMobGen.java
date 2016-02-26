/*
 * This file ("TilePassiveMobGen.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier2;

import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ITickable;

import java.util.List;

public class TilePassiveMobGen extends TileLightComponentBase implements ITickable{

    private static final String TAG_LIGHT_GENERATED = "LightGenerated";
    private int amountGeneratedInNetwork;

    @Override
    public int getMaxConnections(){
        return 1;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 12;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.amountGeneratedInNetwork = compound.getInteger(TAG_LIGHT_GENERATED);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger(TAG_LIGHT_GENERATED, this.amountGeneratedInNetwork);
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            ILightNetwork network = WorldUtil.getNetworkForTile(this);
            if(network != null){
                int range = 4;
                List<EntityAnimal> animals = this.worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.fromBounds(this.pos.getX()-range, this.pos.getY()-range, this.pos.getZ()-range, this.pos.getX()+range, this.pos.getY()+range, this.pos.getZ()+range));

                int genAmount = 0;
                for(EntityAnimal animal : animals){
                    if(animal != null && !animal.isChild() && !animal.isInLove()){
                        genAmount += animal.getHealth()/4;
                    }
                }

                if(genAmount > 0){
                    if(this.amountGeneratedInNetwork != genAmount){
                        this.amountGeneratedInNetwork = genAmount;
                        network.addLightGen(this, this.amountGeneratedInNetwork);
                    }
                }
                else{
                    if(this.amountGeneratedInNetwork > 0){
                        network.removeLightGen(this);
                        this.amountGeneratedInNetwork = 0;
                    }
                }
            }
            else{
                this.amountGeneratedInNetwork = 0;
            }
        }
    }
}
