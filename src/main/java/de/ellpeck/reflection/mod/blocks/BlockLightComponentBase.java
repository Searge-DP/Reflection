/*
 * This file ("BlockLightComponentBas.java") is part of the Reflection Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Reflection License to be found at
 * https://github.com/Ellpeck/Reflection/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Reflection
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.reflection.mod.blocks;

import de.ellpeck.reflection.api.light.ILightTierDisplay;
import de.ellpeck.reflection.api.light.LightNetworkTier;
import de.ellpeck.reflection.mod.tile.TileLightComponentBase;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLightComponentBase extends BlockContainerBase implements ILightTierDisplay{

    private LightNetworkTier theTier;

    public BlockLightComponentBase(String name, LightNetworkTier tier, boolean addTab, Class<? extends TileLightComponentBase> tileClass, String tileName){
        super(Material.glass, name, addTab, tileClass, tileName);
        this.theTier = tier;

        this.setStepSound(soundTypeGlass);
        this.setLightOpacity(1);
        this.setHardness(5.0F);
    }

    @Override
    public LightNetworkTier getTier(){
        return this.theTier;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer(){
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean isFullCube(){
        return false;
    }
}
