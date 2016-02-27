/*
 * This file ("TileRFConverter.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.tile.tier4;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import de.ellpeck.reflection.api.internal.ILightNetwork;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import de.ellpeck.reflection.mod.util.WorldUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileRFConverter extends TileLightComponentBase implements IEnergyProvider, ITickable{

    public EnergyStorage storage = new EnergyStorage(10000, 300);

    private boolean usesLightInNetwork;

    @Override
    public int getMaxConnections(){
        return 1;
    }

    @Override
    public int getMaxDistanceFromComponent(){
        return 10;
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate){
        return this.storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from){
        return this.storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from){
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from){
        return true;
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            ILightNetwork network = WorldUtil.getNetworkForTile(this);
            if(network != null){
                int need = 300;
                int rfGen = 40;
                if(network.getTotalLightExcluded(this) >= need && this.storage.receiveEnergy(rfGen, false) >= rfGen){
                    if(!this.usesLightInNetwork){
                        network.addLightGen(this, need);
                        this.usesLightInNetwork = true;
                    }
                }
                else{
                    if(this.usesLightInNetwork){
                        network.removeLightGen(this);
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
