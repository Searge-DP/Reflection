/*
 * This file ("TileFireGen.java") is part of the Reflection Mod for Minecraft.
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
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldProviderHell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileFireGen extends TileLightComponentBase implements ITickable{

    private static final String TAG_COOLDOWN = "Cooldown";
    private int fireCooldown;

    private boolean hadNetwork;

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.fireCooldown = compound.getInteger(TAG_COOLDOWN);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger(TAG_COOLDOWN, this.fireCooldown);
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
    public void update(){
        if(!this.worldObj.isRemote){
            if(this.worldObj.provider instanceof WorldProviderHell){
                boolean cooldownWatcher = this.fireCooldown > 0;
                if(this.fireCooldown <= 0){
                    int range = 5;

                    List<BlockPos> possiblePositions = new ArrayList<BlockPos>();
                    for(int x = -range; x <= range; x++){
                        for(int z = -range; z <= range; z++){
                            for(int y = -range; y <= range; y++){
                                BlockPos possiblePos = new BlockPos(this.getPos().getX()+x, this.getPos().getY()+y, this.getPos().getZ()+z);
                                IBlockState state = this.worldObj.getBlockState(possiblePos);

                                if(state != null && state.getBlock() instanceof BlockFire){
                                    possiblePositions.add(possiblePos);
                                }
                            }
                        }
                    }

                    if(!possiblePositions.isEmpty()){
                        Collections.shuffle(possiblePositions);

                        BlockPos toEat = possiblePositions.get(0);
                        WorldUtil.setBlockWithParticles(this.worldObj, toEat, Blocks.air.getDefaultState(), false);

                        this.fireCooldown = 50;
                    }
                }
                else{
                    this.fireCooldown--;
                }

                ILightNetwork network = WorldUtil.getNetworkForTile(this);
                boolean hasNetwork = network != null;

                if(cooldownWatcher != this.fireCooldown > 0 || hasNetwork != this.hadNetwork){
                    this.hadNetwork = hasNetwork;

                    if(hasNetwork){
                        if(this.fireCooldown > 0){
                            network.addLightGen(this, 30);
                        }
                        else{
                            network.removeLightGen(this);
                        }
                    }
                }
            }
        }
    }
}
